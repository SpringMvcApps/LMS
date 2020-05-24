package com.lms.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class LmsSessionFactory {
	
	private Session dbSession=null;
    private SessionFactory factory=null;
    private Configuration cfg=null;
   // static int a=0;
	public LmsSessionFactory()
	{
		//a++;
		cfg=new Configuration();
		cfg=cfg.configure("com/lms/cfgs/hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
		//System.out.println(a);
	}
   
	public Session getDbSession()
	{
		dbSession=factory.openSession();
		return dbSession;
	}
	public void closeSession()
	{
		
		dbSession.close();
		
	
	}
	public void closeFactory()
	{
		
			factory.close();
	
	}
	
	


}
