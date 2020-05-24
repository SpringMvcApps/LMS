package com.lms.commons.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.commons.service.UtilityService;
import com.lms.logger.ApplicationExceptionLogger;

@Controller
public class UtilityController {
	
	@Inject 
	private ApplicationExceptionLogger logger;
	@Inject 
	private UtilityService service;
	
	@ResponseBody
	@RequestMapping(value="/StateList.htm",method=RequestMethod.GET)
	public String getStateByCountry(@RequestParam int countryId,Model model)
	{
		String jsonStateList=null;
		try
		{
		jsonStateList=service.fetchStateReturnJson(countryId);
		}
		catch(Exception ex)
		{
			int errId=logger.extractExceptionLogInfo(ex);
			
		}
		return jsonStateList;
	}
	
	@ResponseBody
	@RequestMapping(value="/RegionList.htm",method=RequestMethod.GET)
	public String getRegionByState(@RequestParam int stateId)
	{
		String jsonRegionList=null;
		
		try
		{
			jsonRegionList=service.fetchRegionReturnJson(stateId);
		}
		catch(Exception ex)
		{
			int errId=logger.extractExceptionLogInfo(ex);
		}
		return jsonRegionList;
	}

	@ResponseBody
	@RequestMapping(value="/AreaList.htm",method=RequestMethod.GET)
	public String getAreaByRegion(int regionId)
	{
		String jsonAreaList=null;
		try
		{
			jsonAreaList=service.fetchAreaReturnJson(regionId);
		}
		catch(Exception ex)
		{
			int errId=logger.extractExceptionLogInfo(ex);
		}
		return jsonAreaList;
	}
	
	@ResponseBody
	@RequestMapping(value="/CityList.htm",method=RequestMethod.GET)
  public String getCityByArea(@RequestParam int areaId)
  {
	  String cityJsonList=null;
	  try
	  {
		  cityJsonList=service.fetchCityReturnJson(areaId);
	  }
	  catch(Exception ex)
	  {
		  int errId=logger.extractExceptionLogInfo(ex);
	  }
	  return cityJsonList;
  }
}
