package com.lms.logger;

import org.springframework.stereotype.Component;

import com.lms.utils.Persistor;

@Component
public class ApplicationExceptionLogger {
	
	public int extractExceptionLogInfo(Exception ex)
	{
		
		int errorId=0;
		int count=0;
		StringBuilder builder;
		StackTraceElement[] element=ex.getStackTrace();
		builder=new StringBuilder();
		builder.append("Exception occured at >>");
		for(StackTraceElement trace:element)
		{
		
	builder.append("class>"+trace.getClassName()+">method>"+trace.getMethodName()+">line number"+trace.getLineNumber()+">");
	count++;
	if(count>25)
	{
		break;
	}
		}
		builder.append(ex.getMessage());
		errorId=logException(builder);
		return errorId;
		
	}
public int logException(StringBuilder builder)
{
	int errorId=0;
	Persistor errPersistor=null;
	String exStr=new String(builder);
	errPersistor=new Persistor();
	Object[] inParams={exStr};
	errorId=errPersistor.runSpReturnInt("addErrorLog", inParams);
	return errorId;
} 
}
