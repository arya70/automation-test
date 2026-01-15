package sanityTestCases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import generic.Constants;
import pageObjects.AutomationLogin1;
import pageObjects.CookiesDisabled;

public class testcaseCookiesDisabled {

     WebDriver driver;

    // ==========================================================
    // BEFORE — Create browser with COOKIES DISABLED (isolated)
    // ==========================================================
    @BeforeMethod
    public void setupBrowserWithCookiesDisabled() {

        ChromeOptions options = new ChromeOptions();

        // Disable cookies fully
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.cookies", 2);
        prefs.put("network.cookie.cookieBehavior", 2);
        prefs.put("profile.block_third_party_cookies", true);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        System.out.println("🔥 Browser started with COOKIES DISABLED");
    }

    // ==========================================================
    // TEST CASE — Verify login behaviour when cookies disabled
    // ==========================================================
    @Test(priority = 8)
    public void verifyCookiesDisabled() throws Throwable {

        driver.get(Constants.appurl1);

        // Enter credentials
        AutomationLogin1 lp = PageFactory.initElements(driver, AutomationLogin1.class);
        lp.LoginToApp1(driver);

        // Validate login fails because cookies blocked
        CookiesDisabled cd = PageFactory.initElements(driver, CookiesDisabled.class);
        cd.verifyCookiesDisabledLogin();
    }

    // ==========================================================
    // AFTER — Close this isolated browser
    // ==========================================================
    @AfterMethod
    public void cleanup() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔥 Cookies-disabled browser closed");
        }
    }
}
