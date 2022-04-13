package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(xpath="//a[contains(@href,'Calendar')] ")
	private WebElement calendarTab;

	@FindBy(xpath="//a[contains(@href,'Leads')] ")
	private WebElement leadsTab;
	
	@FindBy(xpath="//a[.='Organizations']")
	private WebElement OrganizationsTab;
	

	@FindBy(xpath="//a[contains(@href,'Contacts') and text()='Contacts']")
	private WebElement contactsTab;
	
	@FindBy(xpath="//a[text()='Opportunities']")
	private WebElement OpportunitiesTab;
	
	
	@FindBy(xpath="//a[text()='Products']")
	private WebElement productsTab;
	
	@FindBy(xpath="//a[contains(@href,'Documents') and text()='Documents']")
	private WebElement documentTab;
	
	
	@FindBy(xpath="//a[contains(@href,'Email') and text()='Email']")
	private WebElement emailTab;
	
	@FindBy(xpath="//a[text()='Trouble Tickets']")
	private WebElement troubleTab;
	
	@FindBy(xpath="//a[contains(@href,'Dashboard') and text()='Dashboard']")
	private WebElement dashboardTab;
	
	@FindBy(xpath="//a[contains(@href,'ProjectTask') and text()='Project Tasks' and not(contains(@id,'more')) and not(contains(@class,'hdrLink'))]")
	private WebElement projectTab;
	
	@FindBy(xpath="//td[contains(@onmouseover,'rajus196@gmail.com')]")
	private WebElement signoutTab;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signoutButton;
	
	// create construcor of homepage
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void getHomePage() {
		
	}

	public WebElement getCalendarTab() {
		calendarTab.click();
		return calendarTab;
	}

	public WebElement getLeadsTab() {
		return leadsTab;
	}

	public WebElement getOrganizationsTab() {
		OrganizationsTab.click();
		return OrganizationsTab;
	}

	public WebElement getContactsTab()
	{
		contactsTab.click();
		return contactsTab;
	}

	public WebElement getOpportunitiesTab() {
		return OpportunitiesTab;
	}

	public WebElement getProductsTab() {
		return productsTab;
	}

	public WebElement getDocumentTab() {
		return documentTab;
	}

	public WebElement getEmailTab() {
		return emailTab;
	}

	public WebElement getTroubleTab() {
		return troubleTab;
	}

	public WebElement getDashboardTab() {
		return dashboardTab;
	}

	public WebElement getProjectTab() {
		return projectTab;
	}

	public WebElement getSignoutTab() {
		signoutTab.click();
		signoutButton.click();
		return signoutTab;
	}

//	public WebElement getSignoutButton() {
//		signoutButton.click();
//		return signoutButton;
//	}
	// by business logic
	public void newContact()
	{
		contactsTab.click();
	}
		
	}

