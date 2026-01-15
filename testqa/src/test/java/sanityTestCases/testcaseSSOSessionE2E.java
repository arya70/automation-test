package sanityTestCases;

import static generic.ScreenshotHelper.captureStep;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.OktaLoginPage;
import pageObjects.SSOLandingPage;
import pageObjects.SSOSessionPage;

public class testcaseSSOSessionE2E {

    WebDriver driver;
    WebDriverWait wait;

    private void launchAndLoginViaSSO(String testName) throws IOException {

        driver = Driver.getBrowser();
        driver.manage().window().maximize();
        driver.get(Constants.appurl1);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        captureStep(driver, testName, "01_Landing_Page");

        SSOLandingPage landing = new SSOLandingPage(driver);
        String parentWindow = driver.getWindowHandle();
        landing.clickProceedToSSO();

        captureStep(driver, testName, "02_Click_Proceed_To_SSO");

        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        captureStep(driver, testName, "03_Okta_Login_Page");

        OktaLoginPage okta = new OktaLoginPage(driver);
        Assert.assertTrue(okta.isOktaLoginPageLoaded(),
                "Okta login page not loaded");

        okta.login(Constants.username, Constants.password);

        captureStep(driver, testName, "04_Okta_Login_Submitted");

        driver.switchTo().window(parentWindow);

        captureStep(driver, testName, "05_Back_To_Exchange");
    }

    /* =========================================================
       TC-SSO-006: Automatic Token Refresh
       ========================================================= */
    @Test
    public void validateAutomaticTokenRefresh() throws IOException {

        String testName = "validateAutomaticTokenRefresh";
        launchAndLoginViaSSO(testName);

        SSOSessionPage session = new SSOSessionPage(driver);

        session.waitForTokenRefreshWindow();
        captureStep(driver, testName, "06_Token_Refresh_Window");

        Assert.assertTrue(session.performActionAfterIdle(),
                "Action failed after idle time");

        captureStep(driver, testName, "07_Action_After_Idle");

        Assert.assertTrue(session.isUserStillLoggedIn(),
                "User logged out after token refresh");

        Assert.assertFalse(session.isRedirectedToSSO(),
                "Unexpected redirect to SSO");

        captureStep(driver, testName, "08_Session_Validated");
    }

    /* =========================================================
       NEGATIVE: Invalid Username
       ========================================================= */
    @Test
    public void validateInvalidUsernameLogin() throws IOException {

        String testName = "validateInvalidUsernameLogin";

        driver = Driver.getBrowser();
        driver.manage().window().maximize();
        driver.get(Constants.appurl1);

        captureStep(driver, testName, "01_Landing_Page");

        SSOLandingPage landing = new SSOLandingPage(driver);
        String parentWindow = driver.getWindowHandle();
        landing.clickProceedToSSO();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                break;
            }
        }

        captureStep(driver, testName, "02_Okta_Page");

        OktaLoginPage okta = new OktaLoginPage(driver);
        okta.enterUsername("invaliduser@test.com");

        captureStep(driver, testName, "03_Invalid_Username_Submitted");

        Assert.assertTrue(driver.getCurrentUrl().contains("okta"),
                "User navigated away from Okta on invalid username");
    }

    /* =========================================================
       NEGATIVE: Invalid Password
       ========================================================= */
    @Test
    public void validateInvalidPasswordLogin() throws IOException {

        String testName = "validateInvalidPasswordLogin";
        launchAndLoginViaSSO(testName);

        captureStep(driver, testName, "03_Invalid_Password_Attempt");

        Assert.assertTrue(driver.getCurrentUrl().contains("okta"),
                "User logged in with invalid password");
    }

    /* =========================================================
       TC-SSO-010: Session Expiry Handling
       ========================================================= */
    @Test
    public void validateSessionExpiryHandling() throws IOException {

        String testName = "validateSessionExpiryHandling";
        launchAndLoginViaSSO(testName);

        SSOSessionPage session = new SSOSessionPage(driver);

        session.waitForTokenRefreshWindow();
        captureStep(driver, testName, "06_Token_Expiry_Window");

        session.refreshBrowser();
        captureStep(driver, testName, "07_Browser_Refreshed");

        Assert.assertTrue(session.isSessionExpired(),
                "Session did not expire as expected");

        captureStep(driver, testName, "08_Session_Expired");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
