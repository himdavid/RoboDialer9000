package com.psqa.framework;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

import com.jcraft.jsch.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class Functions {

	private WiniumDriver winiumDriver;
	private WebDriver webDriver;
	private String PE_TESTER_LOGIN_PAGE = "https://qa-tools.vrli.net/petester/user_mgt/login";
	private String PE_TESTER_USERNAME_LOCATOR = "input[name=\"username\"]";
	private String PE_TESTER_USERNAME_VALUE = "dhim";
	private String PE_TESTER_PASSWORD_LOCATOR = "input[name=\"password\"]";
	private String PE_TESTER_PASSWORD_VALUE = "oc32HAvB";
    private String PE_TESTER_LOGIN_BUTTON_LOCATOR = "input[type='submit']";
    private String PE_TESTER_INPUTFILE_LINK_LOCATOR = "a[href='/petester/input/']";
    private String PE_TESTER_HOST_LOCATOR = "#hostname";
    private String PE_TESTER_CLIENTID_LOCATOR = "input#clientSysId";
    private String PE_TESTER_SOLUTIONNAME_LOCATOR = "#solutionId";
    private String PE_TESTER_SOLUTIONVERSION_LOCATOR = "#solutionVersionId";
    private String PE_TESTER_FILEMONITORTASK_LOCATOR = "#fileMonitorTask";
    private String PE_TESTER_TIMEOUT_LOCATOR = "#fileDropTimeOut";
    private String PE_TESTER_DROPFILE_BUTTON_LOCATOR = "#drop";
    private String PE_TESTER_CHOOSEFILE_LOCATOR = "#svp_file";
    private String PE_TESTER_UPLOADFILE_Button_LOCATOR = "button[value=\"Upload File\"]";

	public WiniumDriver getWiniumDriver(String applicationPath) throws MalformedURLException{
		DesktopOptions options= new DesktopOptions();
		options.setApplicationPath(applicationPath);
		WiniumDriver winiumDriver = new WiniumDriver(new URL("http://localhost:9999"),options);
		this.winiumDriver = winiumDriver;
		return winiumDriver;
	}

	public WebDriver getWebDriver(String browser){

		if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver webDriver = new ChromeDriver();
			this.webDriver = webDriver;
		}

		return webDriver;
	}

	public void waitForElement(String elementID) throws InterruptedException{
		while(winiumDriver.findElementsById(elementID).size() == 0) {
			System.out.println("Waiting for the phone to ring");
			//Thread.sleep(005);
		}
		System.out.println("Element Found!!!");
	}

	/**
     * Find an element by the css selector
     * @return element, the WebElement
     */
	public WebElement findElementByCssSelector(String cssSelector) throws InterruptedException {
        while(webDriver.findElements(By.cssSelector(cssSelector)).size() == 0) {
        	Thread.sleep(1000);
		}
		return webDriver.findElement(By.cssSelector(cssSelector));
    }

    public void selectElementFromDropDown(String cssSelectorDropDown, String text) {
		WebElement dropDownElement = webDriver.findElement(By.cssSelector(cssSelectorDropDown));
		Select select = new Select(dropDownElement);
		select.selectByValue(text);
	}

	public void dropFilePeTester(String host, String testFilePath, String clientId, String solutionName, String version, String fileMonitorTask,
								 String timeOut) throws InterruptedException{

		webDriver.get(PE_TESTER_LOGIN_PAGE);

		if(webDriver.getCurrentUrl().equals(PE_TESTER_LOGIN_PAGE)) {
			findElementByCssSelector(PE_TESTER_USERNAME_LOCATOR).sendKeys(PE_TESTER_USERNAME_VALUE);
			findElementByCssSelector(PE_TESTER_PASSWORD_LOCATOR).sendKeys(PE_TESTER_PASSWORD_VALUE);
			findElementByCssSelector(PE_TESTER_LOGIN_BUTTON_LOCATOR).click();
		}

		findElementByCssSelector(PE_TESTER_INPUTFILE_LINK_LOCATOR).click();

		Thread.sleep(5000);

		findElementByCssSelector("option[value=\""+ host +"\"]").click();
		findElementByCssSelector(PE_TESTER_CHOOSEFILE_LOCATOR).sendKeys(testFilePath);
		findElementByCssSelector(PE_TESTER_CLIENTID_LOCATOR).sendKeys(clientId);
		findElementByCssSelector(PE_TESTER_SOLUTIONNAME_LOCATOR).sendKeys(solutionName);
		findElementByCssSelector(PE_TESTER_SOLUTIONVERSION_LOCATOR).sendKeys(version);
		findElementByCssSelector(PE_TESTER_FILEMONITORTASK_LOCATOR).sendKeys(fileMonitorTask);
		findElementByCssSelector(PE_TESTER_TIMEOUT_LOCATOR).clear();
		findElementByCssSelector(PE_TESTER_TIMEOUT_LOCATOR).sendKeys(timeOut);
		findElementByCssSelector(PE_TESTER_UPLOADFILE_Button_LOCATOR).click();


		findElementByCssSelector(PE_TESTER_DROPFILE_BUTTON_LOCATOR).click();

	}

	public void JDBCconnection() {

		// JDBC driver name and database URL
		String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
		String DB_URL = "jdbc:oracle:thin:@sst-bch01-vip.vrli.net:1580:bch01";

		//  Database credentials
		String USER = "sst01_ch_appl";
		String PASS = "sst01_ch_appl";
		Connection conn;
		Statement stmt;
		try {
			//STEP 2: Register JDBC driver
			Class.forName("oracle.jdbc.OracleDriver");

			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Connected database successfully...");

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql ="SELECT di.DC_ALERT_SYSID, di.KEYWORD, di.VALUE  " +
					"FROM DISPATCH_INTERACT di " +
					"JOIN ALERT a " +
					"ON a.DC_ALERT_SYSID = di.DC_ALERT_SYSID " +
					"WHERE di.DC_ALERT_SYSID = " +
											"(SELECT a.DC_ALERT_SYSID " +
					"						FROM RECORD r " +
					"						JOIN ALERT a " +
											"ON a.TRACKING_ID = r.TRACKING_ID " +
											"AND r.FIRST_NAME = 'Robo' " +
											"AND r.LAST_NAME = 'Cop' " +
											"AND r.WHEN_CREATED < trunc(sysdate) " +
											"AND ROWNUM = 1) AND di.dispatch_seq = 1";

			ResultSet rs = stmt.executeQuery(sql);
			//STEP 5: Extract data from result set
			while (rs.next()) {
				//Retrieve by column name

				String sysid = rs.getString("DC_ALERT_SYSID");
				String keyword = rs.getString("KEYWORD");
				String value = rs.getString("VALUE");

				//Display values
				System.out.println("Alert SysID: " + sysid);
				System.out.println("Keyword: " + keyword);
				System.out.println("Value: " + value);
				System.out.println("==================================");

			}
			rs.close();
		} catch(SQLException  se){

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String SSH(String host, int port, String command) {

		StringBuilder outputBuffer = new StringBuilder();
		String terminalOutput = null;

		try{
			JSch jsch=new JSch();
			Session session=jsch.getSession("dhim", host, port);

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);

			//String passwd = JOptionPane.showInputDialog("Enter password");
			session.setPassword("JwMGwTMS5");
			session.connect(30000);   // making a connection with timeout.
			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setCommand(command);
			InputStream commandOutput = channel.getInputStream();
			channel.connect();

			int readByte = commandOutput.read();
			while(readByte != 0xffffffff)
			{
				outputBuffer.append((char)readByte);
				readByte = commandOutput.read();
			}
			channel.disconnect();
			session.disconnect();
			terminalOutput = outputBuffer.toString();
			System.out.println(terminalOutput);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return terminalOutput;
	}

	public String parseJSON(String json) throws ParseException {
		String jsonString = null;

		JSONParser jsonParser = new JSONParser();

		JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

		String trackingID = (String) jsonObject.get("trackingID");

		System.out.println("The tracking_id is: " + trackingID);

		return jsonString;
	}
}