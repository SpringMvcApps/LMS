package com.lms.admin.beans;

import java.io.Serializable;
import java.util.Date;

public class StateBO implements Serializable {
	private int stateId;
	private String stateName;
	private int countryId;
	private int status;
	private int userId;
	private String countryName;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public StateBO()
	{
		
	}
	public StateBO(int stateId,String stateName,int countryId,int status,int userId)
	{
		this.stateId=stateId;
		this.stateName=stateName;
		this.countryId=countryId;
		this.status=status;
		this.userId=userId;
	}
	public StateBO(String stateName,int countryId,int status,int userId)
	{

		this.stateName=stateName;
		this.countryId=countryId;
		this.status=status;
		this.userId=userId;
	}
	
	
}
