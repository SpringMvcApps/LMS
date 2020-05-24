package com.lms.commons.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="lms_menu")
public class LmsMenu {
	
	@Id
	@Column(name="menu_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="menuIdGen")
	@SequenceGenerator(name="menuIdGen",sequenceName="lms_menu_menu_id_seq",allocationSize=1)
	private int menuId;
	
	@Column(name="page_name")
	private String pageName;
	
	@Column(name="page_url")
	private String pageUrl;
	
	@ManyToOne(targetEntity=Status.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private Status status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;

	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name="menu_to_role",joinColumns=@JoinColumn(name="menu_id",referencedColumnName="menu_id"),
	inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"))
	private List<Role> roles;
	
	@OneToMany(targetEntity=MenuToRole.class,cascade=CascadeType.ALL,mappedBy="menu")
	private List<MenuToRole> menuToRole;
	
	public List<MenuToRole> getMenuToRole() {
		return menuToRole;
	}

	public void setMenuToRole(List<MenuToRole> menuToRole) {
		this.menuToRole = menuToRole;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoles(List<Role> roles)
	{
		this.roles=roles;
	}
	
	public List<Role> getRoles()
	{
		return roles;
	}
	
	public int getMenuId() {
		return menuId;
		
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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
	public User getUser() {
		return user;
	}
	public void setUserId(User user) {
		this.user = user;
	}
	public LmsMenu()
	{
		
	}

	public LmsMenu(String pageName,String pageUrl,Status status,Date lastModified,User user,int roleId)
	{
		this.pageName=pageName;
		this.pageUrl=pageUrl;
		this.status=status;
		this.lastModified=lastModified;
		this.user=user;
	
		
	}
	
	
	
}
