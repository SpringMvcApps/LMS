package com.lms.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.commons.security.SecurityService;

public class RequestFilter implements Filter {
	
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException
	{
		HttpServletRequest httpReq=(HttpServletRequest)request;
		HttpServletResponse httpRes=(HttpServletResponse)response;
		Random random=null;
		boolean isValid=false;
		boolean isBypass=false;
		String queryString=null;
		String forwardUrl=null;
		String url=null;
		System.out.println("Filtered");
		System.out.println(httpReq.getMethod());
		HttpSession session=httpReq.getSession(false);
		if(session!=null && session.getAttribute("userId")!=null)
		{
			
	    int userId=(Integer)session.getAttribute("userId");
	    int roleId=(Integer)session.getAttribute("roleId");
		if(userId!=0)
		{
			
			
			if((httpReq.getMethod()).equalsIgnoreCase("GET"))
			{
				if(httpReq.getHeader("X-Requested-With")==null)//to check if ajax if yes then dont generate token as for ajax req also token generated but its not get updated on jsp due to async
				{
				random=new Random();
				int token=random.nextInt(10000);
				System.out.println(token);
				session.setAttribute("sToken",token);
				httpReq.setAttribute("cToken",token);
				
				if(httpReq.getQueryString()==null)
				{
				url=httpReq.getRequestURI();
				int index=url.lastIndexOf("/");
				index=index+1;
				url=url.substring(index);
				System.out.println(url);
				if(!url.equalsIgnoreCase("Dashboard.htm"))
				{
				isValid=SecurityService.isAuthorisedRequest(url,roleId);
				if(isValid==true)
				{
				isBypass=true;
				}
				else
				{
					forwardUrl="/UnAuthorisedAccess.htm?pageName="+url;
				}
				}
				else
				{
					isBypass=true;
				}
				}
				else
				{
					isBypass=true;
				}
			}
			else
			{
				System.out.println("ajax request");
				isBypass=true;
				
			}
				if(isBypass==true)
				{
					chain.doFilter(httpReq,httpRes);
				}
				else
				{
					RequestDispatcher dispatcher=httpReq.getRequestDispatcher(forwardUrl);
					dispatcher.forward(httpReq, httpRes);
				}
			}
			else
			{
				int cToken=Integer.parseInt(httpReq.getParameter("cToken"));
				System.out.println("client"+cToken);
				int sToken=(Integer)session.getAttribute("sToken");
				System.out.println("server"+sToken);
				if(cToken==sToken)
				{
	                httpReq.setAttribute("cToken", cToken);
					chain.doFilter(httpReq,httpRes);	
				}
				else
				{
					httpRes.sendRedirect("/LMS/Error.jsp");
				}
				
			}
		
		}
		else{
			System.out.println(" a session");
			httpRes.sendRedirect("/LMS/");
		}
		}
		else{
			System.out.println("not a session");
			httpRes.sendRedirect("/LMS/");
		}
		
	}
	

}
