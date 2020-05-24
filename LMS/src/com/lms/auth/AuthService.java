package com.lms.auth;

import com.lms.utils.Persistor;

public class AuthService {
	
	public String[] getSessionVariables(int loggedUserId,String loggedUserPassword)
	{
		Persistor dbObj=null;
		String[] sessionVariables=null;
		String variables;
		String authProcedure="Authorize_User";
		Object[] inParams={loggedUserId,loggedUserPassword};
		dbObj=new Persistor();
		variables=dbObj.runSpReturnString(authProcedure,inParams);
		if(variables!=null)
		{
		sessionVariables=variables.split(",");
		}
		return sessionVariables;
	}

}
