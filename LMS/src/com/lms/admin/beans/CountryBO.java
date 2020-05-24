package com.lms.admin.beans;

import java.io.Serializable;

public class CountryBO implements Serializable {
	private int countryId;
	private String countryName;
	private String countryCode;
	private int status;
	private int userId;
	
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	  public void setUserId(int userId)
	  {
		  this.userId=userId;
	  }
	  public int getUserId()
	  {
		  return userId;
	  }
	public CountryBO()
	{
		
	}
	public CountryBO(String countryName,String countryCode,int status,int userId)
	{
		this.countryCode=countryCode;
	
		this.countryName=countryName;
		this.status=status;
		this.userId=userId;
	}


}
