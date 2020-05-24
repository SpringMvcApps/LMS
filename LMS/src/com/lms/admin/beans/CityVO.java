package com.lms.admin.beans;

import java.io.Serializable;

public class CityVO implements Serializable {
	
	private int cityId;
	private String cityName;
	private int status;
	private int areaId;

	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}



	public CityVO()
	{
		
	}

}
