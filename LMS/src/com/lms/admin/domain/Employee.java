package com.lms.admin.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lms.commons.domain.Role;
import com.lms.commons.domain.Status;
import com.lms.commons.domain.User;

@Entity
@Table(name="emoployee_master_ut")
public class Employee {

	
	@Id
	@Column(name="employee_id")
	private int empId;
	
	@Column(name="first_name")
	private String empName;
	
	@Column(name="last_name")
	private String empLastName;
	
	@Column(name="gender")
	private String gender;
	
	@ManyToOne(targetEntity=City.class,fetch=FetchType.EAGER)
	@JoinColumn(name="city_id",referencedColumnName="city_id")
	private City cityId;
	
	@ManyToOne(targetEntity=State.class,fetch=FetchType.EAGER)
	@JoinColumn(name="state_id",referencedColumnName="state_id")
	private State stateId;
	
	@ManyToOne(targetEntity=Country.class,fetch=FetchType.EAGER)
	@JoinColumn(name="country_id",referencedColumnName="country_id")
	private Country countryId;
	
	@Column(name="mobile_number")
	private String mobileNo;
	
	@Column(name="email_address")
	private String email;
	
	@ManyToOne(targetEntity=Status.class,fetch=FetchType.EAGER)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private Status status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="admin_user_id",referencedColumnName="user_id")
	private User adminId;
	
	@OneToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User userId;
	
	@ManyToOne(targetEntity=Role.class,fetch=FetchType.LAZY)
	@JoinColumn(name="role_id",referencedColumnName="role_id")
	private Role roleId;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public City getCityId() {
		return cityId;
	}

	public void setCityId(City cityId) {
		this.cityId = cityId;
	}

	public State getStateId() {
		return stateId;
	}

	public void setStateId(State stateId) {
		this.stateId = stateId;
	}

	public Country getCountryId() {
		return countryId;
	}

	public void setCountryId(Country countryId) {
		this.countryId = countryId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public User getAdminId() {
		return adminId;
	}

	public void setAdminId(User adminId) {
		this.adminId = adminId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	
	public Employee()
	{
		
	}
	
}
