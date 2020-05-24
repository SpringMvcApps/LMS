package com.lms.appException;

public class AppSecurityException extends ApplicationException {
	
private static String message="Request is not valid, unable to complete.";
	public AppSecurityException()
		{
			super(message);
		}
	}

