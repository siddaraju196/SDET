package com.crm.testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.ClickOnPrevious;
import com.crm.objectRepository.LoginPage;

public class TC_114_ClickOnPreviousButton {
 static WebDriver driver=null;
	public static void main(String[] args) throws Throwable {
String propertyFilePath = ConstantPath.PROPERTYFILEPATH;
PropertyUtility.initialize(propertyFilePath);
String url = PropertyUtility.fetchString("url");
String username = PropertyUtility.fetchString("username");
String password = PropertyUtility.fetchString("password");
String timeout = PropertyUtility.fetchString("timeout");
long timeouts = JavaUtility.convertStringToLong(timeout);

driver=WebDriver_Utility.initializeDriver(driver, timeouts, url);
WebDriver_Utility.waitImlpicit(driver, timeouts);
LoginPage login= new LoginPage(driver);
login.loginAction();
ClickOnPrevious cop= new ClickOnPrevious(driver);
cop.clickAction();
WebElement expContact = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
if(expContact.getText().contains("Jagan433"))
{
	System.out.println("pass");
	
}
else
{
	System.out.println("fail");
}

WebElement signOut = driver.findElement(By.xpath("//img[contains(@src,'themes/softed/images/user.PNG')]"));

Actions action= new Actions(driver);
action.moveToElement(signOut).perform();
driver.findElement(By.xpath("//a[text()='Sign Out']"));
driver.quit();
	}

}
