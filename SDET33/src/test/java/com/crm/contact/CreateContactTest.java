package com.crm.contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest {
	public static void main(String[] args) throws IOException {
		
		//Fetching the data from property file
				FileInputStream fis= new FileInputStream("./src/test/resources/vtiger.properties");
				Properties prp= new Properties();
				prp.load(fis);
				String url = prp.getProperty("url");
				String un = prp.getProperty("username");
				String pwd = prp.getProperty("password");
				String browser = prp.getProperty("browser");
				String timeouts = prp.getProperty("timeout");
				long time = Long.parseLong(timeouts);
				String path = prp.getProperty("excelPath");
				
		//Fetching data from Excel file
				FileInputStream fisExcel=new FileInputStream(path);
				Workbook wb = WorkbookFactory.create(fisExcel);
				String actLastName = wb.getSheet("SDET33").getRow(2).getCell(2).getStringCellValue();
				
		//General operation of browser for opening and logging in
				WebDriver driver = null;
				
				if(browser.equalsIgnoreCase("chrome")){
					WebDriverManager.chromedriver().setup();
					driver= new ChromeDriver();	
				}
				else if(browser.equalsIgnoreCase("firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver= new FirefoxDriver();
				}
				else {
					System.out.println("Browser details not specified");
				}
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
				driver.get(url);
				driver.findElement(By.xpath("//input[@type='text']")).sendKeys(un);
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pwd);
				driver.findElement(By.id("submitButton")).click();
				
		//Finding the contacts tab and creating new contact		
				driver.findElement(By.linkText("Contacts")).click();
				driver.findElement(By.xpath("//img[contains(@title,'Create Contact...')]")).click();
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(actLastName);
				driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
				
		//Verifying the last name
				String expLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
				if(actLastName.equalsIgnoreCase(expLastName)) {
					System.out.println("Contact created Sucessfully");
					
		//Updating the status in the excel file
				wb.getSheet("SDET33").getRow(2).createCell(5).setCellValue("Pass");
				FileOutputStream fosExcel= new FileOutputStream(path);
				wb.write(fosExcel);
				wb.close();
				}
				
		//Signing out from the application
				WebElement logoutButton = driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));

				Actions axn= new Actions(driver);
				axn.moveToElement(logoutButton).perform();
				
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				driver.quit();
				
	}

}
