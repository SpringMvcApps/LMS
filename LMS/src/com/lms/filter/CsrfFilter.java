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
import javax.servlet.http.HttpSession;

import com.lms.appException.AppSecurityException;
import com.lms.commons.security.SecurityService;

public class CsrfFilter implements Filter {
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws ServletException,IOException
	{
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		HttpServletResponse httpResponse=(HttpServletResponse)response;
		String formTokenValue=null;
		String sessionTokenValue=null;
		String cookieTokenValue=null;
		Cookie tokenCookie=null;
		boolean isValidToken=false;
		HttpSession session=null;
		RequestDispatcher dispatcher=null;
		session=httpRequest.getSession(false);
		formTokenValue=httpRequest.getParameter("cToken");
		sessionTokenValue=(String)session.getAttribute("sToken");
		Cookie[] cookies=httpRequest.getCookies();
		tokenCookie=SecurityService.getCookie(cookies,"requestToken");
		cookieTokenValue=tokenCookie.getValue();
		if(httpRequest.getMethod().equalsIgnoreCase("GET"))
		{
			isValidToken=SecurityService.validateToken(sessionTokenValue,cookieTokenValue);
		}
		else
		{
			isValidToken=SecurityService.validateToken(formTokenValue, cookieTokenValue, sessionTokenValue);
			
		}
		if(isValidToken==true)
		{
			formTokenValue=SecurityService.getToken();
			httpRequest.setAttribute("cToken",formTokenValue);
			session.setAttribute("sToken",formTokenValue);
			tokenCookie.setValue(SecurityService.getCookieToken(formTokenValue));
			httpResponse.addCookie(tokenCookie);
			chain.doFilter(new XssFilter(httpRequest),httpResponse);
		}		
	}

}
