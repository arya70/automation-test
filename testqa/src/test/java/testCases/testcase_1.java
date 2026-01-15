package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.AutomationDashboard;


public class testcase_1 {

   static WebDriver driver;   // driver variable

   @Test(priority = 1)  
    public void logintest() throws IOException, InterruptedException {

        driver = Driver.getBrowser();   // Driver = your custom driver manager class
        driver.get(Constants.appurl1);

        AutomationDashboard login1 = PageFactory.initElements(driver, AutomationDashboard.class);
        login1.LoginToApp1(driver);
        // Optional: Close browser
         driver.quit();
    }
}
