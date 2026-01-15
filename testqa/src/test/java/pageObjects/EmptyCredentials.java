package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.AssertHelper;
import generic.KeyboardHelper;
import generic.WebLibrary;
import generic.BaseLoginElements;

public class EmptyCredentials extends BaseLoginElements {

    WebDriver driver;
    WebLibrary lib;

    public EmptyCredentials(WebDriver driver) {
        super(driver);          // loads usernameinput, passwordinput, loginbutton from BaseLoginElements
        this.driver = driver;
        this.lib = new WebLibrary(driver);
        PageFactory.initElements(driver, this);
    }

    // Login error message
    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginErrorMsg;


    // ===============================================================
    // TEST: EMPTY USERNAME + EMPTY PASSWORD
    // ===============================================================
    public void verifyEmptyCredentials() throws IOException, InterruptedException {

        KeyboardHelper.resetKeyboardLanguage();

        // Wait for login page to load
        lib.waitForElementPresent(usernameinput);

        // CLEAR FIELDS FIRST
        usernameinput.clear();
        passwordinput.clear();

        // ENTER EMPTY VALUES (✱ FIXED)
        usernameinput.sendKeys("");
        passwordinput.sendKeys("");

        // CLICK LOGIN
        loginbutton.click();
        Thread.sleep(1000);

        // URL after clicking
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG URL: " + currentUrl);

        // CHECK ERROR MESSAGE
        boolean errorShown = false;
        try {
            errorShown = loginErrorMsg.isDisplayed();
        } catch (Exception e) {
            errorShown = false;
        }

        // ASSERTION (Login must fail)
        AssertHelper.verifyTrue(
            currentUrl.contains("login") || errorShown,
            "Login should NOT succeed with EMPTY credentials!"
        );

        System.out.println("Empty credentials validation passed");
    }
}
