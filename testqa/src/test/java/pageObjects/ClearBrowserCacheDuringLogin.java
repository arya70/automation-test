package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.AssertHelper;
import generic.ClearCacheHelper;
import generic.Constants;
import generic.KeyboardHelper;
import generic.WebLibrary;
import generic.BaseLoginElements;

public class ClearBrowserCacheDuringLogin extends BaseLoginElements {

    WebDriver driver;

    public ClearBrowserCacheDuringLogin(WebDriver driver) {
        super(driver);                    // initialize BaseLoginElements
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    private WebElement loginErrorMsg;

    // ===============================
    // MAIN TEST METHOD
    // ===============================
    public void clearCacheMidLogin() throws Exception {

        // Reset keyboard language
        KeyboardHelper.resetKeyboardLanguage();

        WebLibrary lib = new WebLibrary(driver);
        lib.waitForElementPresent(usernameinput);


        // Step 1 → enter login data but do NOT submit yet
        usernameinput.sendKeys(Constants.usernameinput);
        passwordinput.sendKeys(Constants.passwordinput);


        // Step 2 → Clear browser cache mid-flow
        ClearCacheHelper.clearBrowserCache(driver);

        Thread.sleep(1000);

        // Step 3 → Now attempt login
        loginbutton.click();

        Thread.sleep(2000);

        String finalUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: URL After Cache Clear Attempt → " + finalUrl);

        // Step 4 → Validate expected behaviour
        boolean errorDisplayed = false;

        try {
            errorDisplayed = loginErrorMsg.isDisplayed();
        } catch (Exception ignored) {}

        AssertHelper.verifyTrue(
            finalUrl.contains("login") || errorDisplayed,
            "Login should FAIL after clearing browser cache mid-flow!"
        );
    }
}
