package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.AutomationProductsLinkPage;

public class testcase_4 {

    static WebDriver driver;

    @Test(priority = 4)
    public void loginLinkRedirectCheck() {

        driver = Driver.getBrowser();

        // Step 1: Open home page
        driver.get(Constants.appurl1);

        // Step 2: Click Login link
        AutomationProductsLinkPage loginPage = new AutomationProductsLinkPage(driver);
        loginPage.clickLoginLink();

        // Step 3: Verify redirection to login page
        loginPage.verifyRedirectedToProductsPage();

        driver.quit();
    }
}
