package sanityTestCases;

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

public class testcaseSSOSessionFlow {

    WebDriver driver;
    WebDriverWait wait;

    private void loginViaSSO() throws IOException {

        driver = Driver.getBrowser();
        driver.manage().window().maximize();
        driver.get(Constants.appurl1);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        SSOLandingPage landing = new SSOLandingPage(driver);
        landing.clickProceedToSSO();

        OktaLoginPage okta = new OktaLoginPage(driver);
        Assert.assertTrue(okta.isOktaLoginPageLoaded());
        okta.login(Constants.username, Constants.password);
    }

    /* =========================================================
       TC-SSO-001: Organisation selection screen appears
       ========================================================= */
    @Test
    public void TC_SSO_001_validateOrganisationSelection() throws IOException {

        loginViaSSO();

        SSOSessionPage session = new SSOSessionPage(driver);
        Assert.assertTrue(
            session.isOrganisationSelectionDisplayed(),
            "Organisation selection screen not displayed"
        );
    }

    /* =========================================================
       TC-SSO-002: Organisation selection confirmation
       ========================================================= */
    @Test
    public void TC_SSO_002_validateOrganisationSelectionSuccess() throws IOException {

        loginViaSSO();

        SSOSessionPage session = new SSOSessionPage(driver);
        session.selectFirstOrganisation();

        Assert.assertTrue(
            session.isUserStillLoggedIn(),
            "User not redirected to dashboard after organisation selection"
        );
    }

    /* =========================================================
       TC-SSO-004: Category-role mapping validation
       ========================================================= */
    @Test
    public void TC_SSO_004_validateCategoryRoleMapping() throws IOException {

        loginViaSSO();

        SSOSessionPage session = new SSOSessionPage(driver);
        session.selectFirstOrganisation();

        Assert.assertTrue(
            session.isRestrictedCategoryBlocked(),
            "Restricted category is accessible"
        );
    }

    /* =========================================================
       TC-SSO-008: Session continuity on browser refresh
       ========================================================= */
    @Test
    public void TC_SSO_008_validateSessionOnBrowserRefresh() throws IOException {

        loginViaSSO();

        SSOSessionPage session = new SSOSessionPage(driver);
        session.selectFirstOrganisation();

        session.refreshBrowser();

        Assert.assertTrue(
            session.isUserStillLoggedIn(),
            "Session lost after browser refresh"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
