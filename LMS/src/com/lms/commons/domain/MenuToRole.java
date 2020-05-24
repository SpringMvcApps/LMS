package com.lms.commons.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="menu_to_role")
public class MenuToRole {

@Id
@Column(name="tab_id")
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mtorSeqGen")
@SequenceGenerator(name="mtorSeqGen",sequenceName="menu_to_role_tab_id_seq",allocationSize=1)
private int tabId;
@ManyToOne(targetEntity=LmsMenu.class)
@JoinColumn(name="menu_id",referencedColumnName="menu_id")
private LmsMenu menu;
@ManyToOne(targetEntity=Role.class)
@JoinColumn(name="role_id",referencedColumnName="role_id")
private Role role;
public LmsMenu getMenu() {
	return menu;
}
public void setMenu(LmsMenu menu) {
	this.menu = menu;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}

public int tabId() {
	return tabId;
}
public void setTabId(int tabId) {
	this.tabId = tabId;
}

}
