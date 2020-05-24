package com.lms.commons.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="address_status_master_ut")
public class AddressStatus {
	
	@Id
	@Column(name="status_id")
	private int statusId;
	
	@Column(name="status_value")
	private String statusValue;
	
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;

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



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public AddressStatus()
	{
		
	}
	

}
