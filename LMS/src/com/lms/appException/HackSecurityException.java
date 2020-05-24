package com.lms.appException;

public class HackSecurityException extends ApplicationException {
	private static String message="Request is not valid, unable to complete.";
	public HackSecurityException()
	{
		super(message);
	}

}
