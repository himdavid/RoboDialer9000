package com.psqa.framework;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class functions {

	private WiniumDriver driver;

	public WiniumDriver getDriver(String applicationPath) throws MalformedURLException{
		DesktopOptions options= new DesktopOptions();
		options.setApplicationPath(applicationPath);
		WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"),options);
		this.driver= driver;
		return driver;
	}
	public void waitForElement(String elementID) throws InterruptedException{
		while(driver.findElementsById(elementID).size() == 0) {
			System.out.println("Waiting for the phone to ring");
			Thread.sleep(005);;
		}
		System.out.println("Element Found!!!");
	}

	public void goToPeTester() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://qa-tools.vrli.net/petester/");
	}
}