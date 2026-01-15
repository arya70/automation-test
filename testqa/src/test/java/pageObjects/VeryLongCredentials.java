package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import generic.AssertHelper;
import generic.KeyboardHelper;
import generic.WebLibrary;
import generic.BaseLoginElements;

public class VeryLongCredentials extends BaseLoginElements {

    WebDriver driver;
    WebLibrary lib;

    public VeryLongCredentials(WebDriver driver) {
        super(driver);       
        this.driver = driver;
        this.lib = new WebLibrary(driver);
        PageFactory.initElements(driver, this);
    }

    // Optional error message
    	@FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
    	private WebElement loginErrorMsg;

    // =====================================================================
    // TEST EXTREMELY LONG USERNAME + PASSWORD
    // =====================================================================
    public void enterVeryLongCredentials() throws Exception {

        // Step 1 → Reset keyboard
        KeyboardHelper.resetKeyboardLanguage();

        // Step 2 → Wait for page + input fields
        lib.waitForElementPresent(usernameinput);

        // Step 3 → Generate extremely long input
        String longUsername  = "A".repeat(1000);
        String longPassword  = "B".repeat(1000);

        usernameinput.clear();
        usernameinput.sendKeys(longUsername);

        passwordinput.clear();
        passwordinput.sendKeys(longPassword);

        // Step 4 → Attempt login
        loginbutton.click();
        Thread.sleep(1500);

        // Step 5 → Validate login should NOT succeed
        String url = driver.getCurrentUrl();
        System.out.println("DEBUG URL after long credentials: " + url);

        boolean errorShown = false;
        try {
             errorShown = loginErrorMsg.isDisplayed();
        } catch (Exception ignored) {}

        AssertHelper.verifyTrue(
            url.contains("login") || errorShown,
            "Login should fail when extremely long credentials are used!"
        );

        System.out.println(" Very long credentials validation passed");
    }
}
