package com.crm.genericUtility1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
static ArrayList<String> list=new ArrayList<String>();
	public static ArrayList<String> fetch(String url,String un,String pwd,String query ,int column ) throws SQLException 
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connect=DriverManager.getConnection(url,un,pwd);
		Statement statement= connect.createStatement();
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			
			list.add(result.getString(column));
		}
		connect.close();
		return list;
		
	}
}
