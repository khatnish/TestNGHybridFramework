package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
private	WebElement myAccountOptions;
	
	@FindBy(xpath="//a[text()='Login']")
private	WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registarOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Actions
	
	
	public void searchValidProduct(String product) {
		searchField.sendKeys(product);
	}
	public SearchPage clickSearchButton() {
		searchButton.click();
	return	new SearchPage(driver);
	}
	
	public void clickOnMyAccount() {
		myAccountOptions.click();
	}
	
	public LoginPage clickLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	public void clickRegisterOption() {
		registarOption.click();
	}

}
