package com.lms.commons.security.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.lms.commons.domain.LmsMenu;
import com.lms.utils.Persistor;


public class SecurityAuthenticator
{
	 

	public static Persistor getPersistor()
	{
		return new Persistor();
	}
	
	public static boolean hasAccess(String url,int roleId)
	{
		boolean access=false;
		Persistor dbHelper=null;
		Session connection=null;
		int menuId=0;
		try
		{
			dbHelper=getPersistor();
			connection=dbHelper.getConnection();
			Query query1=connection.createQuery("select count(*) from MenuToRole m where m.menu.pageUrl=?and m.role.roleId=?");
			query1.setParameter(0,url);
			query1.setParameter(1,roleId);
			Object count=query1.uniqueResult();
			if(count!=null)
			{
				Long l=(Long)count;
			int countValue=l.intValue();
			if(countValue>0)
			{
				access=true;
			}
			}
			
		}
		catch(Exception ex)
		{
			throw ex;
		}
		
		return access;
	}

}
