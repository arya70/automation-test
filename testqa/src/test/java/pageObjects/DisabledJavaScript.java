package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import generic.AssertHelper;
import generic.KeyboardHelper;
import generic.WebLibrary;
import generic.Constants;
import generic.BaseLoginElements;

public class DisabledJavaScript extends BaseLoginElements {

    WebDriver driver;

    // ======================================
    // CONSTRUCTOR
    // ======================================
    public DisabledJavaScript(WebDriver driver) {
        super(driver);             // initialize BaseLoginElements
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======================================
    // MAIN ACTION METHOD
    // ======================================
    public void attemptLoginWithDisabledJS() throws Exception {

        // Reset keyboard (same approach as other POMs)
        KeyboardHelper.resetKeyboardLanguage();

        WebLibrary lib = new WebLibrary(driver);
        lib.waitForElementPresent(usernameinput);


        // Step 1 → Enter login details (from Constants file)
        usernameinput.sendKeys(Constants.usernameinput);
        passwordinput.sendKeys(Constants.passwordinput);

        // Step 2 → Click login
        loginbutton.click();
        Thread.sleep(1500);

        // Step 3 → Validation
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL (JavaScript Disabled): " + currentURL);

        // EXPECTATION:
        // With JavaScript disabled → login MUST NOT redirect.
        AssertHelper.verifyTrue(
                currentURL.contains("login"),
                "JavaScript Disabled Test FAILED — Login should NOT proceed without JavaScript!"
        );
    }
}
