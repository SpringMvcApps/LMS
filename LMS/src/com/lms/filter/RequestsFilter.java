package com.lms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.NestedServletException;

import com.lms.appException.AppSecurityException;
import com.lms.appException.ApplicationException;
import com.lms.commons.security.SecurityService;

public class RequestsFilter implements Filter {
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException
	{
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
	    RequestDispatcher dispatcher=null;
	    boolean forwardRequest=false;
		httpResponse.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		//set cache-control header to disable caching  (HTTP/1.0(older) spec).. because some old clients might not support the no-cache header above
		httpResponse.setHeader("Pragma","no-cache");
		//set dateHeader to prevent caching at the proxy server
		httpResponse.setDateHeader("Expires",0);
		try{
		chain.doFilter(httpRequest,httpResponse);
		}
		catch(Exception ex)
		{
			String exMessage=null;
	
			if(ex instanceof AppSecurityException || ex instanceof NestedServletException)
			{
				if(ex instanceof NestedServletException)
				{
					exMessage="Request is not valid, unable to complete.";	
				}
				else
				{
				exMessage=ex.getMessage();
				}
				Cookie[] cookies=httpRequest.getCookies();
				if(cookies!=null)
				{
					Cookie tokenCookie=SecurityService.getCookie(cookies, "requestToken");
					if(tokenCookie!=null)
					{
						tokenCookie.setValue("");
						tokenCookie.setMaxAge(-1);
						httpResponse.addCookie(tokenCookie);
						dispatcher=httpRequest.getRequestDispatcher("/Logout");
						httpRequest.setAttribute("exceptionMsg",exMessage);
						//httpRequest.setAttribute("redirectToLogin",true);
						dispatcher.forward(httpRequest,httpResponse);
					}
					
				}
			}
			else if(ex instanceof ApplicationException)
			{
				
				exMessage=ex.getMessage();
				forwardRequest=true;
			}
			else
			{
			forwardRequest=true;
			}
			if(forwardRequest==true)
			{
				dispatcher=httpRequest.getRequestDispatcher("/Error.jsp");
				httpRequest.setAttribute("exception",exMessage);
				dispatcher.forward(httpRequest,httpResponse);
			}
			
		}
	}
}
