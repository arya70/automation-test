package testCases;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.AutomationLogin1;
import pageObjects.AutomationDashboard;

public class testcase_2 {

    static WebDriver driver;
    static Set<Cookie> savedCookies = new HashSet<>();

    // ------------------------
    // TC_SSO_001 - Initial Login
    // ------------------------
    @Test(enabled = false)
    public void initialLogin() throws IOException, Throwable {

        driver = Driver.getBrowser();
        driver.get(Constants.appurl1);

        AutomationLogin1 login = new AutomationLogin1(driver);
        login.LoginToApp1(driver);

        // save cookies after successful login
        savedCookies = driver.manage().getCookies();
        System.out.println("Cookies saved: " + savedCookies.size());

        driver.quit();
    }

    // ------------------------
    // TC_SSO_002 - Already Logged In (Active Session Check)
    // ------------------------
    @Test(enabled = false, dependsOnMethods = "initialLogin")
    public void alreadyLoggedInCheck() throws IOException {

        driver = Driver.getBrowser();

        // Step 1: open base login page first
        driver.get(Constants.appurl1);

        // Step 2: add saved cookies to new browser session
        for (Cookie cookie : savedCookies) {
            driver.manage().addCookie(cookie);
        }

        // Step 3: refresh to apply session cookies
        driver.navigate().refresh();

        // Step 4: verify user is redirected to dashboard automatically
        AutomationDashboard dashboard = new AutomationDashboard(driver);
        dashboard.Logged(driver);

        System.out.println("User successfully auto-logged in using active session.");
        driver.quit();
    }
}
