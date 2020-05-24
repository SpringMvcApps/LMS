package com.lms.commons.security.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lms.logger.ApplicationExceptionLogger;

@Controller
public class SecurityController {
	@Inject 
	private ApplicationExceptionLogger logger;
	
	@RequestMapping(value="/UnAuthorisedAccess.htm",method=RequestMethod.GET)
	public String unAuthorisedAccess(@RequestParam("pageName") String pageName,Model model)
	{
		String viewName=null;
		try
		{
			model.addAttribute("pageName",pageName);
			viewName="UnAuthorisedAccess";
		}
		catch(Exception ex)
		{
			int errId=logger.extractExceptionLogInfo(ex);
			model.addAttribute("errorId",errId);
			model.addAttribute("error","Error Occured while rendering page");
			viewName="Error";
		}
		
		return viewName;
	}

}
