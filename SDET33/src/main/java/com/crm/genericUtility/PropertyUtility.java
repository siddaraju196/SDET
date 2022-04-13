
package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	static Properties prop;
public static String initialize(String key) throws Throwable
{
	FileInputStream fis=new FileInputStream(ConstantPath.PROPERTYFILEPATH);
	 prop= new Properties();
	 prop.load(fis); 		
String data= prop.getProperty(key);
return data;
	
}

//public static String fetch(String path,String key) throws IOException
//{
//	FileInputStream fis=new FileInputStream(path);
//	prop= new Properties();
//	prop.load(fis);
//	 String data = prop.getProperty(key);
//	 return data;

}