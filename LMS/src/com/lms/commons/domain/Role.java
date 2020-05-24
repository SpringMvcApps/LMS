package com.lms.commons.domain;

import java.util.Date;

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

@Entity
@Table(name="role_master_ut")
public class Role {

	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="roleIdGen")
	@SequenceGenerator(name="roleIdGen",sequenceName="role_master_ut_role_id_seq",allocationSize=1)
	private int roleId;
	@Column(name="role_name")
	private String roleName;
	@Column(name="last_modified")
	private Date lastModified;
	@ManyToOne(targetEntity=Status.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private Status status;
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Role()
	{
		
	}
	
	public Role(String roleName,Date lastModified,Status status,User user)
	{
		this.roleName=roleName;
		this.status=status;
		this.lastModified=lastModified;
		this.user=user;
		
	}
}
