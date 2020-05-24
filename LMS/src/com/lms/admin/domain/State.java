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
@Table(name="state_master_ut")
public class State {

	@Id
	@Column(name="state_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="stateId_generator")
	@SequenceGenerator(name="stateId_generator",sequenceName="state_master_ut_state_id_seq",allocationSize=1)
	private int stateId;
	
	@Column(name="state_name")
	private String stateName;
	
	@ManyToOne(targetEntity=Country.class,fetch=FetchType.LAZY)
	@JoinColumn(name="country_id",referencedColumnName="country_id")
	private Country country;
	
	@ManyToOne(targetEntity=AddressStatus.class,fetch=FetchType.LAZY)
	@JoinColumn(name="status",referencedColumnName="status_id")
	private AddressStatus status;
	
	@Column(name="last_modified")
	private Date lastModified;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private User user;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public State()
	{
		
	}
	public State(String stateName,Country country,AddressStatus status,Date lastModified,User user)
	{
		this.stateName=stateName;
		this.country=country;
		this.status=status;
		this.user=user;
		
	}
	
}
