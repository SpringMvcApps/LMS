package com.lms.commons.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lms.admin.beans.AreaBO;
import com.lms.admin.beans.CityBO;
import com.lms.admin.beans.RegionBO;
import com.lms.admin.beans.StateBO;
import com.lms.utils.Persistor;

@Repository
public class UtilityDao {
	
	public Persistor getPersistor()
	{
		return new Persistor();
	}
//	public void closeDbConnection(Persistor dbHelper)
//	{
//		dbHelper.closeConnection();
//	}

	public List<StateBO> getCountryStateList(int countryId)
	{
		List<StateBO> stateBoList=null;
		Persistor dbHelper=null;
		Session connection=null;
		try
		{
			dbHelper=getPersistor();
			connection=dbHelper.getConnection();
			Query query=connection.createQuery("select s.stateId,s.stateName from State s where s.country.countryId="+countryId+" "+"and s.status.statusId="+2);
			List<Object[]> stateList=query.list();
			if(stateList!=null)
			{
				stateBoList=new ArrayList<StateBO>();
				for(Object[] oArr:stateList)
				{
					StateBO bo=new StateBO();
					bo.setStateId((Integer)oArr[0]);
					bo.setStateName((String)oArr[1]);
					stateBoList.add(bo);
					
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
			connection=null;
			dbHelper=null;
		}
		
		return stateBoList;
	}
	
	public List<RegionBO> getStatesRegionList(int stateId)
	{
		List<RegionBO> regionBoList=null;
		Persistor dbHelper=null;
		Session connection=null;
		try
		{
			dbHelper=getPersistor();
			connection=dbHelper.getConnection();
			Query query=connection.createQuery("select r.regionId,r.regionName from Region r where r.state.stateId="+stateId+" "+"and r.status.statusId="+2);
			List<Object[]> regionList=query.list();
			if(regionList!=null)
			{
				regionBoList=new ArrayList<RegionBO>();
				for(Object[] oArr:regionList)
				{
					RegionBO bo=new RegionBO();
					bo.setRegionId((Integer)oArr[0]);
					bo.setRegionName((String)oArr[1]);
					regionBoList.add(bo);
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
			connection=null;
			dbHelper=null;
		}
		return regionBoList;
	}
	
	public List<AreaBO> getRegionsAreaList(int regionId)
	{
		List<AreaBO> areaBoList=null;
		Persistor dbHelper=null;
		Session connection=null;
		try
		{
			dbHelper=getPersistor();
			connection=dbHelper.getConnection();
			Query query=connection.createQuery("select a.areaId,a.areaName from Area a where a.region.regionId="+regionId+" "+"and a.status.statusId="+2);
			List<Object[]> areaList=query.list();
			if(areaList!=null)
			{
				areaBoList=new ArrayList<AreaBO>();
				for(Object[] oArr:areaList)
				{
					AreaBO bo=new AreaBO();
					bo.setAreaId((Integer)oArr[0]);
					bo.setAreaName((String)oArr[1]);
					areaBoList.add(bo);
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
			connection=null;
			dbHelper=null;
		}
		return areaBoList;
		
	}
	
	public List<CityBO> getAreasCityList(int areaId)
	{
		List<CityBO> cityBoList=null;
		Persistor dbHelper=null;
		Session connection=null;
		try
		{
			dbHelper=getPersistor();
			connection=dbHelper.getConnection();
			Query query=connection.createQuery("select c .cityId,c.cityName from City c where c.area.areaId="+areaId+" "+"and c.status.statusId="+2);
			List<Object[]> cityList=query.list();
			if(cityList!=null)
			{
				cityBoList=new ArrayList<CityBO>();
				for(Object[] oArr:cityList)
				{
					CityBO bo=new CityBO();
					bo.setCityId((Integer)oArr[0]);
					bo.setCityName((String)oArr[1]);
					cityBoList.add(bo);
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
			connection=null;
			dbHelper=null;
		}
		return cityBoList;
	}
}





