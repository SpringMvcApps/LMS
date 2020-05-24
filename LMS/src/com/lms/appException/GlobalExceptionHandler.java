package com.lms.appException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {
	
	public GlobalExceptionHandler()
	{
		System.out.println("GlobalExceptionHandler");
	}
	
	@ExceptionHandler(ApplicationException.class)
	public String handleAppException(HttpServletRequest request,Exception ex)
	{
	
		return "Error";
	}
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request,Exception ex)
	{
	    request.setAttribute("ExceptionMessage", "Exception from Advice");
		return "Error";
	}

}
