package com.lms.appException;

public class PageNotFoundException extends RuntimeException {
	public PageNotFoundException(String msg)
	{
		super(msg);
	}

}
