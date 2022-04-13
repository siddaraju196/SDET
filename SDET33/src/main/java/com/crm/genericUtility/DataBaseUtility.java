package com.crm.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility {
static Connection connection;
static ArrayList<String> List= new ArrayList<String>();
/**
 * This method is used to establish the connection of the MYSQLDatabase
 *  
 * 
 * 
 */
public static void getMysqlDatabaseConnection(String dbUrl,String dbUserName,String dbPassword) throws Exception
{
	Driver d=new Driver();
	DriverManager.registerDriver(d);
	connection=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
}
/**
 * This method is used to fetch the data from database based on the column number
 * @throws SQLException 
 * 
 */
public static ArrayList<String> getDataFromDatabase(String query,int columnNumber) throws SQLException {
	Statement statement=connection.createStatement();
 ResultSet result = statement.executeQuery(query);
	 while(result.next())
	 {
		 List.add(result.getNString(columnNumber));
	 }
	 statement.close();
	 return List;
}
/**
 * This method is used to fetch the data from the database  based on the column name
 * @throws SQLException 
 */
public static ArrayList<String> getDatabase(String query,String columnNameOrColumnNumber) throws SQLException{
	String num="";
	for (int i = 0; i < columnNameOrColumnNumber.length(); i++) {
		char ch=columnNameOrColumnNumber.charAt(i);
		if (Character.isDigit(ch))	
		{
		      num=num+columnNameOrColumnNumber.charAt(i);
		}
		else {
			break;
		}
	}
	Statement statement=connection.createStatement();
	ResultSet result=statement.executeQuery(query);
	int columnNumber=0;
	String columnName=null;
	if(num.equalsIgnoreCase(columnNameOrColumnNumber))
			{
		columnNumber=Integer.parseInt(num);
				while(result.next())
				{
			List.add(result.getString(columnNumber));
				}
		}
	
	else{

		columnName=columnNameOrColumnNumber;
		while(result.next())
		{
			List.add(result.getString(columnName));
			
		}
	}
	statement.close();
	return  List;
		
}
/**
 * This method is used to update /write/modify the data inside the database
 * @throws SQLException 
 */
public static void updateDateaIntoDatabase(String query) throws SQLException
{
	Statement statement= connection.createStatement();
	statement.executeUpdate(query);
	System.out.println("query executed successfully");
	statement.close();
}
public static void closeDatabase() throws SQLException
{
	connection.close();
}
/**
 * This method is used to close the database connection
 * @throws SQLException 
 */
public static void getMysqlDatabaseConnection1(String dbUrl,String dbUserName,String dbPassword) throws SQLException
{
	Driver driver= new Driver();
	DriverManager.registerDriver(driver);
	Connection connection= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
}
	
}
