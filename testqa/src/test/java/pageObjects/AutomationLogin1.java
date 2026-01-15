package pageObjects;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import generic.WebLibrary;

public class AutomationLogin1 {

   
    static String username, password;
    WebDriver driver;

    // Constructor: initialize PageFactory with the passed driver
    public AutomationLogin1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement usernameinput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordinput;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginbutton;
    
    public void LoginToApp1(WebDriver driver) throws IOException, InterruptedException {
        // Wait until the button is visible..
    	try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_ALT);
            Thread.sleep(500); // small wait for keyboard change to apply
        } catch (Exception e) {
            e.printStackTrace();
        }
    	WebLibrary lib = new WebLibrary(driver);
    	lib.waitForElementPresent(usernameinput);



        //username = MSExcelAutomation.getexceldata("Sheet2", 1, 3);
        //password = MSExcelAutomation.getexceldata("Sheet2", 1, 4);
        Thread.sleep(500);

        usernameinput.sendKeys("ioux@mail.co");
        passwordinput.sendKeys("ioux!@#$%");
        loginbutton.click();

        String currenturl = driver.getCurrentUrl();
        System.out.println("DEBUG URL: " + currenturl);

        System.out.println("Current url is:" + currenturl);
        Assert.assertFalse(currenturl.contains("login"), "Login failed – still on login page!");

    }
}
