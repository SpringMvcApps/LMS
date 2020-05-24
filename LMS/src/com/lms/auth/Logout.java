package com.lms.auth;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.logger.ApplicationExceptionLogger;

public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		HttpSession session=null;
		ApplicationExceptionLogger logger=null;
		Cookie tokenCookie=null;
	    session=req.getSession(false);
	    if(session!=null)
	    {

			System.out.println("exists for invalidate");
			System.out.println(session.getId());
			session.invalidate();	
			if(req.getAttribute("exceptionMsg")!=null)
			{
				RequestDispatcher dispatcher=req.getRequestDispatcher("/Error.jsp");
				req.setAttribute("exception",req.getAttribute("exceptionMsg"));
				dispatcher.forward(req,res);
			}
			else
			{
			res.sendRedirect("/LMS/");
			}
	    }
		
//		catch(Exception ex)
//		{
//		logger=new ApplicationExceptionLogger();
//		int errId=logger.extractExceptionLogInfo(ex);
//		RequestDispatcher rd=req.getRequestDispatcher("/Error.jsp");
//		req.setAttribute("errorId",errId);
//		req.setAttribute("error","Error occured while processing request");
//		rd.forward(req,res);
//		}

	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		doGet(request,response);
	}

}
