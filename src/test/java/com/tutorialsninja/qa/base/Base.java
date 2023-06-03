package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties propTestdata;
	
	public Base() throws IOException  {
		prop=new Properties();
		File file= new File("C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream inputfile;
		propTestdata = new Properties();
		File dataFile= new File("C:\\Users\\nisha\\eclipse-workspace\\TutorialsNinjaProj\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		FileInputStream inputDataFile=new FileInputStream(dataFile);
		propTestdata.load(inputDataFile);
		try {
			inputfile = new FileInputStream(file);
			prop.load(inputfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public WebDriver initializedBrowserAndOpenApplicationURL(String browserName) {
		if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}
		 else if(browserName.equals("safari")) {
			driver=new SafariDriver();
		}
		 else if(browserName.equals("edge")) {
				driver=new EdgeDriver();
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
