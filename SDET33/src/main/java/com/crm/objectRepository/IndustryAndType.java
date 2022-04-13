package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class IndustryAndType {
	@FindBy(name="industry")
	private WebElement industryDropDown;
	@FindBy(name="accounttype")
	private WebElement type;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	public WebElement getSaveButton() {
		return saveButton;
	}
	public  IndustryAndType(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}
	public WebElement getType() {
		return type;
	}
	// by business logic
	public void selectIndustry(String Education)
	{
	  Select s= new Select(industryDropDown);
	  s.selectByValue(Education);
	}
   public void selectType(String Analyst)
   {
	   Select s1= new Select(type);
	   s1.selectByVisibleText(Analyst);
   }
   public void save()
   {
	   saveButton.click();
   }
}
