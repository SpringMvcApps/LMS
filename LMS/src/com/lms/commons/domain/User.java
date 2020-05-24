package com.lms.commons.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_master_ut")
public class User {
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=Status.class)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private Status status;
	
	public void setUserId(int userId)
	{
		this.userId=userId;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserPassword(String userPassword)
	{
		this.userPassword=userPassword;
	}
	public String getUserPassword()
	{
		return userPassword;
	}
	
	public void setLastModified(Date lastModified)
	{
		this.lastModified=lastModified;
	}

	public Date getLastModified()
	{
		return lastModified;
	}
	public void setStatus(Status status)
	{
		this.status=status;
	}
	public Status getStatus()
	{
		return status;
	}
	public User()
	{
		
	}
	public User(String userPassword,Date lastModified,Status status)
	{
	
		this.userPassword=userPassword;
		this.lastModified=lastModified;
		this.status=status;
	}
}
