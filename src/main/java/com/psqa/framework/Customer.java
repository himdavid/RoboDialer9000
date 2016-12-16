package com.psqa.framework;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.winium.WiniumDriver;


/**
 * Hello world!
 *
 */
public class Customer {
	
	private String ANSWER_PHONE_LOCATOR = "ButtonAnswer";
	private String HANGUP_PHONE_LOCATOR = "ButtonEnd";
	private String DIAL_ZERO_LOCATOR = "DialButtonZero";
	private String DIAL_ONE_LOCATOR = "DialButtonOne";
	private String DIAL_TWO_LOCATOR = "DialButtonTwo";
	private String DIAL_THREE_LOCATOR = "DialButtonThree";
	private String DIAL_FOUR_LOCATOR = "DialButtonFour";
	private String DIAL_FIVE_LOCATOR = "DialButtonFive";
	private String DIAL_SIX_LOCATOR = "DialButtonSix";
	private String DIAL_SEVEN_LOCATOR = "DialButtonSeven";
	private String DIAL_EIGHT_LOCATOR = "DialButtonEight";
	private String DIAL_NINE_LOCATOR = "DialButtonNine";
	private String DIAL_POUND_LOCATOR = "DialButtonPound";
	private String DIAL_STAR_LOCATOR = "DialButtonStar";
	private String MUTE_PHONE_LOCATOR = "ButtonMute";

	 public void test() throws IOException{
	  Functions functions = new Functions();


	  try{
		  functions.getWebDriver("Chrome");
		  functions.dropFilePeTester("sst01", "C:\\Users\\david_him\\Documents\\Projects\\BofA\\WO15157\\testfiles\\varolii.std.in.20161206.Early.dat",
				  "2695", "PE_EARLY_COLL", "v7",
				  "InputFileMonitor", "50000");

		  WiniumDriver winiumDriver = functions.getWiniumDriver(
		  		"C:\\Program Files (x86)\\Avaya\\Avaya one-X Communicator\\onexcui.exe");


		  /**
		   * Navigational Tests
		   */
		  functions.waitForElement(ANSWER_PHONE_LOCATOR);
		  winiumDriver.findElementById(ANSWER_PHONE_LOCATOR).click();
		  winiumDriver.findElementById(MUTE_PHONE_LOCATOR).click();

		   /**
		   * Perform a Live Answer
		   */
		  Thread.sleep(5000);
		  winiumDriver.findElementById("DialButtonOne").click();

		  /**
		   * Authenticate with the correct SSN
		   */
		  Thread.sleep(10000);
		  winiumDriver.findElementById(DIAL_TWO_LOCATOR).click();
		  winiumDriver.findElementById(DIAL_NINE_LOCATOR).click();
		  winiumDriver.findElementById(DIAL_SIX_LOCATOR).click();
		  winiumDriver.findElementById(DIAL_NINE_LOCATOR).click();
		  winiumDriver.findElementById(DIAL_POUND_LOCATOR).click();

		  /**
		   * Disconnect from the call
		   */
		  Thread.sleep(10000);
		  winiumDriver.findElementById(HANGUP_PHONE_LOCATOR).click();

		  // Get alert info from database
		  Thread.sleep(5000);
		  functions.JDBCconnection();
	  }
	  catch(Exception e){
	   System.out.println(e.getMessage());
	  }
	 }

	 public void sandBox(){
		 Functions functions = new Functions();
		 //String sshCommand = "curl http://sst-chiweb01:8080/api/rest/records/007cf20a-a0de-4224-b937-7330f9030d5f | python -m json.tool";
		 String jsonText = functions.SSH("svc-app03.vrli.net",22,"curl http://sst-chiweb01:8080/api/rest/records/007cf20a-a0de-4224-b937-7330f9030d5f | python -m json.tool");

		 try {
			 functions.parseJSON(jsonText);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }

	 }

	public static void main(String[] args) throws IOException {
		Customer app = new Customer();
		//app.test();
		app.sandBox();
	}
}