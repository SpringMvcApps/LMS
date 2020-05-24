package com.lms.admin.beans;

import java.io.Serializable;

public class RoleDTO implements Serializable {
	private int roleId;
	private String roleName;
	private int status;
	private int user;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public RoleDTO()
	{
		
	}
	
}