package com.lms.admin.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.lms.commons.domain.AddressStatus;
import com.lms.commons.domain.Status;
import com.lms.commons.domain.User;

@Entity
@Table(name="country_master_ut")
public class Country {
	
@Id
@Column(name="country_id")
//@GenericGenerator(strategy="sequence",name="country_master_ut_country_id_seq")
//@GeneratedValue(generator="country_master_ut_country_id_seq")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryId_generator")
@SequenceGenerator(name="countryId_generator", sequenceName = "country_master_ut_country_id_seq",allocationSize=1)
private int countryId;

@Column(name="country_name")
private String countryName;

@ManyToOne(targetEntity=AddressStatus.class,fetch=FetchType.LAZY)//no cascade as delete operation will delete status record
@JoinColumn(name="status",referencedColumnName="status_id")
private AddressStatus status;

@Column(name="last_modified")
private Date lastModified;

@Column(name="country_code")
private String countryCode;

@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
@JoinColumn(name="user_id",referencedColumnName="user_id")
private User userId;

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
public AddressStatus getStatus() {
	return status;
}
public void setStatus(AddressStatus status) {
	this.status = status;
}
public Date getLastModified() {
	return lastModified;
}
public void setLastModified(Date lastModified) {
	this.lastModified = lastModified;
}
public String getCountryCode() {
	return countryCode;
}
public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}
public User getUserId() {
	return userId;
}
public void setUserId(User userId) {
	this.userId = userId;
}

public Country()
{
	
}
public Country(String countryName,AddressStatus status,Date lastModfied,String countryCode,User userId)
{
	this.countryName=countryName;
	this.status=status;
	this.lastModified=lastModfied;
	this.countryCode=countryCode;
	this.userId=userId;
}
}
