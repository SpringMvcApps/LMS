package com.lms.utils;

public class TestUtils {
	
	public static void main(String[] args)
	{
		Persistor p=new Persistor();
		System.out.println(p.getFactory());
		p.closeConnection();
	//	p.viewStatus();
		//System.out.println(p.getFactory());
	
//		Object[] obj={"delhi",10};
//	//	p.runSpReturnString("addCity", obj);
//		
//		for(Object o:obj)
//		{
//			if(o instanceof String)
//			{
//				System.out.println("String captured");
//			}
//			else if(o instanceof Integer)
//			{
//				System.out.println("int  captured");
//			}
//		}
		
	}

}
