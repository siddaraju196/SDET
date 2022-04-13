package com.crm.contact;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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

public class CreateContactWithOrgTest {

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
		Random rn= new Random();
		int randomNumber = rn.nextInt(1000);
		
		
//Fetching data from Excel file
		FileInputStream fisExcel=new FileInputStream(path);
		Workbook wb = WorkbookFactory.create(fisExcel);
		String orgName = wb.getSheet("SDET33").getRow(5).getCell(1).getStringCellValue();
		String actLastName = wb.getSheet("SDET33").getRow(5).getCell(2).getStringCellValue();
		
		String actOrgName = orgName+randomNumber;
		
		
		
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
		
//Finding the Organization tab and creating new organization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(actOrgName);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		
		if(actOrgName.equalsIgnoreCase(expOrgName))
		{
			System.out.println("Organization created sucessfully");
		}
		else {
			System.out.println("no org");
		}

//Finding the contacts tab and creating new contact		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[contains(@title,'Create Contact...')]")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(actLastName);
		
		
		
//Finding the organization name from the organization lookup
		String parentWinId = driver.getWindowHandle();
		driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1]/img[@src='themes/softed/images/select.gif']")).click();
		Set<String> childWinIds = driver.getWindowHandles();
		for(String winId:childWinIds) {
			driver.switchTo().window(winId);
			if(winId.equals(parentWinId)) {
				}
			else {
				break;
			}
		}
		driver.findElement(By.name("search_text")).sendKeys(actOrgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[@href='javascript:window.close();']")).click();
		
		for(String winId:childWinIds) {
			driver.switchTo().window(winId);
			if(winId.equals(parentWinId)) {
			break;	
			}			
		}
		
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
//verifying the last name with organization name
		String expLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		
		if(actLastName.equalsIgnoreCase(expLastName) && actOrgName.equalsIgnoreCase(expOrgName)) {
			System.out.println("Contact created with organization");
			System.out.println("Test case passed");
			
//Updating the status in the excel sheet		
			wb.getSheet("SDET33").getRow(5).createCell(5).setCellValue("Pass");
			FileOutputStream fosExcel= new FileOutputStream(path);
			wb.write(fosExcel);
			wb.close();
		}		
		else {
			System.out.println("NOt");
		}
		

		
//Signing out from the application
		WebElement logoutButton = driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));

		Actions axn= new Actions(driver);
		axn.moveToElement(logoutButton).perform();
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}

}
