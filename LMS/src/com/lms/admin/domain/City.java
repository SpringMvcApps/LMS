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
import com.lms.commons.domain.Status;
import com.lms.commons.domain.User;

@Entity
@Table(name="city_master_ut")
public class City {
	
	@Id
	@Column(name="city_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cityIdGen")
	@SequenceGenerator(name="cityIdGen",sequenceName="city_master_ut_city_id_seq",allocationSize=1)
	private int cityId;
	
	@Column(name="city_name")
	private String cityName;
	
	@ManyToOne(targetEntity=AddressStatus.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private AddressStatus status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=Area.class,fetch=FetchType.LAZY)
	@JoinColumn(name="area_id",referencedColumnName="area_id")
	private Area area;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public City()
	{
		
	}

}
