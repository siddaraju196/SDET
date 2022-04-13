package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgWithIndustryTest {

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
				
		//Fetching the data from excel file
				FileInputStream fisExcel= new FileInputStream(path);
				Workbook wb = WorkbookFactory.create(fisExcel);
				String orgName = wb.getSheet("SDET33").getRow(11).getCell(1).getStringCellValue();
				String actOrgName = orgName+randomNumber;
				String actIndusName = wb.getSheet("SDET33").getRow(11).getCell(2).getStringCellValue();
				String actTypName = wb.getSheet("SDET33").getRow(11).getCell(3).getStringCellValue();
				
				
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
				
				
				
		//Finding Industry and type text field & entering the data
				WebElement indusList = driver.findElement(By.name("industry"));
				Select slct= new Select(indusList);
				List<WebElement> allIndusList = slct.getOptions();
				for(int i=0; i<allIndusList.size(); i++) {
					slct.selectByVisibleText(actIndusName);
				}
				
				WebElement typeList = driver.findElement(By.name("accounttype"));
				Select slct1= new Select(typeList);
				List<WebElement> allTypeList = slct1.getOptions();
				for(int i=0; i<allTypeList.size(); i++) {
					slct1.selectByVisibleText(actTypName);
				}
				
				driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
 
		//Verifying the Organization created with industry and type text field
				String expOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				String expInduName = driver.findElement(By.id("dtlview_Industry")).getText();
				String expTypName = driver.findElement(By.id("dtlview_Type")).getText();
				if(actOrgName.equals(expOrgName) && actIndusName.equals(expInduName) && actTypName.equals(expTypName) ) {
					System.out.println("Organization Created sucessfully with industry and type");
					System.out.println("TestCase Passed");
					
		//Updating the status in the excel file
					wb.getSheet("SDET33").getRow(11).createCell(5).setCellValue("Pass");
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
