package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis= new FileInputStream("./src/test/resources/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		// fetch data from excel
		FileInputStream fisExcel= new FileInputStream("./src/test/resources/ExcelPage.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		Row row = wb.getSheet("status").getRow(11);
		String orgName = row.getCell(1).toString();

		// create Random 
		Random ran= new Random();
		int randomNumber = ran.nextInt(1000);
		String expectedOrgName = orgName+randomNumber;
		
		// launch the browser
				WebDriverManager.chromedriver().setup();
				WebDriver  driver= new ChromeDriver();
		
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.get(url);
				driver.findElement(By.name("user_name")).sendKeys(username);
				driver.findElement(By.name("user_password")).sendKeys(password);
				driver.findElement(By.id("submitButton")).click();
				driver.findElement(By.linkText("Organizations")).click();
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				driver.findElement(By.name("accountname")).sendKeys(expectedOrgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				WebElement expOrg=driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"));
				if(expOrg.getText().equals(expectedOrgName))
				{
					FileOutputStream fos= new FileOutputStream("./src/test/resources/ExcelPage.xlsx");
					row.createCell(2).setCellValue("Pass");
					wb.write(fos);
					System.out.println("pass: org name is correct");
				}
				else
				{
					System.out.println("fail: org name is not correct");
				}
					
				WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));
				 Actions act= new Actions(driver);
				 act.moveToElement(signOut).perform();
				 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				 
				
				
	}

}
