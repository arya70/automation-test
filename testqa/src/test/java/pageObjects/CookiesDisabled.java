package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.AssertHelper;
import generic.KeyboardHelper;
import generic.WebLibrary;
import generic.Constants;
import generic.BaseLoginElements;

public class CookiesDisabled extends BaseLoginElements {

    WebDriver driver;

    // ======================================
    // CONSTRUCTOR
    // ======================================
    public CookiesDisabled(WebDriver driver) {
        super(driver); 
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ======================================
    // LOCATORS (specific to this test)
    // ======================================
    @FindBy(xpath = "//div[contains(text(),'cookies') or contains(text(),'Cookies')]")
    private WebElement cookiesErrorMsg;


    // ======================================
    // MAIN ACTION METHOD
    // ======================================
    public void verifyCookiesDisabledLogin() throws Exception {

        // Reset keyboard input (consistent with your framework)
        KeyboardHelper.resetKeyboardLanguage();

        WebLibrary lib = new WebLibrary(driver);
        lib.waitForElementPresent(usernameinput);


        // Step 2 → Enter login details
        usernameinput.sendKeys(Constants.usernameinput);
        passwordinput.sendKeys(Constants.passwordinput);

        loginbutton.click();
        Thread.sleep(1000);

        // Step 3 → Validate error message when cookies are disabled
        try {

            boolean displayed = cookiesErrorMsg.isDisplayed();

            AssertHelper.verifyTrue(
                displayed,
                "Error message should appear when cookies are disabled!"
            );

            System.out.println("Cookies Disabled Error Message: " + cookiesErrorMsg.getText());

        } catch (Exception e) {

            AssertHelper.verifyTrue(
                false,
                "Login was allowed even when cookies are disabled! This is a defect."
            );
        }
    }
}
