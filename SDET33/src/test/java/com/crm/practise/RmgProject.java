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

public class RmgProject {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=null;
		
		try
		{
		Driver driver =new Driver();
		DriverManager .registerDriver(driver);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery("select * from project");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver1=new ChromeDriver();
		driver1.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver1.get("http://localhost:8084/");
		driver1.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver1.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver1.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver1.findElement(By.linkText("Projects")).click();
		driver1.close();
		}
		finally
		{
			connection.close();
			System.out.println("---connection close---");
		}
		
	}
}


