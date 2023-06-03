package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegistarPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{

	public RegisterTest() throws IOException {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
		
		driver = initializedBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage= new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickRegisterOption();
		
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)
	public void verifyRegisterWithMandatoryField() {
		RegistarPage registerPage= new RegistarPage(driver);
		registerPage.enterFirstName(propTestdata.getProperty("firstName"));
		registerPage.enterLastName(propTestdata.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimestamp());
		registerPage.entertelephone(propTestdata.getProperty("telphone"));
		registerPage.enterpassword(propTestdata.getProperty("password"));
		registerPage.enterconformPassword(propTestdata.getProperty("conformPassword"));
		registerPage.selectCheckBox();
		AccountSuccessPage asp=registerPage.clickLogin();
		
		String actualmsg= asp.accountSuccessMsg();
		
		Assert.assertEquals(actualmsg,propTestdata.getProperty("successMessage") );
		
	}
	@Test(priority=2)
	public void verifyRegisterAccountByProvidingAllFields() throws InterruptedException {
		
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("nisha");
		driver.findElement(By.id("input-lastname")).sendKeys("khatiwada");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimestamp());
		driver.findElement(By.id("input-telephone")).sendKeys("984274444");
		driver.findElement(By.id("input-password")).sendKeys("Test@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Test@123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@type=\"checkbox\"]")).click();
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		Thread.sleep(3000);
	String actualmsg=driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!'] ")).getText();
		
		Assert.assertEquals(actualmsg,"Your Account Has Been Created!","Account is not created" );
		
	}
	
	@Test
	public void verifyRegisterWithAllEmptyFields() {
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		
		String actualPrivacyWarningMsg= driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible'] ")).getText();
		Assert.assertEquals(actualPrivacyWarningMsg, "Warning: You must agree to the Privacy Policy!");
	
		String actualFirstNameWarningMsg= driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarningMsg, "First Name must be between 1 and 32 characters!");
		
		String actualLastNameWarningMsg= driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarningMsg, "Last Name must be between 1 and 32 characters!");
		
		String actualEmailWarningMsg= driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailWarningMsg, "E-Mail Address does not appear to be valid!");
		
		String actualTelephoneWarningMsg= driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarningMsg, "Telephone must be between 3 and 32 characters!");
		
		String actualPasswordWarningMsg= driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarningMsg, "Password must be between 4 and 20 characters!");
		
		
	
	}
		
	}


