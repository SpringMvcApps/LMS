package com.lms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException
	{
		HttpSession session=null;
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		session=httpRequest.getSession(false);
		if(session!=null && session.getAttribute("userId")!=null  && ((String)session.getAttribute("userIp")).equals(request.getRemoteAddr()))
		{
			
			chain.doFilter(httpRequest,httpResponse);
		
		}
		else
		{
			httpResponse.sendRedirect(httpRequest.getContextPath()+"?session=false&&targetPage="+httpRequest.getRequestURI());
		}
		

	}
		

}
