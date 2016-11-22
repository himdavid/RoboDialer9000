package com.psqa.framework;

import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;


/**
 * Hello world!
 *
 */
public class Customer {
	
	String ANSWER_BUTTON = "ButtonAnswer";
	
	
	 //@Test
	 public void test() throws IOException{
	  functions functions = new functions();
	  WiniumDriver driver = functions.getDriver("C:\\Program Files (x86)\\Avaya\\Avaya one-X Communicator\\onexcui.exe");
	  try{
		  
		  
		  functions.goToPeTester();

		  functions.waitForElement(ANSWER_BUTTON);
		  driver.findElementById(ANSWER_BUTTON).click();

//		  driver.findElementById("DialButtonNine").click();
//		  driver.findElementById("DialButtonTwo").click();
//		  driver.findElementById("DialButtonZero").click();
//		  driver.findElementById("DialButtonSix").click();
//		  driver.findElementById("DialButtonNine").click();
//		  driver.findElementById("DialButtonZero").click();
//		  driver.findElementById("DialButtonTwo").click();
//		  driver.findElementById("DialButtonOne").click();
//		  driver.findElementById("DialButtonFive").click();
//		  driver.findElementById("DialButtonThree").click();
//		  driver.findElementById("DialButtonSix").click();
		  driver.close();
	  }
	  catch(Exception e){
	   System.out.println(e.getMessage());
	  }
	 }

	public static void main(String[] args) throws IOException {
		Customer app = new Customer();
		app.test();
	}
}
