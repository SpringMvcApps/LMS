package com.lms.commons.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_master_ut")
public class Status {
	@Id
	@Column(name="status_id")
	private int statusId;
	@Column(name="status_value")
	private String statusValue;
	@Column(name="last_modified")
	private Date lastModified;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Status()
	{
		
	}
	
	

}
