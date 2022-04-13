package com.crm.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Facebook {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
Connection connection=null;
String url=null;
String username=null;
String password=null;
try
{
	Driver driver=new Driver();
	DriverManager.registerDriver(driver);
	connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook","root","root");
	Statement statement = connection.createStatement();
	ResultSet result = statement.executeQuery("select * from facebook;");
	while(result.next())
	{
		url=result.getString(1);
		username=result.getString(2);
		password=result.getString(3);
	}
	WebDriverManager.chromedriver().setup();
	WebDriver driver1=new ChromeDriver();
	driver1.manage().window().maximize();
	driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	String expectedUesrname=username;
	driver1.get(url);
	driver1.findElement(By.id("email")).sendKeys(username);
	driver1.findElement(By.id("pass")).sendKeys(password);
	driver1.findElement(By.name("login")).click();
	driver1.close();
	
}
finally
{
	connection.close();
	System.out.println("-----connection is closed--");
}
	}
}
	


