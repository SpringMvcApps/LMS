package com.lms.commons.service;

import java.util.List;

import javax.inject.Inject;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.lms.admin.beans.AreaBO;
import com.lms.admin.beans.CityBO;
import com.lms.admin.beans.RegionBO;
import com.lms.admin.beans.StateBO;
import com.lms.commons.dao.UtilityDao;

@Service
public class UtilityService {
	
	@Inject
	private UtilityDao dao;
	
	public String getJson(Object obj) throws Exception
	{
		String json=null;
		ObjectMapper mapper=new ObjectMapper();
		json=mapper.writeValueAsString(obj);
		return json;
	}
	public String fetchStateReturnJson(int countryId)throws Exception
	{
		 
		String stateJsonList=null;
		try
		{
		List<StateBO> stateBoList=dao.getCountryStateList(countryId);
		if(stateBoList!=null)
		{
			stateJsonList=getJson(stateBoList);
		}
		}
		catch(Exception ex)
		{
		throw ex;	
		}
		
		return stateJsonList;
		
	}
	public String fetchRegionReturnJson(int stateId)throws Exception
	{
		String regionJsonList=null;
		try
		{
		List<RegionBO> regionBoList=dao.getStatesRegionList(stateId)	;
		if(regionBoList!=null)
		{
			regionJsonList=getJson(regionBoList);
		}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return regionJsonList;
	}

	public String fetchAreaReturnJson(int regionId)throws Exception
	{
		String areaJsonList=null;
		try
		{
			List<AreaBO> areaBoList=dao.getRegionsAreaList(regionId);
			if(areaBoList!=null)
			{
				areaJsonList=getJson(areaBoList);
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return areaJsonList;
	}
	
	public String fetchCityReturnJson(int areaId)throws Exception
	{
		String cityJsonList=null;
		try
		{
			List<CityBO> cityBoList=dao.getAreasCityList(areaId);
			if(cityBoList!=null)
			{
				cityJsonList=getJson(cityBoList);
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return cityJsonList;
	}

}
