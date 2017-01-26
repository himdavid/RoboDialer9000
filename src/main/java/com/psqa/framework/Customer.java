package com.psqa.framework;

import com.jcraft.jsch.Session;
import com.psqa.framework.Functions;
import com.psqa.framework.JSONFactory;
import com.psqa.framework.SSHFactory;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.winium.WiniumDriver;

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

    public Customer() {
    }

    public void test() throws IOException {
        Functions functions = new Functions();

        try {
            functions.getWebDriver("Chrome");
            functions.dropFilePeTester("sst01", "C:\\Users\\david_him\\Documents\\Projects\\BofA\\WO15157\\testfiles\\varolii.std.in.20161206.Early.dat", "2695", "PE_EARLY_COLL", "v7", "InputFileMonitor", "50000");
            WiniumDriver e = functions.getWiniumDriver("C:\\Program Files (x86)\\Avaya\\Avaya one-X Communicator\\onexcui.exe");
            functions.waitForElement(this.ANSWER_PHONE_LOCATOR);
            e.findElementById(this.ANSWER_PHONE_LOCATOR).click();
            e.findElementById(this.MUTE_PHONE_LOCATOR).click();
            Thread.sleep(5000L);
            e.findElementById("DialButtonOne").click();
            Thread.sleep(10000L);
            e.findElementById(this.DIAL_TWO_LOCATOR).click();
            e.findElementById(this.DIAL_NINE_LOCATOR).click();
            e.findElementById(this.DIAL_SIX_LOCATOR).click();
            e.findElementById(this.DIAL_NINE_LOCATOR).click();
            e.findElementById(this.DIAL_POUND_LOCATOR).click();
            Thread.sleep(10000L);
            e.findElementById(this.HANGUP_PHONE_LOCATOR).click();
            Thread.sleep(5000L);
            functions.JDBCconnection();
        } catch (Exception var3) {
            System.out.println(var3.getMessage());
        }

    }

    public void sandBox() throws ParseException {
        SSHFactory ssh = new SSHFactory();
        JSONFactory json = new JSONFactory();
        Session sshSession = ssh.connectToSSH("svc-app03.vrli.net", 22);
        String jsonText = ssh.sendCommand(sshSession, "curl http://sst-chiweb01:8080/api/rest/records/ec7395a1572f4fff8fbab5be523f89d0");
        HashMap jsonMap = JSONFactory.parseJSONMap(jsonText);
        json.printJSONMap(jsonMap);
    }

    public static void main(String[] args) throws IOException, ParseException {
        Customer app = new Customer();
        app.test();
    }
}
