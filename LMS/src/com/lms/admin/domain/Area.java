package com.lms.admin.domain;

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

import com.lms.commons.domain.AddressStatus;
import com.lms.commons.domain.User;

@Entity
@Table(name="area_master_ut")
public class Area {

	@Id
	@Column(name="area_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="areaIdGen")
	@SequenceGenerator(name="areaIdGen",sequenceName="area_master_ut_area_id_seq",allocationSize=1)
	private int areaId;
	
	@Column(name="area_name")
	private String areaName;
	
	@ManyToOne(targetEntity=AddressStatus.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private AddressStatus status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=Region.class,fetch=FetchType.LAZY)
	@JoinColumn(name="region_id",referencedColumnName="region_id")
	private Region region;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;
	
	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Area()
	{
		
	}
}
