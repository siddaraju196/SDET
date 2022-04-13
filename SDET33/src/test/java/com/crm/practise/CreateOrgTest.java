package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
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

public class CreateOrgTest {
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
		
//Generating random number
		Random ran= new Random();
		int randomNumber = ran.nextInt(1000);
		
//Fetching the organization name from excel
		FileInputStream fisExcel= new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fisExcel);
		String orgName = wb.getSheet("SDET33").getRow(8).getCell(1).getStringCellValue();
		String actOrgName = orgName+randomNumber;
		
//General operation of browser of opening and logging in
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
		
//Finding the Organization tab and creating new organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(actOrgName);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();

//Verifying the Organization created		
		if(actOrgName.equals(expOrgName)) {
			System.out.println("Organization Created");
			System.out.println("TestCase Passed");
			
//Updating the Excel file for the status
			wb.getSheet("SDET33").getRow(8).createCell(5).setCellValue("Pass");
			FileOutputStream fosExcel= new FileOutputStream(path);
			wb.write(fosExcel);
			wb.close();
		}
				
//SigningOut from the Application		
		WebElement logoutButton = driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
		Actions axn= new Actions(driver);
		axn.moveToElement(logoutButton).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		
		driver.quit();	
		
		
	}

}
