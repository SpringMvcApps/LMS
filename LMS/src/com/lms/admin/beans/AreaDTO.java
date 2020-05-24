package com.lms.admin.beans;

import java.io.Serializable;

public class AreaDTO implements Serializable{

	private int areaId;
	private String areaName;
	private int status;
	private int regionId;
	private String regionName;
	private int userId;
	
	
	public int getAreaId() {
		return areaId;
	}


	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getRegionId() {
		return regionId;
	}


	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}


	public String getRegionName() {
		return regionName;
	}


	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public AreaDTO()
	{
		
	}
}
