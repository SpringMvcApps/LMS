package com.lms.admin.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.admin.beans.AreaDTO;
import com.lms.admin.beans.AreaVO;
import com.lms.admin.beans.CityDTO;
import com.lms.admin.beans.CityVO;
import com.lms.admin.beans.CountryDTO;
import com.lms.admin.beans.CountryVO;
import com.lms.admin.beans.EmployeeDTO;
import com.lms.admin.beans.EmployeeVO;
import com.lms.admin.beans.LmsMenuDTO;
import com.lms.admin.beans.LmsMenuVO;
import com.lms.admin.beans.RegionDTO;
import com.lms.admin.beans.RegionVO;
import com.lms.admin.beans.RoleDTO;
import com.lms.admin.beans.RoleVO;
import com.lms.admin.beans.StateDTO;
import com.lms.admin.beans.StateVO;
import com.lms.admin.service.AdminService;
import com.lms.appException.ApplicationException;
import com.lms.logger.ApplicationExceptionLogger;

@Controller
public class AdminController {
	
	@Inject 
	private ApplicationExceptionLogger logger;
	@Inject
	private AdminService service;
	
	@RequestMapping(value="/Dashboard.htm",method=RequestMethod.GET)
	public String getHome(HttpServletRequest request,HttpServletResponse response,Model model)
	{
	
		return "Home";
	}
	
	@RequestMapping(value="/AddCountry.htm",method=RequestMethod.GET)
	public String viewCountryForm(HttpServletRequest req,HttpServletResponse res,Model model)
	{
		String viewName=null;
		boolean hasAccess=false;
		try
		{
	
		List<CountryDTO> dtoList=service.fetchCountry();
		model.addAttribute("countries",dtoList);
		System.out.println("In country");
		viewName="AddCountry";
		}
		catch(Exception ex)
		{
			int errId=logger.extractExceptionLogInfo(ex);
			model.addAttribute("errorId",errId);
			model.addAttribute("error","Error occured while displaying Country Page");
			viewName="Error";
		}
		return viewName;
	
    }
	
    @RequestMapping(value="/AddCountry.htm",method=RequestMethod.POST)
	public String addCountry(@ModelAttribute CountryVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
	{
    	
    	String viewName=null;
    	boolean hasError=false;
    	
    	try
    	{
         HttpSession session=req.getSession(false);
         if(session!=null)
         {
        int userId=(Integer)session.getAttribute("userId");
    
     	CountryDTO dto=new CountryDTO(vo.getCountryName(),vo.getCountryCode(),vo.getStatus(),userId);
     	
     	boolean flag=service.addCountry(dto);
     	if(flag==true)
     	{
            model.addAttribute("alert","Record Inserted Successfully");
     	}
     	else
     	{
     		model.addAttribute("alert", "Failed to insert please try again");
     	}
        }
        else
        {
        	hasError=true;
        	res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
        }
    	
        }
    	catch(Exception ex)
    	{
    		
    		hasError =true;
    		logger=new ApplicationExceptionLogger();
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Failed to insert");
    		viewName="Error";
    	}
         
    	if(hasError==false)
    	{
    	
    		List<CountryDTO> dtoList=service.fetchCountry();
            model.addAttribute("countries",dtoList);
    		model.addAttribute("alert", "Inserted");
    		viewName="AddCountry";
    	}
		return viewName;
	}
    
    @RequestMapping(value="/ViewCountry.htm",method=RequestMethod.GET)
    public String getCountryRecords(Model model)
    {
    	List<CountryDTO> dtoList=null;
    	String viewName=null;
    	try{
    	dtoList=service.fetchCountry();
    	model.addAttribute("countries",dtoList);
    	viewName="ViewCountry";
    	}
    	catch(Exception ex)
    	{
    	    int id=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",id);
    	    model.addAttribute("error","Error occured while fetching country data");
    	    viewName="Error";
    	}
    	return viewName;
    }
    
    @RequestMapping(value="/deleteCountry.htm",method=RequestMethod.GET)
   public String deleteCountry(@RequestParam("id") String countryId,HttpServletResponse res,Model model)
   {
       boolean flag=false;
       String viewName=null;
       try{
       flag=service.deleteSelectedCountry(countryId);
       if(flag==true)
       {

          res.sendRedirect("/LMS/AddCountry.htm?alert=Y");
          
       }
       else
       {

    	   res.sendRedirect("/LMS/AddCountry.htm?alert=N");
       }
       }
       catch(Exception ex)
       {
    	   int errId=logger.extractExceptionLogInfo(ex);
    	   model.addAttribute("errorId",errId);
    	   model.addAttribute("error","Error occured while while deleting country");
    	   viewName="Error";
       }
      
	   return viewName;
   }

    @RequestMapping(value="/updateCountry.htm",method=RequestMethod.POST)
    public String updateCountry(@ModelAttribute CountryVO vo,HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	HttpSession session=request.getSession(false);
    	if(session!=null)
    	{
    		int userId=(Integer)session.getAttribute("userId");
    		CountryDTO dto=new CountryDTO();
    		dto.setCountryId(vo.getCountryId());
    		dto.setCountryName(vo.getCountryName());
    		dto.setCountryCode(vo.getCountryCode());
    		dto.setStatus(vo.getStatus());
    		dto.setUserId(userId);
    		try
    		{
    			flag=service.updateSelectedCountry(dto);	
    			if(flag==true)
    			{
    				model.addAttribute("alert","Updated");
    				List<CountryDTO> dtoList=service.fetchCountry();
    				model.addAttribute("countries",dtoList);
    				viewName="AddCountry";
    			}
    			else
    			{
    				model.addAttribute("alert","failed to update");
    				List<CountryDTO> dtoList=service.fetchCountry();
    				model.addAttribute("countries",dtoList);
    				viewName="AddCountry";
    			}
    		}
    		catch(Exception ex)
    		{
    			int errId=logger.extractExceptionLogInfo(ex);
    			model.addAttribute("errorId",errId);
    			model.addAttribute("error","Error occured while updating record");
    			viewName="Error";
    		}
    		
    		
    		
    	}
    	else
    	{
    		 model.addAttribute("alert","You must Log In First");
        	 viewName="LoginPage";
    	}
    	
    	return viewName;
    }
    
    @RequestMapping(value="/AddState.htm",method=RequestMethod.GET)
    public String viewStateForm(Model model)
    {
    	String viewName=null;
    	try
    	{
    	List<CountryDTO> dtoList=service.fetchActiveCountries();
    	model.addAttribute("countries",dtoList);
    	List<StateDTO> dtoStateList=service.fetchStates();
    	model.addAttribute("states",dtoStateList);
    	viewName="AddState";
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while rendering page");
    		viewName="Error";
    	}
    	return viewName;
    }
    
    @RequestMapping(value="/AddState.htm",method=RequestMethod.POST)
    public String addState(@ModelAttribute StateVO vo,HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	
    	String viewName=null;
    	boolean flag=false;
    	boolean hasError=false;
    	try{
    	HttpSession session=request.getSession(false);
    	if(session!=null)
    	{
    	int userId=(Integer)session.getAttribute("userId");
    	StateDTO dto=new StateDTO(vo.getStateName(),vo.getCountryId(),vo.getStatus(),userId);
    	
    	flag=service.addState(dto);
    	if(flag==true)
    	{
    		model.addAttribute("alert","Record Inserted Successfully");
    		
    	}
    	else
    	{
    		model.addAttribute("alert", "Failed to insert please try again");
    	}
    	}
    	else
    	{
    		hasError=true;
    		response.sendRedirect("/LMS/?targetPage="+request.getRequestURI());
    	}
    	}
    	catch(Exception ex)
    	{
    		hasError=true;
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while inserting state record ");
    		viewName="Error";
    	}
    	if(hasError==false)
    	{
    		model.addAttribute("alert","Inserted");
    		List<StateDTO> dtoList=service.fetchStates();
    		model.addAttribute("states",dtoList);
    		List<CountryDTO> dtoCountryList=service.fetchActiveCountries();
    		model.addAttribute("countries",dtoCountryList);
    		viewName="AddState";
    	}
    
    	return viewName;
    }
    @RequestMapping(value="/updateState.htm",method=RequestMethod.POST)
    public String updateState(@ModelAttribute StateVO vo,HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	HttpSession session=request.getSession(false);
    	if(session!=null)
    	{
    		try
    		{
    		
    			int userId=(Integer)session.getAttribute("userId");
    			StateDTO dto=new StateDTO();
    			dto.setStateId(vo.getStateId());
    			dto.setStateName(vo.getStateName());
    			dto.setCountryId(vo.getCountryId());
    			dto.setStatus(vo.getStatus());
    			dto.setUserId(userId);
    			flag=service.updateSelectedState(dto);
    			if(flag==true)
    			{
    				model.addAttribute("alert","Updated");
    				List<StateDTO> dtoList=service.fetchStates();
    				List<CountryDTO> dtoCountryList=service.fetchActiveCountries();
    				model.addAttribute("countries",dtoCountryList);
    				model.addAttribute("states",dtoList);
    				viewName="AddState";
    				
    			}
    			else
    			{
    				model.addAttribute("alert","Failed to update");
    	    		List<StateDTO> dtoList=service.fetchStates();
    	    		List<CountryDTO> dtoCountryList=service.fetchActiveCountries();
    	    		model.addAttribute("countries",dtoCountryList);
    	    		model.addAttribute("states",dtoList);
    	    		viewName="AddState";
    				
    			}
    		}
    		catch(Exception ex)
    		{
    			int errId=logger.extractExceptionLogInfo(ex);
    			model.addAttribute("errorId",errId);
    			model.addAttribute("error","Error occured while updating state record");
    			viewName="Error";
    		}
    		
    	}
    	else
    	{
    		model.addAttribute("alert","You must Log In First");
    		viewName="LoginPage";
    	}
    	return viewName;
    	
    }
   
    @RequestMapping(value="/deleteState.htm",method=RequestMethod.GET)
    public String deleteState(@RequestParam("id") String stateId,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	try
    	{
    		flag=service.deleteSelectedState(stateId);
    		if(flag==true)
    		{
    			res.sendRedirect("/LMS/AddState.htm?alert=Y");
    		}
    		else
    		{
    			res.sendRedirect("/LMS/AddState.htm?alert=N");
    		}
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while updating state record");
    		viewName="Error";
    	}
    	return viewName;
    }
    	
    @RequestMapping(value="/AddRegion.htm",method=RequestMethod.GET)
    public String viewRegionForm(Model model)
    {
    	String viewName=null;
    	try{
    	
    	List<StateDTO> activeStates=service.getActiveStates();
    	List<RegionDTO> dtoList=service.getRegions();
    	model.addAttribute("activeStates",activeStates);
    	model.addAttribute("regions",dtoList);
    	viewName="AddRegion";
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while rendering region page");
    		viewName="Error";
    	}
    	return viewName;
    }
    
    @RequestMapping(value="/AddRegion.htm",method=RequestMethod.POST)
    public String addRegion(@ModelAttribute RegionVO vo,HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String viewName="";
    	boolean flag=false;
    	boolean hasError=false;
    	try
    	{
    	HttpSession session=request.getSession(false);
    	if(session!=null)
    	{
    		int userId=(Integer)session.getAttribute("userId");
    		RegionDTO dto=new RegionDTO();
    		dto.setRegionName(vo.getRegionName());
    		dto.setStatus(Integer.parseInt(vo.getStatus()));
    		dto.setStateId(Integer.parseInt(vo.getStateId()));
    		dto.setUserId(userId);
    		flag=service.addRegionRecord(dto);
    		if(flag==true)
    		{
    			model.addAttribute("alert","Record Inserted Successfully");
    		}
    		else
    		{
    			model.addAttribute("alert", "Failed to insert please try again");
    		}
    		
    		
    	}
    	else
    	{
    		hasError=true;
    		response.sendRedirect("/LMS/?targetPage="+request.getRequestURI());
    		
    	}
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured");
    		viewName="Error";	
    	}
    	if(hasError==false)
    	{
    		List<StateDTO> activeStates=service.getActiveStates();
			List<RegionDTO> dtoList=service.getRegions();
			model.addAttribute("activeStates",activeStates);
			model.addAttribute("regions",dtoList);
			model.addAttribute("alert","Inserted");
			viewName="AddRegion";
    	}
    		
    	return viewName;
    }
    
    @RequestMapping(value="/AddArea.htm",method=RequestMethod.GET)
    public String viewArea(Model model)
    {
    	String viewName=null;
    	try
    	{
    	List<RegionDTO> dtoRegionList=service.getActiveRegions();
    	List<AreaDTO> dtoList=service.fetchAreas();
    	model.addAttribute("regions",dtoRegionList);
    	model.addAttribute("areas",dtoList);
    	viewName="AddArea";
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error while rendering Area Page");
    		viewName="Error";
    	}
    	
    	return viewName;
    }
    @RequestMapping(value="/AddArea.htm",method=RequestMethod.POST)
    public String addArea(@ModelAttribute AreaVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	boolean hasError=false;
    	HttpSession session=req.getSession(false);
    	
    	try
		{
    	if(session!=null)
    	{
    		
    		int userId=(Integer)session.getAttribute("userId");
    		AreaDTO dto=new AreaDTO();
    		dto.setAreaName(vo.getAreaName());
    		dto.setStatus(Integer.parseInt(vo.getStatus()));
    		dto.setRegionId(Integer.parseInt(vo.getRegionId()));
    		dto.setUserId(userId);
    		boolean flag=service.addArea(dto);
    		if(flag==true)
    		{
    			
    			model.addAttribute("alert","Record Inserted Successfully");
    			
    		}
    		else
    		{
    			model.addAttribute("alert", "Failed to insert please try again");
    			
    		}
    	}
    		else
        	{
    			hasError=true;
    			res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
        	}
    		}
    		catch(Exception ex)
    		{
    			hasError=true;
    			int errId=logger.extractExceptionLogInfo(ex);
    			model.addAttribute("errorId",errId);
    			model.addAttribute("error","Error occured while inserting record");
    			viewName="Error";
    		}
    		if(hasError==false)
    		{
    			List<RegionDTO> dtoRegionList=service.getActiveRegions();
    			List<AreaDTO> dtoList=service.fetchAreas();
    			model.addAttribute("regions",dtoRegionList);
    			model.addAttribute("areas",dtoList);
    			model.addAttribute("alert","Inserted");
    			viewName="AddArea";
    		}
    			
    
    	return viewName;
    }
    
    @RequestMapping(value="/AddMenu.htm",method=RequestMethod.GET)
    public String viewMenuPage(Model model)
    {
    	String viewName="AddMenu";
    	List<RoleDTO> roleDTOList=service.fetchActiveRoles();
    	model.addAttribute("roleDTOList",roleDTOList);
    	return viewName;
    	
    }
    @RequestMapping(value="/AddMenu.htm",method=RequestMethod.POST)
    public String addMenuPage(@ModelAttribute LmsMenuVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	boolean hasError=false;
    	try
		{
    	HttpSession session=req.getSession(false);
    	if(session!=null)
    	{
    		
    			int userId=(Integer)session.getAttribute("userId");
    			LmsMenuDTO dto=new LmsMenuDTO();
    			dto.setPageName(vo.getPageName());
    			dto.setPageUrl(vo.getPageUrl());
    			dto.setStatus(vo.getStatus());
    			dto.setRoleId(vo.getRoleId());
    			dto.setUser(userId);
    			flag=service.addMenu(dto);
    			if(flag==true)
    			{
    				model.addAttribute("alert","Record Inserted Successfully");
    			}
    			else
    			{
    				model.addAttribute("alert", "Failed to insert please try again");
    			}
    		}
    		else
    		{
    			hasError=true;
    			res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
    		
    		}
			}
    		catch(Exception ex)
    		{
    			hasError=true;
    			int errId=logger.extractExceptionLogInfo(ex);
    			model.addAttribute("errorId",errId);
    			model.addAttribute("error","Error occured while adding menu record");
    			viewName="Error";
    		}
    		if(hasError==false)
    		{
    			List<RoleDTO> roleDTOList=service.fetchActiveRoles();
				model.addAttribute("roleDTOList",roleDTOList);
				model.addAttribute("alert","Inserted");
				viewName="AddMenu";
    		}
    	
    	
    	
    	return viewName;
    }
    
    @RequestMapping(value="/AddCity.htm",method=RequestMethod.GET)
    public String viewCityPage(Model model)
    {
    	String viewName=null;
    	try
    	{
    	List<AreaDTO> areaDTOList=service.fetchActiveAreas();
    	List<CityDTO> cityDTOList=service.fetchCities();
    	model.addAttribute("areaDTOList",areaDTOList);
    	model.addAttribute("cityDTOList",cityDTOList);
    	viewName="AddCity";
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while displaying the city page");
    		viewName="Error";
    	}
    	return viewName;
    }
    
    @RequestMapping(value="/AddCity.htm",method=RequestMethod.POST)
    public String addCityDetails(@ModelAttribute CityVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	boolean hasError=false;
    	try
    	{
    	HttpSession session=req.getSession(false);
    	if(session!=null)
    	{
    
    		int userId=(Integer)session.getAttribute("userId");
    		CityDTO dto=new CityDTO();
    		dto.setCityName(vo.getCityName());
    		dto.setStatus(vo.getStatus());
    		dto.setAreaId(vo.getAreaId());
    		dto.setUserId(userId);
    		flag=service.addCity(dto);
    		if(flag==true)
    		{
    		
    		model.addAttribute("alert","Record Inserted Successfully");
    		}
    		else
    		{
    		model.addAttribute("alert", "Failed to insert please try again");
    		}
    	}
    	else
    	{
    		hasError=true;
    		res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
    	}
    	}
    	catch(Exception ex)
    	{
    		hasError=true;
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while inserting city details");
    		viewName="Error";
    	}
    	if(hasError==false)
    	{
    		
    		List<AreaDTO> areaDTOList=service.fetchActiveAreas();
    		List<CityDTO> cityDTOList=service.fetchCities();
    		model.addAttribute("areaDTOList",areaDTOList);
    		model.addAttribute("cityDTOList",cityDTOList);
    		viewName="AddCity";
    	}
    	
    
    	return viewName;
    }
    
    @RequestMapping(value="/AddRole.htm",method=RequestMethod.GET)
    public String viewRolePage(Model model)
    {
    	String viewName=null;
    	try{
    		
    		List<RoleDTO> roleDTOList=service.fetchRoles();
    		model.addAttribute("roleDTOList",roleDTOList);
    		viewName="Role";
    	  }
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error while displaying ");
    		viewName="Error";
    	}
    	
    	return viewName;
    }
    
    @RequestMapping(value="/AddRole.htm",method=RequestMethod.POST)
    public String addUserRoles(@ModelAttribute RoleVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	boolean flag=false;
    	boolean hasError=false;
    	try
		{
    	HttpSession session=req.getSession(false);
    	if(session!=null)
    	{
    		int userId=(Integer)session.getAttribute("userId");
    		RoleDTO dto=new RoleDTO();
    		dto.setRoleName(vo.getRoleName());
    		dto.setStatus(vo.getStatus());
    		dto.setUser(userId);
    	
    		flag=service.addUserRole(dto);
    		if(flag==true)
    		{
    			
    			model.addAttribute("alert","Record Inserted Successfully");
    		}
    		else
    		{
    			model.addAttribute("alert", "Failed to insert please try again");
    		}
    	}
    	else
    	{
    		hasError=true;
    		res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
    	}
		}
    		catch(Exception ex)
    		{
    			hasError=true;
    			int errId=logger.extractExceptionLogInfo(ex);
    			model.addAttribute("errorId",errId);
    			model.addAttribute("error","Error occured while inserting user role");
    			viewName="Error";
    		}
    		
    		if(hasError==false)
    		{
    			List<RoleDTO> roleDTOList=service.fetchRoles();
    			model.addAttribute("roleDTOList",roleDTOList);
    			viewName="Role";
    		}
    	
    	
    	return viewName;
    }
    
    @RequestMapping(value="/Employee.htm",method=RequestMethod.GET)
    public String viewEmployeeForm(HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	List<CountryDTO> countryDtoList=null;
    	List<RoleDTO> roleDtoList=null;
    	try
    	{
    		countryDtoList=service.fetchActiveCountries();
    		roleDtoList=service.fetchActiveRoles();
    		model.addAttribute("countryDtoList",countryDtoList);
    		model.addAttribute("roleDtoList",roleDtoList);
    		viewName="EmployeeRegistration";
    	}
    	catch(Exception ex)
    	{
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Error occured while rendering web page");
    		viewName="Error";
    		
    	}
    	return viewName;
    }
    
    @RequestMapping(value="/Employee.htm",method=RequestMethod.POST)
    public String registerEmployee(@ModelAttribute EmployeeVO vo,HttpServletRequest req,HttpServletResponse res,Model model)
    {
    	String viewName=null;
    	int adminId=0;
    	boolean hasError=false;
    	int generatedId=0;
    	
    	try
    	{
    	HttpSession session=req.getSession(false);
    	if(session!=null)
    	{
    	adminId=(Integer)session.getAttribute("userId");
    	EmployeeDTO dto=new EmployeeDTO();
    	dto.setEmpName(vo.getEmpName());
    	dto.setEmpLastName(vo.getEmpLastName());
    	dto.setGender(vo.getGender());
    	dto.setCountry(vo.getCountry());
    	dto.setState(vo.getState());
    	dto.setCity(vo.getCity());
        dto.setAreaId(vo.getAreaId());
        dto.setRegionId(vo.getRegionId());
        dto.setStatus(vo.getStatus());
        dto.setEmail(vo.getEmail());
        dto.setMobileNo(vo.getMobileNo());
        dto.setAdminId(adminId);
        dto.setRoleId(vo.getRoleId());
        generatedId=service.addEmployeeDetails(dto);
        if(generatedId>0)
        {
        	model.addAttribute("alert","Record Inserted Successfully");
        }
        else
        {
        	model.addAttribute("alert", "Failed to insert please try again");
        }
        
    	}
    	else
    	{
    		hasError=true;
    		res.sendRedirect("/LMS/?targetPage="+req.getRequestURI());
    	}
    	}
    	catch(Exception ex)
    	{
    		hasError=true;
    		int errId=logger.extractExceptionLogInfo(ex);
    		model.addAttribute("errorId",errId);
    		model.addAttribute("error","Failed to register employee");
    		viewName="Error";
    	}
    	if(hasError==false)
    	{
    		//addModel Data and confirmation msg
    		viewName="EmployeeRegistration";
    	}
    	return viewName;
    }
    
}
