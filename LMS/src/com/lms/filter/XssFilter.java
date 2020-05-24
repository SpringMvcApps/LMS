package com.lms.filter;

import java.util.Enumeration;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.lms.appException.AppSecurityException;
import com.lms.commons.security.SecurityService;

public class XssFilter extends HttpServletRequestWrapper {
   // static Pattern pattern=Pattern.compile("<script>.*</script>|alert.*|alert[\\Wa-zA-Z0-9]*|alert()|(<script>.*)*");
   
	public XssFilter(HttpServletRequest request)
	{
		super(request);
	}
	public String getParameter(String name)
	{
		String value=null;
		value=super.getParameter(name);
		SecurityService.validateInput(value);
		return value;
	}
	
	public void setAttribute(String key,Object value)
	{
		if(value instanceof String)
		{
		SecurityService.validateInput((String)value);
		}
		super.setAttribute(key,value);
		
		
	}
	public Object getAttribute(String key)
	{
		Object attribute=super.getAttribute(key);
		return attribute;
	}
	public Enumeration<String> getParameterNames()
	{
	 Enumeration<String> e=super.getParameterNames();
		return e;
	}
	public String[] getParameterValues(String name)
	{
		String[] values=super.getParameterValues(name);
		for(String input:values)
		{
			System.out.println(input+"for parse");
			SecurityService.validateInput(input);
			System.out.println(input);
		}
		return values;
	}
//	public RequestDispatcher getRequestDispatcher(String path)
//	{
//		return super.getRequestDispatcher(path);
//	}
// public void validateInput(String input)
// {
//	 boolean isXss=false;
//	 if(input!=null)
//	 {
//		 isXss=pattern.matcher(input).matches();
//	 }
//	 if(isXss==true)
//	 {
//		throw new AppSecurityException(); 
//	 }
//	 
//	 
// }
}
