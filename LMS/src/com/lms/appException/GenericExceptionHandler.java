package com.lms.appException;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenericExceptionHandler extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		doPost(request,response);
	}
  public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
  {
	 String message=null;
	 RequestDispatcher dispatcher=request.getRequestDispatcher("/PageNotFound.jsp");
	 dispatcher.forward(request,response);
  }
}
