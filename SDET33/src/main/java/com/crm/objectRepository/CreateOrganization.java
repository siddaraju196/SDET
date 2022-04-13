
package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganization {
@FindBy(xpath="//input[@name='accountname']")
private WebElement lastName;

@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement saveButton;


public CreateOrganization(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}


public WebElement getLastName(String orgName) 
{
	lastName.sendKeys(orgName);
	saveButton.click();
	return lastName;
}



}
