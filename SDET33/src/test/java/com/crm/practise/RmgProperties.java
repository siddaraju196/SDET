package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RmgProperties {

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
FileInputStream fis = new  FileInputStream("./src/test/resources/rmgprogram.properties");
Properties prop= new Properties();
prop.load(fis);
String url = prop.getProperty("url");
String username = prop.getProperty("username");
String password = prop.getProperty("password");
String browser = prop.getProperty("browser");
String timeout = prop.getProperty("timeout");
System.out.println("url="+url);
System.out.println("username="+username);
System.out.println("password="+password);
System.out.println("timeout="+timeout);
WebDriverManager.chromiumdriver().setup();
WebDriver driver= new ChromeDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get(url);
driver.findElement(By.id("usernmae")).sendKeys(username);
driver.findElement(By.id("inputPassword")).sendKeys(password);
driver.findElement(By.xpath("//button[.='Sign in']")).click();
driver.findElement(By.linkText("Projects")).click();
driver.findElement(By.xpath("//span[.='Create Project']")).click();
driver.findElement(By.name("projectName")).sendKeys("TY_PRJ_004");
driver.findElement(By.name("createdBy")).sendKeys("Deepak Sir");
WebElement dropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
Select selct =new Select(dropdown);
selct.selectByIndex(2);
driver.findElement(By.xpath("//input[@type='submit']")).click();

	
}





	}


