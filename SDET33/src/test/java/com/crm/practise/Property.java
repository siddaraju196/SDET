package com.crm.practise;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
FileInputStream fis= new FileInputStream("./src/test/resources/commondata.properties");
Properties prop= new Properties();
prop.load(fis);
String url = prop.getProperty("url");
String username=prop.getProperty("username");
String password=prop.getProperty("password");
String browser=prop.getProperty("browser");
String timeout=prop.getProperty("timeout");

System.out.println("url="+url);
System.out.println("username="+username);
System.out.println("password="+password);
System.out.println("browser="+browser);
System.out.println("timeout="+timeout);


	}

}
