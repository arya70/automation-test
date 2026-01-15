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

public class SpecialCharacters extends BaseLoginElements {

    WebDriver driver;
    WebLibrary lib;

    public SpecialCharacters(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.lib = new WebLibrary(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginErrorMsg;

    // ===============================================================
    // TEST: Invalid special-character credentials
    // ===============================================================
    public void verifySpecialCharactersLogin() throws IOException, InterruptedException {

        KeyboardHelper.resetKeyboardLanguage();

        lib.waitForElementPresent(usernameinput);

        // ❗ VERY IMPORTANT: Use invalid special-character values
        usernameinput.clear();
        passwordinput.clear();

        usernameinput.sendKeys("invalid@@@mail.com");
        passwordinput.sendKeys("!@#$%^&*()<>?/");

        loginbutton.click();
        Thread.sleep(1200);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG URL: " + currentUrl);

        boolean errorShown = false;
        try {
            errorShown = loginErrorMsg.isDisplayed();
        } catch (Exception ignored) {}

        // Assertion: Login MUST FAIL
        AssertHelper.verifyTrue(
                currentUrl.contains("login") || errorShown,
                "Login should NOT succeed with invalid special character credentials!"
        );

        System.out.println("✔ Special characters login validation passed");
    }
}
