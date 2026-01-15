package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.AutomationHomepage;

public class testcase_3 {

    static WebDriver driver;

    @Test(priority = 3)
    public void notLoggedInCheck() {

        driver = Driver.getBrowser();

        // Step 1: Open site
        driver.get(Constants.appurl1);

        // Step 2: Create POM object with driver
        AutomationHomepage home = new AutomationHomepage(driver);

        // Step 3: Verify user is NOT logged in
        boolean status = home.isUserNotLoggedIn();

        Assert.assertTrue(status, 
            "FAIL: User seems logged in! Expected NOT logged in.");

        System.out.println("PASS: User is NOT logged in — No active session found.");

        driver.quit();
    }
}
