package com.lms.admin.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lms.admin.beans.AreaBO;
import com.lms.admin.beans.CityBO;
import com.lms.admin.beans.CountryBO;
import com.lms.admin.beans.EmployeeBO;
import com.lms.admin.beans.LmsMenuBO;
import com.lms.admin.beans.RegionBO;
import com.lms.admin.beans.RoleBO;
import com.lms.admin.beans.StateBO;
import com.lms.admin.domain.Area;
import com.lms.admin.domain.City;
import com.lms.admin.domain.Country;
import com.lms.admin.domain.Region;
import com.lms.admin.domain.State;
import com.lms.commons.domain.AddressStatus;
import com.lms.commons.domain.LmsMenu;
import com.lms.commons.domain.MenuToRole;
import com.lms.commons.domain.Role;
import com.lms.commons.domain.Status;
import com.lms.commons.domain.User;
import com.lms.utils.Persistor;


@Repository
public class AdminDAO {
	
	public Persistor getPersistor()
	{
		return new Persistor();
	}
	

public boolean insertCountry(CountryBO bo)
{
	Persistor dbHelper=null;
	boolean flag=false;
	dbHelper=getPersistor();
	Session connection=dbHelper.getConnection();
	Country country=new Country();
	country.setCountryName(bo.getCountryName());
	country.setCountryCode(bo.getCountryCode());
	AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
	country.setStatus(status);
	User user=connection.get(User.class,new Integer(bo.getUserId()));
	country.setUserId(user);
	country.setLastModified(new Date());
	flag=dbHelper.saveObject(country);
    dbHelper=null;
	return flag; 
	
}


public boolean deleteCountry(int countryId)
{
	boolean flag=false;
	Persistor dbHelper=null;
	dbHelper=getPersistor();
	Session connection=dbHelper.getConnection();
	Country country=connection.get(Country.class,new Integer(countryId));
	flag=dbHelper.deleteObject(country);
	dbHelper=null;
	return flag;
}

	public boolean updateCountry(CountryBO bo)
	{
		boolean flag=false;
		Persistor dbHelper=null;
		dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		int countryId=bo.getCountryId();
	    Country country=connection.get(Country.class,new Integer(countryId));
	    AddressStatus addStatus=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
	    User user=connection.get(User.class,new Integer(bo.getUserId()));
	    country.setCountryName(bo.getCountryName());
	    country.setCountryCode(bo.getCountryCode());
	    country.setLastModified(new Date());
	    country.setStatus(addStatus);
	    country.setUserId(user);
	    flag=dbHelper.updateObject(country);
	    
	    dbHelper=null;
		return flag;
	}
	
	public List<CountryBO> getCountry()
	{
		List<CountryBO> countryBOList=null;
		Persistor dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		Query query=connection.createQuery("select c from Country c");
		List<Country> countryList=query.list();
		
         if(countryList!=null)
         {
        	 countryBOList =new ArrayList<CountryBO>();
		for(Country country:countryList)
		{
			CountryBO countryBO=new CountryBO();
			countryBO.setCountryId(country.getCountryId());
			countryBO.setCountryCode(country.getCountryCode());
			countryBO.setCountryName(country.getCountryName());
			AddressStatus addStatus=country.getStatus();
			countryBO.setStatus(addStatus.getStatusId());
			countryBOList.add(countryBO);
			
		}
		
        }
         dbHelper.closeConnection();
         dbHelper=null;
		return countryBOList;
	}
	public List<CountryBO> getActiveCountries()
	{
		boolean flag=false;
		Persistor dbHelper=null;
		List<CountryBO> boList=null;
		dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		Query query=connection.createQuery("select c.countryId,c.countryName from Country c where c.status.statusId="+2);
		List<Object[]> countryList=query.list();
		if(countryList!=null)
		{
			boList=new ArrayList<CountryBO>();
		for(Object[] country:countryList )
		{
			CountryBO bo=new CountryBO();
			bo.setCountryId((Integer)country[0]);
			bo.setCountryName((String)country[1]);
			boList.add(bo);
		}
		}
		dbHelper.closeConnection();
		dbHelper=null;
		return boList;
	}
	
	public boolean insertState(StateBO bo)
	{
		Persistor dbHelper=null;
		dbHelper=getPersistor();
		boolean flag=false;
		Session connection=dbHelper.getConnection();
		Country country=connection.get(Country.class,new Integer(bo.getCountryId()));
		AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
		User user=connection.get(User.class,new Integer(bo.getUserId()));
		State state=new State();
		state.setStateName(bo.getStateName());
		state.setCountry(country);
		state.setStatus(status);
		state.setLastModified(new Date());
		state.setUser(user);
		flag=dbHelper.saveObject(state);
	    dbHelper=null;
		return flag;
		
	}
	public boolean updateState(StateBO bo)
	{
		Persistor dbHelper=null;
		boolean flag=false;
		dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		Country country=connection.get(Country.class,new Integer(bo.getCountryId()));
		AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
		User user=connection.get(User.class,new Integer(bo.getUserId()));
		State state=connection.get(State.class,new Integer(bo.getStateId()));
		state.setStateName(bo.getStateName());
		state.setCountry(country);
		state.setStatus(status);
		state.setLastModified(new Date());
		state.setUser(user);
		flag=dbHelper.updateObject(state);
		dbHelper=null;
		return flag;
	}
	public boolean deleteState(int stateId)
	{
		Persistor dbHelper=null;
		boolean flag=false;
		dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		
		State state=connection.get(State.class,new Integer(stateId));
		flag=dbHelper.deleteObject(state);
		dbHelper=null;
		return flag;
	}
	public List<StateBO> getStates()
	{
		Persistor dbHelper=null;
		List<StateBO> boList=null;
		dbHelper=getPersistor();
		Session connection=dbHelper.getConnection();
		Query query=connection.createQuery("select s from State s");
		List<State> stateList=query.list();
		if(stateList!=null)
		{
			boList=new ArrayList<StateBO>();
			for(State state:stateList)
			{
				AddressStatus status=state.getStatus();
				Country country=state.getCountry();
				StateBO bo=new StateBO();
				bo.setStateId(state.getStateId());
				bo.setStateName(state.getStateName());
				bo.setCountryId(country.getCountryId());
				bo.setCountryName(country.getCountryName());
				bo.setStatus(status.getStatusId());
			    boList.add(bo);
			}
			
		}
		dbHelper.closeConnection();
		dbHelper=null;
		return boList;
	}
	
	public List<StateBO> fetchActiveStates()
	{
		Persistor dbHelper=null;
		Session connection=null;
		List<StateBO> boList=null;
		dbHelper=getPersistor();
		connection=dbHelper.getConnection();
		Query query=connection.createQuery("select s.stateId,s.stateName from State s where s.status.statusId="+2);
		List<Object[]> state=query.list();
		if(state!=null)
		{
			boList=new ArrayList<StateBO>();
			for(Object[] oArr:state)
			{
				StateBO bo=new StateBO();
				bo.setStateId((Integer)oArr[0]);
				bo.setStateName((String)oArr[1]);
				boList.add(bo);
				
			}
		}
		dbHelper.closeConnection();
		dbHelper=null;
		return boList;
	}
	
	public boolean insertRegion(RegionBO bo)
	{
		boolean flag=false;
		Persistor dbHelper=null;
		Session connection=null;
		dbHelper=getPersistor();
		connection=dbHelper.getConnection();
		AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
		State state=connection.get(State.class,new Integer(bo.getStateId()));
		User user=connection.get(User.class,new Integer(bo.getUserId()));
		Region region =new Region();
		region.setRegionName(bo.getRegionName());
		region.setStatus(status);
		region.setLastModified(new Date());
		region.setState(state);
		region.setUser(user);
		flag=dbHelper.saveObject(region);
		dbHelper=null;
		return flag;
		
	}
  public List<RegionBO> fetchRegions()
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  List<RegionBO> boList=null;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Query query=connection.createQuery("select r from Region r");
	  List<Region> regionList=query.list();
	 
	  if(regionList!=null)
	  {
		  boList=new ArrayList<RegionBO>();
		  for(Region region:regionList)
		  {
			  RegionBO bo=new RegionBO();
			  bo.setRegionId(region.getRegionId());
			  bo.setRegionName(region.getRegionName());
			  AddressStatus status=region.getStatus();
			  State state=region.getState();
			  bo.setStatus(status.getStatusId());
			  bo.setStateId(state.getStateId());
			  bo.setStateName(state.getStateName());
			  boList.add(bo);
			
		  }
	  }
	  dbHelper.closeConnection();
	  dbHelper=null;
	  return boList;
			  
	  
  }
  public List<RegionBO> fetchActiveRegions()
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  List<RegionBO> boList=null;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Query query =connection.createQuery("select r.regionId,r.regionName from Region r where r.status.statusId="+2);
	  List<Object[]> activeRegions=query.list();
	  if(activeRegions!=null)
	  {
		  boList=new ArrayList<RegionBO>();
		  for(Object [] oArr:activeRegions)
		  {
			  RegionBO bo=new RegionBO();
			  bo.setRegionId((Integer)oArr[0]);
			  bo.setRegionName((String)oArr[1]);
			  boList.add(bo);
		  }
	  }
	  dbHelper.closeConnection();
	  dbHelper=null;
	  return boList;
  }
  public boolean insertArea(AreaBO bo)
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  boolean flag=false;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
	  Region region=connection.get(Region.class,new Integer(bo.getRegionId()));
	  User user=connection.get(User.class,new Integer(bo.getUserId()));
	  Area area=new Area();
	  area.setAreaName(bo.getAreaName());
	  area.setStatus(status);
	  area.setLastModified(new Date());
	  area.setRegion(region);
	  area.setUser(user);
	  flag=dbHelper.saveObject(area);
	  dbHelper=null;
	  return flag;
	  
  }
  
  public boolean updateArea(AreaBO bo)
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  boolean flag=false;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Area area=connection.get(Area.class,new Integer(bo.getAreaId()));
	  AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
	  Region region=connection.get(Region.class,new Integer(bo.getRegionId()));
	  User user=connection.get(User.class,new Integer(bo.getUserId()));
	  area.setAreaName(bo.getAreaName());
	  area.setStatus(status);
	  area.setLastModified(new Date());
	  area.setRegion(region);
	  area.setUser(user);
	  flag=dbHelper.updateObject(area);
	  dbHelper=null;
	  return flag;
  }
  public boolean deleteArea(int areaId)
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  boolean flag=false;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Area area=connection.get(Area.class,new Integer(areaId));
	  flag=dbHelper.deleteObject(area);
	  dbHelper=null;
	  return flag;
  }
  public List<AreaBO> getAreas()
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  List<AreaBO> boList=null;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Query query=connection.createQuery("select a from Area a");
	  List<Area> areaList=query.list();
	  if(areaList!=null)
	  {
		  boList=new ArrayList<AreaBO>();
		  for(Area area:areaList)
		  {
			  AreaBO bo=new AreaBO();
			  bo.setAreaId(area.getAreaId());
			  bo.setAreaName(area.getAreaName());
			  AddressStatus status=area.getStatus();
			  Region region=area.getRegion();
			  bo.setStatus(status.getStatusId());
			  bo.setRegionId(region.getRegionId());
			  bo.setRegionName(region.getRegionName());
			  boList.add(bo);
		  }
	  }
	  dbHelper.closeConnection();
	  dbHelper=null;
	  return boList;
  }
  public List<AreaBO> getActiveAreas()
  {
	  Persistor dbHelper=null;
	  Session connection=null;
	  List<AreaBO> boList=null;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Query query=connection.createQuery("select a.areaId,a.areaName from Area a where a.status.statusId=?");
	  query.setParameter(0,new Integer(2));
	  List<Object[]> objArrList=query.list();
	  if(objArrList!=null)
	  {
		  boList=new ArrayList<AreaBO>();
		  for(Object[] objArr:objArrList)
		  {
			  AreaBO bo=new AreaBO();
			  bo.setAreaId((Integer)objArr[0]);
			  bo.setAreaName((String)objArr[1]);
			  boList.add(bo);
		  }
	  }
	  dbHelper.closeConnection();
	  dbHelper=null;
	  return boList;
  }
  
  public List<RoleBO> getActiveRoles()
  {
	  List<RoleBO> roleBOList=null;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  try{
	  connection=dbHelper.getConnection();
	  Query query=connection.createQuery("select r.roleId,r.roleName from Role r where r.status.statusId=?");
	  query.setParameter(0,new Integer(2));
	  List<Object[]> objArr=query.list();
	  if(objArr!=null)
	  {
		  roleBOList=new ArrayList<RoleBO>();
		  
		  for(Object[] arr:objArr)
		  {
			  RoleBO bo=new RoleBO();
			  bo.setRoleId((Integer)arr[0]);
			  bo.setRoleName((String)arr[1]);
			  roleBOList.add(bo);
			
		  }
	  }
	  }
	  catch(Exception ex)
	  {
		
		  throw ex;
	  }
	  finally
	  {
		  dbHelper.closeConnection();
		  dbHelper=null; 
	  }
	 
	  return roleBOList;
  }
  
  public boolean insertMenuToRole(LmsMenuBO bo)
  {
	  boolean flag=false;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  connection=dbHelper.getConnection();
	  Status status=connection.get(Status.class,new Integer(bo.getStatus()));
	  User user=connection.get(User.class,new Integer(bo.getUser()));
	  Role role=connection.get(Role.class,new Integer(bo.getRoleId()));
	  LmsMenu menu=new LmsMenu();
	  menu.setPageName(bo.getPageName());
	  menu.setPageUrl(bo.getPageUrl());
	  menu.setStatus(status);
	  menu.setUser(user);
	  menu.setLastModified(new Date());
	  MenuToRole mtor=new MenuToRole();
	  mtor.setMenu(menu);
	  mtor.setRole(role);
	  List<MenuToRole> mtoList=new ArrayList<MenuToRole>();
	  mtoList.add(mtor);
	  menu.setMenuToRole(mtoList);
	  flag=dbHelper.saveObject(menu);
	  dbHelper=null;
	  return flag;
  }
  public boolean insertCity(CityBO bo)
  {
	  boolean flag=false;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  try
	  {
	  connection=dbHelper.getConnection();
	  AddressStatus status=connection.get(AddressStatus.class,new Integer(bo.getStatus()));
	  Area area=connection.get(Area.class,new Integer(bo.getAreaId()));
	  User user=connection.get(User.class,new Integer(bo.getUserId()));
	  City city=new City();
	  city.setCityName(bo.getCityName());
	  city.setStatus(status);
	  city.setLastModified(new Date());
	  city.setArea(area);
	  city.setUser(user);
	  flag=dbHelper.saveObject(city);
	  }
	  catch(Exception ex)
	  {
		  throw ex;
	  }
	  finally
	  {
		  
		  dbHelper=null;
		  connection=null;
	  }
	  
	  return flag;
  }
  public List<CityBO> getCities()
  {
	  List<CityBO> cityBOList=null;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  try{
	  connection=dbHelper.getConnection();
	  Query query=connection.createQuery("select c from City c");
	  List<City> cityList=query.list();
	  if(cityList!=null)
	  {
		  cityBOList=new ArrayList<CityBO>();
		  for(City city:cityList)
		  {
			  CityBO bo=new CityBO();
			  bo.setCityId(city.getCityId());
			  bo.setCityName(city.getCityName());
			  int statusId=city.getStatus().getStatusId();
			  bo.setStatus(statusId);
			  int areaId=city.getArea().getAreaId();
			  bo.setAreaId(areaId);
			  bo.setAreaName(city.getArea().getAreaName());
			  cityBOList.add(bo);
		  }
	  }
	  }
	  catch(Exception ex)
	  {
		 throw ex; 
	  }
	  finally{
		  
		  dbHelper.closeConnection();
		  dbHelper=null;
		  connection=null;
	  }
	  return cityBOList;
	  
	  
  }
  public boolean insertRole(RoleBO bo)
  {
	  boolean flag=false;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  try{
	  connection=dbHelper.getConnection();
	  Status status=connection.get(Status.class,new Integer(bo.getStatus()));
	  User user=connection.get(User.class,new Integer(bo.getUser()));
	  Role role=new Role();
	  role.setRoleName(bo.getRoleName());
	  role.setStatus(status);
	  role.setLastModified(new Date());
	  role.setUser(user);
	  flag=dbHelper.saveObject(role);
	  }
	  catch(Exception ex)
	  {
		  throw ex;
	  }
	  finally{
		
		  dbHelper=null;
		  connection=null;
	  }
	  
	  return flag;
  }
  public List<RoleBO> getRoles()
  {
	  List<RoleBO> roleBOList=null;
	  Persistor dbHelper=null;
	  Session connection=null;
	  dbHelper=getPersistor();
	  try
	  {
		  connection=dbHelper.getConnection();
		  Query query=connection.createQuery("select r from Role r");
		  List<Role> roleList=query.list();
		  if(roleList!=null)
		  {
			  roleBOList=new ArrayList<RoleBO>();
			  for(Role role:roleList)
			  {
				  RoleBO bo=new RoleBO();
				  Status status=role.getStatus();
				  bo.setRoleId(role.getRoleId());
				  bo.setRoleName(role.getRoleName());
				  bo.setStatus(status.getStatusId());
				 roleBOList.add(bo);
				 
			  }
		  }
		  
	  }
	  catch(Exception ex)
	  {
		  throw ex;
	  }
	  finally
	  {
		  dbHelper.closeConnection();
		  dbHelper=null;
		  connection=null;
	  }
	  return roleBOList;
  }
  
  public int insertEmployee(EmployeeBO bo)
  {
	 int generatedId=0;
	 Persistor dbHelper=null;
	 try
	 {
		 Object[] inParams={bo.getEmpName(),bo.getEmpLastName(),bo.getGender(),
		 bo.getCity(),bo.getState(),bo.getCountry(),bo.getMobileNo(),
		 bo.getEmail(),bo.getStatus(),bo.getAdminId(),bo.getRoleId(),bo.getAreaId(),bo.getRegionId()};
		 dbHelper=getPersistor();
		 generatedId=dbHelper.runSpReturnInt("registerUser", inParams);
		
	 }
	 catch(Exception ex)
	 {
		 throw ex;
	 }
	 finally
	 {
		 dbHelper=null;
	 }
	 return generatedId;
  }
}
