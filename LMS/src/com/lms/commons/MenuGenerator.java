package com.lms.commons;

import java.util.ArrayList;
import java.util.List;

import com.lms.admin.beans.LmsMenuBean;

public class MenuGenerator {
	
	public static List<LmsMenuBean> getMenuList(int roleId)
	{
		List<LmsMenuBean> beanList=new ArrayList<LmsMenuBean>();
		List<Object[]> menuObj=MenuProvider.fetchRoleWiseList(roleId);
		for(Object[] oArr:menuObj)
		{
			LmsMenuBean bean=new LmsMenuBean();
			bean.setPageName((String)oArr[0]);
			bean.setPageUrl((String)oArr[1]);
			beanList.add(bean);
		}
		
		return beanList;
		
	}

}
