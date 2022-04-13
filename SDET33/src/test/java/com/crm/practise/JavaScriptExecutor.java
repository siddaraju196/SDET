package com.crm.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.WebDriver_Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutor {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//WebDriver_Utility.launchApplication(null, null, 0);
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		WebDriver_Utility.openApplicationThroughJavascript(driver, "http://localhost:8888");
		WebElement ele = driver.findElement(By.name("user_name"));
		WebDriver_Utility.sendKeysThroughJavaScriptExecutorUserName(driver, ele, "admin");
		WebElement ele1=driver.findElement(By.name("user_password"));
		WebDriver_Utility.sendKeysThroughJavaScriptExecutorForPassword(driver, ele1, "admin");
		WebElement ele2=driver.findElement(By.id("submitButton"));
		WebDriver_Utility.clickActionThroughJs(driver, ele2);
		Thread.sleep(1000);
		
		WebDriver_Utility.scrollDownPage(driver, "+");
		Thread.sleep(1000);
		WebDriver_Utility.scrollDownPage(driver, "-");
		WebElement ele3=driver.findElement(By.xpath("//td[text()='Module']"));
		Thread.sleep(1000);
		WebDriver_Utility.scrollTillElementThroughJS(driver, ele3);
		
		
		
	}

}
