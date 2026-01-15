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
import pageObjects.DisabledJavaScript;

public class testcaseDisabledJavaScript {

     WebDriver driver;

    // ===========================================
    // BEFORE: Run ONLY for this class
    // Creates a NEW Browser with JS disabled
    // ===========================================
    @BeforeMethod
    public void setupBrowserWithDisabledJS() {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.managed_default_content_settings.javascript", 2); 
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        System.out.println("Browser started with JavaScript disabled");
    }

    // ===========================================
    // TEST
    // ===========================================
    @Test(priority = 7)
    public void verifyLoginWithDisabledJS() throws Throwable {

        driver.get(Constants.appurl1);

        DisabledJavaScript obj = PageFactory.initElements(driver, DisabledJavaScript.class);

        obj.attemptLoginWithDisabledJS();
    }

    // ===========================================
    // AFTER: Kill driver so JS disable won't affect other tests
    // ===========================================
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println(" Browser closed (JS-disabled instance removed)");
        }
    }
}
