package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInforrmation {
@FindBy(xpath = "//span[@id='dtlview_Last Name']")
private WebElement lastNameContact;

public ContactInforrmation(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

public WebElement getLastNameContact() {
	return lastNameContact;
}
// by business logic
public String savedLastName()
{
	String data = lastNameContact.getText();
	return data;
}
}
