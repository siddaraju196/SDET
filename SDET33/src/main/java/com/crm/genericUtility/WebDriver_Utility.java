package com.crm.genericUtility;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriver_Utility {
	static WebDriver driver;
	
	public static void initializeDriver(WebDriver driver) throws Throwable
	{
       if(PropertyUtility.initialize("browser").equalsIgnoreCase("chrome"))
       {
    	   WebDriverManager.chromedriver().setup();
    	   driver= new ChromeDriver();
       }
       else if(PropertyUtility.initialize("browser").equalsIgnoreCase("firefox"))
       {
    	   WebDriverManager.firefoxdriver().setup();
    	   driver= new FirefoxDriver();
       }
       else
       {
    	   Reporter.log("browser is not found",true);
       }
	}
	public static WebDriver openBrowser(WebDriver driver, String url,long timeouts)
	{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(timeouts, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	
	}
	
	public static void waitImlpicit(WebDriver driver, long timeout)
	{
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	public static void waitExplictElementVisible(WebDriver driver, long timeout,WebElement ele)
	{
		WebDriverWait wb= new WebDriverWait(driver,timeout);
		wb.until(ExpectedConditions.visibilityOf(ele));
	}
	/**
	 * This method is used
	 * @param driver
	 * @param timeout
	 * @param ele
	 * @param pollingTime
	 */
	public static void waitUntilElementVisibleWithCustomPoll(WebDriver driver, long timeout,WebElement ele,long pollingTime)
	{
		WebDriverWait wb= new WebDriverWait(driver,timeout);
		wb.until(ExpectedConditions.visibilityOf(ele));
		wb.pollingEvery(Duration.ofSeconds(pollingTime));
		wb.ignoring(Throwable.class);
 
	
	}
	
	public static void customWaitTillElementCliucickable(WebElement ele, int timeout,int pollingTime) throws Throwable
	{
		int count=0;
		while(count<=timeout) 
		{
			try
			{
				ele.click();
				break;
			}
			catch (NoSuchElementException e)
			{
				Thread.sleep(pollingTime);
				count++;
			}
		}
	}
	/**
	 * This method will maximize the browser window
	 * @param driver
	 */
public static void maximizeBrowser(WebDriver driver)
{
	driver.manage().window().maximize();
}
	public static void launchApplication(WebDriver driver,String url)
	{
		driver.get(url);
	}
	public static void waitforPageLoad(WebDriver driver, long timeout) {
		// TODO Auto-generated method stub
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS); 
	}
	public static void openApplicationThroughJavascript(WebDriver driver,String url)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.location='"+url+"'");

	}
	/**
	 * This method is used to send keys
	 * @param driver
	 * @param ele
	 * @param input1
	 */
	public static void sendKeysThroughJavaScriptExecutorUserName(WebDriver driver, WebElement ele,String input)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+input+"'", ele);
		
	}
	public static void sendKeysThroughJavaScriptExecutorForPassword(WebDriver driver, WebElement ele,String input)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+input+"'", ele);
		
	}
	public static void clickActionThroughJs(WebDriver driver,WebElement ele)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);
	}
	public static void scrollDownPage(WebDriver driver,String upOrDown)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,"+upOrDown+"document.body.scrollHeight)");
	}
	/**
	 * This method is used to scroll the webpage untill the element is present
	 * @param driver
	 * @param ele
	 */
	public static void scrollTillElementThroughJS(WebDriver driver,WebElement ele)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	public static String takeScreenShotOfFailedScript(WebDriver driver,String fileName) throws IOException 
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		 File dst = new File("./screenShot/"+fileName+"_"+JavaUtility.getCurrentTimeAndDate()+".png");
		FileUtils.copyFile(src, dst);
		// why they did dst.getAbsolutePath() having doubt
		String absolutePath= dst.getAbsolutePath();
		return absolutePath;
	}
	/**
	 * this method is used to switch the frame by nameOrI  d
	 * @param driver
	 * @param nameOrId
	 */
	public static void frame(WebDriver driver, String nameOrId) {
	driver.switchTo().frame(nameOrId);
	}
	public static void frame(WebDriver driver, WebElement ele)
	{
		driver.switchTo().frame(ele);
	}
	/**
	 * this method is used to quit the browser instance
	 * @param driver
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	/**
	 * this method is used to switch the frame by index
	 * @param driver
	 * @param index
	 */
	public static void frame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * this method is used to select the index from dropdown based on index
	 * @param ele
	 * @param index
	 */
	public static void select(WebElement ele, int index)
	{
		Select sel= new Select(ele);
		sel.selectByIndex(index);
	}
	/**
	 * this method is used to select value from the dropdown based on value
	 * @param ele
	 * @param value
	 */
	public static void select(WebElement ele, String value)
	{
		Select sel= new Select(ele);
		sel.selectByValue(value);
	}
	// why it is not accepting
//	public static void select(WebElement ele, String bus)
//	{
//		Select sel= new Select(ele);
//		sel.deselectByVisibleText(bus);
//	}

	public static void moveToElement(WebDriver driver, WebElement ele)
	{
		Actions act= new Actions(driver);
		act.moveToElement(ele).perform();
	}
	public static void rightClick(WebDriver driver, WebElement ele)
	{
 		Actions act= new Actions(driver);
		act.contextClick(ele).perform();
	}
	
}
