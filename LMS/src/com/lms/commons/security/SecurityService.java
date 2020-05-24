package com.lms.commons.security;

import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import com.lms.appException.AppSecurityException;
import com.lms.commons.security.dao.SecurityAuthenticator;


public class SecurityService {
	
	private static final Pattern pattern=Pattern.compile("<script.*>|<script>.*</script>|alert\\(.*\\)|(on.*=)+.*\\(.*\\)|href=.*",Pattern.CASE_INSENSITIVE);
	public static boolean isAuthorisedRequest(String url,int roleId)
	{
		boolean isAuthorised=false;
		isAuthorised=SecurityAuthenticator.hasAccess(url,roleId);
		return isAuthorised;
	}
	public static Cookie getCookie(Cookie[] cookies,String cookieName)
	{
		Cookie requestCookie=null;
		for(Cookie cookie:cookies)
		{
			if(cookie.getName().equalsIgnoreCase(cookieName))
			{
				requestCookie=cookie;
				break;
				
			}
		}
		return requestCookie;
		
	}
    public static String getToken()
    {
    	StringBuilder token=new StringBuilder();
    	String tokenSample="ahrdlfoJSjKKbjLHm1934sdghbjk%4734hshBSskspomfghcx55g253gf5d";
    	Random random=new Random();
    	for(int counter=1;counter<=10;counter++)
    	{
    		int charIndex=random.nextInt((tokenSample.length()-1));
    		token.append(tokenSample.charAt(charIndex));
    	}
    	
    	return token.toString();
    }
    public static String getCookieToken(String formTokenValue)
    {
    	int cookieTokenValue=0;
    	cookieTokenValue=formTokenValue.hashCode();
    	return Integer.toString(cookieTokenValue);
    }
    public static boolean validateToken(String form,String cookie)
    {
    	boolean isValid=false;
    	int hash=0;
    	try
    	{
    	hash=form.hashCode();
    	if(hash==(Integer.parseInt(cookie)))
    	{
    		isValid=true;
    	}
    	else
    	{
    		throw new AppSecurityException();
    	}
    	}
    	catch(Exception ex)
    	{
    		throw new AppSecurityException();
    	}
     	return isValid;
    }
    public static boolean validateToken(String form,String cookie,String session)
    {
    	boolean isValid=false;
    	try
    	{
    	if(form.equals(session))
    	{
    		int hash=form.hashCode();
    		if(hash==(Integer.parseInt(cookie)))
    		{
    			isValid=true;
    		}
    		else
        	{
        		throw new AppSecurityException();
        	}
    	}
    	else
    	{
    		throw new AppSecurityException();
    	}
    	}
    	catch(Exception ex)
    	{
    		throw new AppSecurityException();
    	}
    	return isValid;
    }
    public static void validateInput(String input)
    {
    	boolean isXss=false;
    	if(input!=null)
    	{
    		isXss=pattern.matcher(input).find();
    		
    	}
    	if(isXss)
    	{
    		System.out.println("XSS");
    		throw new AppSecurityException();
    	}
    	
    }
}
