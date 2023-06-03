package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateExtentReport() {
		ExtentReports extentReport= new ExtentReports();
		File ExtentReportFile= new File("C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter= new ExtentSparkReporter(ExtentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation Results Report");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		Properties props= new Properties();
		File configProp= new File("C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(configProp);
			props.load(fis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", props.getProperty("url"));
		extentReport.setSystemInfo("Browser Name",props.getProperty("browserName"));
		extentReport.setSystemInfo("Email",props.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",props.getProperty("validPassword"));
		
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReport;

		
		

	}

}
