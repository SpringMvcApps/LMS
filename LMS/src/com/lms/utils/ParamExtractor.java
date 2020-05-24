package com.lms.utils;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;

public class ParamExtractor {
	
	public int extractParam(ProcedureCall call,Object[] obj)
	{
		
		int num=0;
		for(Object o:obj)
		{
			if(o instanceof String)
			{
				
				call.registerParameter(num,String.class,ParameterMode.IN).bindValue((String)o);
				num++;
				
			}
			else if(o instanceof Integer)
			{
				call.registerParameter(num,Integer.class,ParameterMode.IN).bindValue((Integer)o);
				num++;
			}
		}
		return num;
	}
	

}
