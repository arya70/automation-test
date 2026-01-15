package sanityTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.BrowserBackAfterLoginPage;
import pageObjects.AutomationLogin1;


public class testcaseBackButtonAfterLogin {

     WebDriver driver;

    @Test(priority = 5)
    public void browserBackAfterLogin() throws Exception {

        driver = Driver.getBrowser();        // Launch browser
        driver.get(Constants.appurl1);       // Open application URL

        // Login Page POM
        AutomationLogin1 lp = PageFactory.initElements(driver, AutomationLogin1.class);

        // Perform Login
        lp.LoginToApp1(driver);
        Thread.sleep(1000);

        // Browser Back Validation POM
        BrowserBackAfterLoginPage back = PageFactory.initElements(driver, BrowserBackAfterLoginPage.class);

        // 1. Verify user is on landing page after login
        back.verifyUserOnLandingPage();

        // 2. Press browser back button
        back.pressBrowserBackButton();

        Thread.sleep(1000);

        // 3. Verify user is STILL on landing page (back navigation blocked)
        back.verifyUserStillOnLandingPage();

        // Close Browser
        driver.quit();
    }
}
