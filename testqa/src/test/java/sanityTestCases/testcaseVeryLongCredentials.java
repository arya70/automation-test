package sanityTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.VeryLongCredentials;

public class testcaseVeryLongCredentials {

     WebDriver driver;

    @Test(priority = 3)
    public void verifyVeryLongCredentials() throws Exception {

        driver = Driver.getBrowser();              // FIX 1: Start browser
        driver.get(Constants.appurl1 + "/login");  // FIX 2: Navigate correctly

        VeryLongCredentials obj = new VeryLongCredentials(driver);
        obj.enterVeryLongCredentials();

        String currentURL = driver.getCurrentUrl();

        // FIX 3: Assert login should NOT be successful
        Assert.assertTrue(
                currentURL.contains("login"),
                "Login should fail for extremely long input!"
        );

        driver.quit();
    }
}
