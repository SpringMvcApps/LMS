package com.lms.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.appException.AppSecurityException;
import com.lms.commons.security.SecurityService;
import com.lms.filter.XssFilter;
import com.lms.logger.ApplicationExceptionLogger;

public class Authenticator extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		HttpSession session=null;
		RequestDispatcher dispatcher=null;
		AuthService service=null;
		String [] sessionVariables;
		String cookieTokenValue=null;
		Cookie tokenCookie=null;
		String formTokenValue=null;
		ApplicationExceptionLogger logger;
		String targetPage=null;

	        Cookie[] cookies= request.getCookies();
	        tokenCookie=SecurityService.getCookie(cookies,"requestToken");
	        cookieTokenValue=tokenCookie.getValue();
	        formTokenValue=request.getParameter("cToken");
	        if(SecurityService.validateToken(formTokenValue,cookieTokenValue))
	        {
			targetPage=request.getParameter("targetUrl");
			int userId=Integer.parseInt(request.getParameter("username"));
			String password=request.getParameter("password");
			SecurityService.validateInput(password);
			service=new AuthService();
			sessionVariables=service.getSessionVariables(userId, password);
			formTokenValue=SecurityService.getToken();
			request.setAttribute("cToken",formTokenValue);
			tokenCookie.setValue(SecurityService.getCookieToken(formTokenValue));
			response.addCookie(tokenCookie);
			if(sessionVariables!=null)
			{
				session=request.getSession(true);
				session.setAttribute("userId",userId);
				session.setAttribute("firstName", sessionVariables[0]);
				session.setAttribute("lastName",sessionVariables[1]);
				session.setAttribute("roleId",Integer.parseInt(sessionVariables[2]));
				session.setAttribute("roleName",sessionVariables[3]);
				session.setAttribute("userIp",request.getRemoteAddr());
				session.setAttribute("sToken",formTokenValue);
				if(targetPage==null)
				{
				response.sendRedirect(request.getContextPath()+"/Dashboard.htm");
				}
				else
				{
					response.sendRedirect(targetPage);
				}
				
			}
			else{
				
				dispatcher=request.getRequestDispatcher("/Login.htm");
				request.setAttribute("authErr","Username or Password is incorrect");
				dispatcher.forward(request,response);
			
				
			}
	        }
	        else
	        {
	        	throw new AppSecurityException();
	        }
		//}
//		catch(Exception srvEx)
//		{
//			int errorId=0;
//			logger=new ApplicationExceptionLogger();
//			errorId=logger.extractExceptionLogInfo(srvEx);
//			dispatcher=request.getRequestDispatcher("/Error.jsp");
//			request.setAttribute("errorId", errorId);
//			request.setAttribute("error","Error While Authentication");
//			dispatcher.forward(request,response);
//		}
		

	}

}
