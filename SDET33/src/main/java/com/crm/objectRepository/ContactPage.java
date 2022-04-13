package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
 @FindBy(xpath="//img[@alt='Create Contact...']")
 private WebElement plusButton;
 
public ContactPage(WebDriver driver)
  {
	  PageFactory.initElements(driver, this);
  }

public WebElement getPlusButton()
{
	
	return plusButton;
}
// by business logic
public void plus()
{
	plusButton.click();
}
}