package com.lms.utils;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;

import com.lms.factory.LmsSessionFactory;


public class Persistor {
	private LmsSessionFactory gblFactory=null;
	private Session gblConnection=null;
	public Persistor()
	{
		
	}
	public LmsSessionFactory getFactory()
	{
		if(gblFactory==null)
		{
		gblFactory= new LmsSessionFactory();
		}
		return gblFactory;
	}
	public Session getConnection()
	{   	
		
		if(gblConnection==null)
	    {
		
		    LmsSessionFactory factory=getFactory();
	
			gblConnection=factory.getDbSession();
		}
		return gblConnection;
	}
	public void closeConnection()
	{

		LmsSessionFactory factory=getFactory();
		factory.closeSession();
		factory.closeFactory();
		gblFactory=null;
		gblConnection=null;
		
		System.out.println("------------connection closed--------");
		
	}

	
	public boolean saveObject(Object bean)
	{
		Transaction tx=null;
		Session connection=null;
		boolean flag=false;
		try{
		 connection=getConnection();
		tx=connection.beginTransaction();
	
		connection.save(bean);
		tx.commit();
		flag=true;
		}
		catch(Exception ex){
			
			tx.rollback();
			
			throw ex;
		}
		finally
		{
			closeConnection();
		}
		return flag;
	}
	
	
	
	public boolean deleteObject(Object bean)
	{
		boolean flag=false;
		Transaction tx=null;
		Session connection=getConnection();
		tx=connection.beginTransaction();
		try
		{
			connection.delete(bean);
			tx.commit();
			flag=true;
		}
		catch(Exception ex)
		{
			tx.rollback();
			throw ex;
		
		}
		finally{
			closeConnection();
		}
		return flag;
	}
	
	
	public boolean updateObject(Object bean)
	{
		boolean flag=false;
		Transaction tx=null;
		Session connection=getConnection();
		tx=connection.beginTransaction();
		try
		{
		connection.update(bean);
		tx.commit();
		flag=true;	
		}
		catch(Exception ex)
		{
			tx.rollback();
		}
		finally{
			closeConnection();
		}
		return flag;
	}
	
	
	public String runSpReturnString(String spName,Object[] inParams)
	{
	    
	    String output=null;
	    Transaction tx=null;
	    Session connection=null;
	    ProcedureCall call=null;
	    int paramNumber=0;
	    Object returnValue;
	    ParamExtractor extractor=null;
	    
	    try{
		connection=getConnection();
		tx=connection.beginTransaction();
		
		call=connection.createStoredProcedureCall(spName);
		extractor=new ParamExtractor();
		paramNumber=extractor.extractParam(call,inParams);
		call.registerParameter(paramNumber,String.class,ParameterMode.OUT);
		ProcedureOutputs ol=call.getOutputs();
		returnValue=ol.getOutputParameterValue(paramNumber);
		output=(String)returnValue;
		
		tx.commit();
		}
		catch(Exception ex)
		{
	
			throw ex;
		}
		finally{
	 closeConnection();
		}
		
		return output;
		
	}
	
	public int runSpReturnInt(String spName,Object[] inParams)
	{
		int result=0;
		Session connection=null;
		Transaction tx=null;
		ProcedureCall call=null;
		int paramNumber=0;
		Object returnValue;
		ParamExtractor extractor=null;
		
		connection=getConnection();
		tx=connection.beginTransaction();
		call=connection.createStoredProcedureCall(spName);
		extractor=new ParamExtractor();
		paramNumber=extractor.extractParam(call, inParams);
		call.registerParameter(paramNumber,Integer.class,ParameterMode.OUT);
	    ProcedureOutputs outputs=call.getOutputs();
	    returnValue=outputs.getOutputParameterValue(paramNumber);
	    result=(Integer)returnValue;
	    tx.commit();
	    closeConnection();
	 
	    return result;
		
		
	}
	



}
