package com.tutorialsninja.qa.listeners;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		 extentReport =  ExtentReporter.generateExtentReport();
		
	
	}
	@Override
	public void onTestStart(ITestResult result) {
		String testName= result.getName();
		 extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+" start execution");
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName= result.getName();
		extentTest.log(Status.PASS, testName+" test success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName= result.getName();
	WebDriver driver= null;
	try {
		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		File srcscreenShotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String desscreenShotPath= ("C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\Screenshots\\"+testName+".png");
		try {
			FileHandler.copy(srcscreenShotfile,new File(desscreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.addScreenCaptureFromPath(desscreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" test got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName= result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" test got skipped");
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport="C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\test-output\\ExtentReport\\extentReport.html";
		File extentReport= new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
