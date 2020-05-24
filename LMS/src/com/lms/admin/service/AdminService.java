package com.lms.admin.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lms.admin.beans.AreaBO;
import com.lms.admin.beans.AreaDTO;
import com.lms.admin.beans.CityBO;
import com.lms.admin.beans.CityDTO;
import com.lms.admin.beans.CountryBO;
import com.lms.admin.beans.CountryDTO;
import com.lms.admin.beans.EmployeeBO;
import com.lms.admin.beans.EmployeeDTO;
import com.lms.admin.beans.LmsMenuBO;
import com.lms.admin.beans.LmsMenuDTO;
import com.lms.admin.beans.RegionBO;
import com.lms.admin.beans.RegionDTO;
import com.lms.admin.beans.RoleBO;
import com.lms.admin.beans.RoleDTO;
import com.lms.admin.beans.StateBO;
import com.lms.admin.beans.StateDTO;
import com.lms.admin.dao.AdminDAO;

@Service
public class AdminService {
	
	@Inject
	private AdminDAO dao;
	
	public boolean addCountry(CountryDTO dto)
	{
		boolean flag=false;
CountryBO bo=new CountryBO(dto.getCountryName(), dto.getCountryCode(), dto.getStatus(),dto.getUserId());
		flag=dao.insertCountry(bo);
		return true;
	}
	
	public List<CountryDTO> fetchCountry()
	{
		List<CountryDTO> dtoList=null;
		List<CountryBO> boList=dao.getCountry();
		
		if(boList!=null)
		{
			dtoList=new ArrayList<CountryDTO>();
			for(CountryBO bo:boList)
			{
				CountryDTO dto=new CountryDTO();
				dto.setCountryId(bo.getCountryId());
				dto.setCountryName(bo.getCountryName());
				dto.setCountryCode(bo.getCountryCode());
				dto.setStatus(bo.getStatus());
				dtoList.add(dto);
				
			}
		
		}
		return dtoList;
		
	}
	
	public boolean deleteSelectedCountry(String countryId)
	{
		boolean flag=false;
		flag=dao.deleteCountry(Integer.parseInt(countryId));
		return flag;
		
	}
	public boolean updateSelectedCountry(CountryDTO dto)
	{
		boolean flag=false;
		CountryBO bo=new CountryBO();
		bo.setCountryId(dto.getCountryId());
		bo.setCountryName(dto.getCountryName());
		bo.setCountryCode(dto.getCountryCode());
		bo.setStatus(dto.getStatus());
		bo.setUserId(dto.getUserId());
		flag=dao.updateCountry(bo);
		
		return flag;
		
	}
	public List<CountryDTO> fetchActiveCountries()
	{
		List<CountryDTO> dtoList=null;
		List<CountryBO> boList=null;
		
		boList=dao.getActiveCountries();
		if(boList!=null)
		{
			dtoList=new ArrayList<CountryDTO>();
			for(CountryBO bo:boList)
			{
				CountryDTO dto=new CountryDTO();
				dto.setCountryId(bo.getCountryId());
				dto.setCountryName(bo.getCountryName());
				dtoList.add(dto);
			}
			
		}
		return dtoList;
		
	}
	
	public boolean addState(StateDTO dto)
	{
		boolean flag=false;
		StateBO bo=new StateBO();
		bo.setStateName(dto.getStateName());
		bo.setCountryId(dto.getCountryId());
		bo.setStatus(dto.getStatus());
		bo.setUserId(dto.getUserId());
		flag=dao.insertState(bo);
		return flag;
	}
	public boolean updateSelectedState(StateDTO dto)
	{
		boolean flag=false;
		StateBO bo=new StateBO();
		bo.setStateId(dto.getStateId());
		bo.setStateName(dto.getStateName());
		bo.setCountryId(dto.getCountryId());
		bo.setStatus(dto.getStatus());
		bo.setUserId(dto.getUserId());
		flag=dao.updateState(bo);
		return flag;
		
	}
	public boolean deleteSelectedState(String stateId)
	{
		boolean flag=false;
		flag=dao.deleteState(Integer.parseInt(stateId));
		return flag;
	}
	public List<StateDTO> fetchStates()
	{
		List<StateDTO> dtoList=null;
		List<StateBO> boList=dao.getStates();
		if(boList!=null)
		{
			dtoList=new ArrayList<StateDTO>();
			for(StateBO bo:boList)
			{
				StateDTO dto=new StateDTO();
				dto.setStateId(bo.getStateId());
				dto.setStateName(bo.getStateName());
				dto.setCountryId(bo.getCountryId());
				dto.setCountryName(bo.getCountryName());
				dto.setStatus(bo.getStatus());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
	
	public List<StateDTO> getActiveStates()
	{
		List<StateDTO> dtoList=null;
		List<StateBO> boList=dao.fetchActiveStates();
		if(boList!=null)
		{
			dtoList=new ArrayList<StateDTO>();
			for(StateBO bo:boList)
			{
				StateDTO dto=new StateDTO();
				dto.setStateId(bo.getStateId());
				dto.setStateName(bo.getStateName());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
public boolean addRegionRecord(RegionDTO dto)
{
	boolean flag=false;
	RegionBO bo=new RegionBO();
	bo.setRegionName(dto.getRegionName());
	bo.setStatus(dto.getStatus());
	bo.setStateId(dto.getStateId());
	bo.setUserId(dto.getUserId());
	flag=dao.insertRegion(bo);
	return flag;
	
}
public List<RegionDTO> getRegions()
{
	List<RegionDTO> dtoList=null;
	List<RegionBO> boList=dao.fetchRegions();
	if(boList!=null)
	{
		dtoList=new ArrayList<RegionDTO>();
		for(RegionBO bo:boList)
		{
			RegionDTO dto=new RegionDTO();
			dto.setRegionId(bo.getRegionId());
			dto.setRegionName(bo.getRegionName());
			dto.setStateId(bo.getStateId());
			dto.setStateName(bo.getStateName());
			dto.setStatus(bo.getStatus());
			dtoList.add(dto);
		}
	}
	
      return dtoList;
}
public List<RegionDTO> getActiveRegions()
{
	List<RegionDTO> dtoList=null;
	List<RegionBO> boList=dao.fetchActiveRegions();
	if(boList!=null)
	{
		dtoList=new ArrayList<RegionDTO>();
		for(RegionBO bo:boList)
		{
			RegionDTO dto=new RegionDTO();
			dto.setRegionId(bo.getRegionId());
			dto.setRegionName(bo.getRegionName());
			dtoList.add(dto);
		}
		
	}
	return dtoList;
}
public boolean addArea(AreaDTO dto)
{
	boolean flag=false;
	AreaBO bo=new AreaBO();
	bo.setAreaName(dto.getAreaName());
	bo.setStatus(dto.getStatus());
	bo.setRegionId(dto.getRegionId());
	bo.setUserId(dto.getUserId());
	flag=dao.insertArea(bo);
	return flag;
}
public boolean updateSelectedArea(AreaDTO dto)
{
	boolean flag=false;
	AreaBO bo=new AreaBO();
	bo.setAreaId(dto.getAreaId());
	bo.setAreaName(dto.getAreaName());
	bo.setStatus(dto.getStatus());
	bo.setRegionId(dto.getRegionId());
	bo.setUserId(dto.getUserId());
	flag=dao.updateArea(bo);
	return flag;
}
public boolean deleteSelectedArea(int areaId)
{
	boolean flag=false;
	flag=dao.deleteArea(areaId);
	return flag;
}
public List<AreaDTO> fetchAreas()
{
	List<AreaDTO> dtoList=null;
	List<AreaBO> boList=dao.getAreas();
	if(boList!=null)
	{
		dtoList=new ArrayList<AreaDTO>();
		for(AreaBO bo:boList)
		{
			AreaDTO dto=new AreaDTO();
			dto.setAreaId(bo.getAreaId());
			dto.setAreaName(bo.getAreaName());
			dto.setStatus(bo.getStatus());
			dto.setRegionId(bo.getRegionId());
			dto.setRegionName(bo.getRegionName());
			dtoList.add(dto);
			
		}
	}
	return dtoList;
}
public List<AreaDTO> fetchActiveAreas()
{
	List<AreaDTO> dtoList=null;
	List<AreaBO> boList=dao.getActiveAreas();
	if(boList!=null)
	{
		dtoList=new ArrayList<AreaDTO>();
		for(AreaBO bo:boList)
		{
			AreaDTO dto=new AreaDTO();
			dto.setAreaId(bo.getAreaId());
			dto.setAreaName(bo.getAreaName());
			dtoList.add(dto);
		}
	}
	return dtoList;
}

public List<RoleDTO> fetchActiveRoles(){
	List<RoleDTO> roleDTOList=null;
	List<RoleBO> roleBOList=dao.getActiveRoles();
	if(roleBOList!=null)
	{
		roleDTOList=new ArrayList<RoleDTO>();
		for(RoleBO bo:roleBOList)
		{
			RoleDTO dto=new RoleDTO();
			dto.setRoleId(bo.getRoleId());
			dto.setRoleName(bo.getRoleName());
			roleDTOList.add(dto);
		}
	}
	return roleDTOList;
}
public boolean addMenu(LmsMenuDTO dto)
{
	boolean flag=false;
	LmsMenuBO bo=new LmsMenuBO();
	bo.setPageName(dto.getPageName());
	bo.setPageUrl(dto.getPageUrl());
	bo.setRoleId(dto.getRoleId());
	bo.setStatus(dto.getStatus());
	bo.setUser(dto.getUser());
	flag=dao.insertMenuToRole(bo);
	return flag;
	
}
public boolean addCity(CityDTO dto)
{
	boolean flag=false;
	CityBO bo=new CityBO();
	bo.setCityName(dto.getCityName());
	bo.setStatus(dto.getStatus());
	bo.setAreaId(dto.getAreaId());
	bo.setUserId(dto.getUserId());
	flag=dao.insertCity(bo);
	return flag;
}
public List<CityDTO> fetchCities()
{
	List<CityDTO> cityDTOList=null;
	List<CityBO> cityBOList=dao.getCities();
	if(cityBOList!=null)
	{
		cityDTOList=new ArrayList<CityDTO>();
		for(CityBO bo:cityBOList)
		{
			CityDTO dto=new CityDTO();
			dto.setCityId(bo.getCityId());
			dto.setCityName(bo.getCityName());
			dto.setStatus(bo.getStatus());
			dto.setAreaId(bo.getAreaId());
			dto.setAreaName(bo.getAreaName());
			cityDTOList.add(dto);
		}
	}
	return cityDTOList;
}
public boolean addUserRole(RoleDTO dto)
{
	boolean flag=false;
	RoleBO bo=new RoleBO();
	bo.setRoleName(dto.getRoleName());
	bo.setStatus(dto.getStatus());
	bo.setUser(dto.getUser());
	flag=dao.insertRole(bo);
	return flag;
	
}
public List<RoleDTO> fetchRoles()
{
	List<RoleDTO> roleDTOList=null;
	List<RoleBO> roleBOList=dao.getRoles();
	if(roleBOList!=null)
	{
		roleDTOList=new ArrayList<RoleDTO>();
		for(RoleBO bo:roleBOList)
		{
			RoleDTO dto=new RoleDTO();
			dto.setRoleId(bo.getRoleId());
			dto.setRoleName(bo.getRoleName());
			dto.setStatus(bo.getStatus());
			roleDTOList.add(dto);
			
		}
	}
	return roleDTOList;
}

public int addEmployeeDetails(EmployeeDTO dto)
{
	int generatedId=0;
	EmployeeBO bo=new EmployeeBO();
	bo.setEmpName(dto.getEmpName());
	bo.setEmpLastName(dto.getEmpLastName());
	bo.setGender(dto.getGender());
	bo.setCity(dto.getCity());
	bo.setState(dto.getState());
	bo.setCountry(dto.getCountry());
	bo.setMobileNo(dto.getMobileNo());
	bo.setEmail(dto.getEmail());
	bo.setStatus(dto.getStatus());
	bo.setAdminId(dto.getAdminId());
	bo.setRoleId(dto.getRoleId());
	bo.setAreaId(dto.getAreaId());
	bo.setRegionId(dto.getRegionId());
	generatedId=dao.insertEmployee(bo);
	return generatedId;
	
}
}
