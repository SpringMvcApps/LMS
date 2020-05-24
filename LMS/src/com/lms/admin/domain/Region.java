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
@Table(name="region_master_ut")
public class Region {
	
	@Id
	@Column(name="region_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="regionGenerator")
	@SequenceGenerator(name="regionGenerator",sequenceName="region_master_ut_region_id_seq",allocationSize=1)
	private int regionId;
	
	@Column(name="region_name")
	private String regionName;
	
	@ManyToOne(targetEntity=AddressStatus.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private AddressStatus status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=State.class,fetch=FetchType.LAZY)
	@JoinColumn(name="state_id",referencedColumnName="state_id")
	private State state;
	
    @ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Region()
	{
		
	}
}
