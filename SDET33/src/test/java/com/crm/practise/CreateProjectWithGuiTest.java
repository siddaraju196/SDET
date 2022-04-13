package com.crm.practise;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectWithGuiTest {
	public static void main(String[] args) throws SQLException, IOException {
		Connection connection= null;
//Connection to DB
		try {
			Driver driver1= new Driver();
			DriverManager.registerDriver(driver1);
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			
//Property file operation	
			FileInputStream fis= new FileInputStream("./src/test/resources/commonData.properties");
			Properties prp=new Properties();
			prp.load(fis);
			String url = prp.getProperty("url");
			String un = prp.getProperty("username");
			String pwd = prp.getProperty("password");
			String timeouts = prp.getProperty("timeout");
			String browser = prp.getProperty("browser");
			long time = Long.parseLong(timeouts);
			
//Browser operation
			WebDriver driver2= null;
			
			if(browser.equalsIgnoreCase("chrome")){
				WebDriverManager.chromedriver().setup();
				driver2= new ChromeDriver();	
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver2= new FirefoxDriver();
			}
			else {
				System.out.println("Browser details not specified");
			}
			
			driver2.manage().window().maximize();
			driver2.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			driver2.get(url);
			driver2.findElement(By.xpath("//input[@name='username']")).sendKeys(un);
			driver2.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
			driver2.findElement(By.xpath("//button[@type='submit']")).click();
			driver2.findElement(By.xpath("//a[@href='/dashboard/projects']")).click();
			driver2.findElement(By.xpath("//span[.='Create Project']")).click();
			String proj_name= "SDET333";
			String proj_manager = "Mohan";
			driver2.findElement(By.name("projectName")).sendKeys(proj_name);
			driver2.findElement(By.name("createdBy")).sendKeys(proj_manager);
			WebElement projectStatus = driver2.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
				Select s= new Select(projectStatus);
				s.selectByVisibleText("On Goging");
			driver2.findElement(By.xpath("//input[@value='Add Project']")).click();
			
			Statement statement= connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project;");
			while(result.next()) {
				String projName = result.getString("project_name");
				
				if(proj_name.equals(projName)) {
					System.out.println("Test case passed");
				}
			}
			
			driver2.close();
				
		}
		finally {
			connection.close();
			System.out.println("Connection to DB closed");
			
			
		}
		
		
		
	}
	

}
