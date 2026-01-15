package sanityTestCases;

import static generic.ScreenshotHelper.captureStep;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.OktaLoginPage;
import pageObjects.SSOLandingPage;

public class testcaseSSOLogin {

    WebDriver driver;
    WebDriverWait wait;

    private void loginViaSSO() throws IOException {

        driver = Driver.getBrowser();
        driver.manage().window().maximize();
        driver.get(Constants.appurl1);

        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.globalwait));

        captureStep(driver, "validateCompleteSSOFlow", "01_Landing_Page");

        // ================= SSO LANDING =================
        SSOLandingPage landing = new SSOLandingPage(driver);
        String parentWindow = driver.getWindowHandle();
        landing.clickProceedToSSO();

        captureStep(driver, "validateCompleteSSOFlow", "02_Click_Proceed_To_SSO");

        // ================= SWITCH TO OKTA =================
        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        captureStep(driver, "validateCompleteSSOFlow", "03_Okta_Login_Page");

        // ================= OKTA LOGIN =================
        OktaLoginPage okta = new OktaLoginPage(driver);
        Assert.assertTrue(okta.isOktaLoginPageLoaded(),
                "Okta login page not loaded");

        okta.login(Constants.username, Constants.password);

        captureStep(driver, "validateCompleteSSOFlow", "04_Okta_Login_Submitted");

        // ================= WAIT FOR OKTA =================
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("okta")));

        // ================= BACK TO EXCHANGE =================
        for (String win : driver.getWindowHandles()) {
            driver.switchTo().window(win);
        }

        captureStep(driver, "validateCompleteSSOFlow", "05_Back_To_Exchange");

        // ================= INTERMEDIATE PAGE =================
        By proceedBtn = By.id("ep-sso-next-btn");
        wait.until(ExpectedConditions.elementToBeClickable(proceedBtn)).click();

        captureStep(driver, "validateCompleteSSOFlow", "06_Proceed_To_Dashboard");

        // ================= FINAL DASHBOARD =================
        wait.until(ExpectedConditions.urlContains("/app/seller/dashboard"));

        captureStep(driver, "validateCompleteSSOFlow", "07_Dashboard_Loaded");
    }

    @Test
    public void validateCompleteSSOFlow() throws IOException {
        loginViaSSO();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/app/seller/dashboard"),
                "User not redirected to Exchange dashboard after SSO"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
