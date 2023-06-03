package com.tutorialsninja.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	public WebDriver driver;
	LoginPage loginPa;
	AccountPage ap;

	public LoginTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() {

		driver = initializedBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		 loginPa = homePage.clickLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredential(String email, String password) {
		loginPa.enterEmail(email);
		loginPa.enterPassword(password);
		AccountPage ap = loginPa.clickLoginButton();

		synchronized (driver) {
			try {
				driver.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Assert.assertTrue(ap.editYouraccountInformation());
		

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() throws IOException {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInValidCredential() {

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.xpath("//input[@id='input-password']"))
				.sendKeys(propTestdata.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).isDisplayed());
		driver.quit();

	}

	@Test(priority = 3)
	public void verifyLoginWithEmptyField() {

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String ErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert')]")).getText();
		Assert.assertEquals(ErrorMessage, propTestdata.getProperty("expectedErrorMessage"));

	}
}
