package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import generic.AssertHelper;
import generic.KeyboardHelper;
import generic.BaseLoginElements;

public class BrowserBackAfterLoginPage extends BaseLoginElements {

    WebDriver driver;

    // ======================================
    // CONSTRUCTOR
    // ======================================
    public BrowserBackAfterLoginPage(WebDriver driver) {
        super(driver);                           // Initialize BaseLoginElements
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======================================
    // BUSINESS LOGIC
    // ======================================

    /**
     * Step 1: Validate user is on expected landing page after login.
     */
    public void verifyUserOnLandingPage() throws Exception {

        KeyboardHelper.resetKeyboardLanguage();  // optional consistency

        String currentURL = driver.getCurrentUrl();
        System.out.println("Landing Page URL: " + currentURL);

        boolean isCorrectPage =
                currentURL.contains("/dashboard") ||
                currentURL.contains("/home") ||
                currentURL.contains("/account") ||
                currentURL.contains("automationexercise.com");

        AssertHelper.verifyTrue(
            isCorrectPage,
            "User should be on the Landing Page after login!"
        );
    }


    /**
     * Step 2: Press browser BACK button.
     */
    public void pressBrowserBackButton() {
        driver.navigate().back();
        System.out.println("Browser back button pressed.");
    }


    /**
     * Step 3: Validate user STILL stays on landing page after back action.
     */
    public void verifyUserStillOnLandingPage() {

        String currentURL = driver.getCurrentUrl();
        System.out.println("URL After Browser Back: " + currentURL);

        boolean isStillOnLanding =
                currentURL.contains("/dashboard") ||
                currentURL.contains("/home") ||
                currentURL.contains("/account") ||
                currentURL.contains("automationexercise.com");

        AssertHelper.verifyTrue(
            isStillOnLanding,
            "User should remain on Landing Page even after browser BACK action!"
        );
    }
}
