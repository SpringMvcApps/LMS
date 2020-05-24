package com.lms.commons;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lms.factory.LmsSessionFactory;
import com.lms.utils.Persistor;

public class MenuProvider {
	
	public static List<Object[]> fetchRoleWiseList(int roleId)
	
	{
		Persistor dbHelper=null;
		dbHelper=new Persistor();
		Session connection=dbHelper.getConnection();
		//Query query=session.createQuery("select m.menu from MenuToRole m where m.role.roleId="+roleId);
		Query query=connection.createQuery("select m.menu.pageName,m.menu.pageUrl from MenuToRole m where m.role.roleId="+roleId);
		List<Object[]> menuList=query.list();
		//List<LmsMenu> menuList=query.list();
		dbHelper.closeConnection();
		return menuList;
	}
//public static void main(String[] args)
//{
//	MenuProvider m=new MenuProvider();
////	List<Object[]>mn=m.fetchRoleWiseList(1);
////	for(Object[] obj:mn)
////	{
////		System.out.println((String)obj[0]);
////		System.out.println((String)obj[1]);
////	}
//	List<LmsMenu>mn=m.fetchRoleWiseList(1);
//	for(LmsMenu mj:mn)
//	{
//		System.out.println(mj.getPageName()+mj.getPageUrl());
//		System.out.println(mj.getUser().getUserId());
//		
//	}
//}
}
