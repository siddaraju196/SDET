package com.crm.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class VtigerTable {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=null;
		String url=null;
		String username=null;
		String password=null;
		String lastname=null;
		try
		{
		   Driver driver = new Driver();
		   DriverManager.registerDriver(driver);
		   connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger","root","root");
		  Statement statement=connection.createStatement();
		  
		  ResultSet result = statement.executeQuery("select * from vtiger;");
		  while(result.next())
		  {
			 url =result.getString(1);
			username = result.getString(2);
			 password =result.getString(3);
			lastname = result.getString(4);
		  }
		  WebDriverManager.chromedriver().setup();
		  WebDriver webDriver=new ChromeDriver();
		  webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  String expectedLastName=lastname;
		  webDriver.get(url);
		  webDriver.findElement(By.name("user_name")).sendKeys(username);
		  webDriver.findElement(By.name("user_password")).sendKeys(password);
		  webDriver.findElement(By.id("submitButton")).click();
		  webDriver.findElement(By.linkText("Contacts")).click();
		  webDriver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		  webDriver.findElement(By.name("lastname")).sendKeys(lastname);
		  
		  webDriver.findElement(By.cssSelector("input[type='submit']")).click();
		 WebElement ele = webDriver.findElement(By.xpath("//img[@src='themes/softed/images/layerPopupBg.gif']"));
		 Actions actions= new Actions(webDriver);
		 actions.moveToElement(ele).perform();
		  webDriver.findElement(By.linkText("Sign Out")).click();
		  webDriver.close();
		 }
		finally
		{
			//connection.close();
			System.out.println("---connection is closed---");
		}
		
	}

}
