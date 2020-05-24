package com.lms.admin.beans;

import java.io.Serializable;

public class LmsMenuBean implements Serializable {
	
	public LmsMenuBean()
	{
		
	}
	private String pageName;
	private String pageUrl;
	private int status;
	
	public void setPageName(String pageName)
	{
		this.pageName=pageName;

	}
	public String getPageName()
	{
		return pageName;
	}
	public void setPageUrl(String pageUrl)
	{
		this.pageUrl=pageUrl;
	}
	public String getPageUrl()
	{
		return pageUrl;
	}
	public void setStatus(int status)
	{
		this.status=status;
	}
	public int getStatus()
	{
		return status;
	}

}
