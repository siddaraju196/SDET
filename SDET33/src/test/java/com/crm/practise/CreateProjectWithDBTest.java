package com.crm.practise;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectWithDBTest {

	public static void main(String[] args) throws SQLException, IOException {
		Connection connection= null;
//Connection to DB
				try {
					Driver driver1= new Driver();
					DriverManager.registerDriver(driver1);
					connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
					Statement statement= connection.createStatement();
					String proj_id = "TY_proj_030";
					statement.executeUpdate("insert into project values('"+proj_id+"','Deba','26/03/2022','SDET33','completd',9);");
					
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
					List<WebElement> projectIdList = driver2.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[1]"));
					
					for(WebElement prjID:projectIdList) {
						String pID = prjID.getText();
						
						if(proj_id.equals(pID)) {
							System.out.println("Testcase passed");
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
