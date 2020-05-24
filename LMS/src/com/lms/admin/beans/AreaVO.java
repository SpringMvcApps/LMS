package com.lms.admin.beans;

import java.io.Serializable;

public class AreaVO implements Serializable {
private String areaId;
private String areaName;
private String status;
private String regionId;
public String getAreaId() {
	return areaId;
}
public void setAreaId(String areaId) {
	this.areaId = areaId;
}
public String getAreaName() {
	return areaName;
}
public void setAreaName(String areaName) {
	this.areaName = areaName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getRegionId() {
	return regionId;
}
public void setRegionId(String regionId) {
	this.regionId = regionId;
}

public AreaVO()
{
	
}

}
