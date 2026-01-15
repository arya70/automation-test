package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OktaLoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public OktaLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    // ================= USERNAME =================
    @FindBy(xpath = "//span[contains(@class,'o-form-input-name-identifier')]//input[@name='identifier']")
    private WebElement username;

    // ================= KEEP SIGNED IN (CHECKBOX INPUT) =================
    @FindBy(xpath = "//input[@type='checkbox' and @name='rememberMe']")
    private WebElement keepMeSignedIn;

    // ================= NEXT BUTTON =================
    @FindBy(xpath = "//input[@type='submit' and @value='Next']")
    private WebElement nextButton;

    // ================= VERIFY / SIGN IN =================
    @FindBy(xpath = "//input[@type='submit' and (contains(@value,'Verify') or contains(@value,'Sign'))]")
    private WebElement verifyButton;

    // ================= PAGE LOAD CHECK =================
    public boolean isOktaLoginPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================= USERNAME STEP =================
    public void enterUsername(String user) {

        WebElement userField =
                wait.until(ExpectedConditions.elementToBeClickable(username));

        userField.click();
        userField.clear();
        userField.sendKeys(user);

        try {
            if (keepMeSignedIn.isDisplayed() && !keepMeSignedIn.isSelected()) {
                keepMeSignedIn.click();
            }
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    // ================= PASSWORD STEP =================
    public void enterPassword(String pass) {

        List<WebElement> passwordInputs =
                driver.findElements(By.name("credentials.passcode"));

        if (!passwordInputs.isEmpty()) {
            WebElement password = passwordInputs.get(0);
            wait.until(ExpectedConditions.visibilityOf(password));
            password.clear();
            password.sendKeys(pass);

            wait.until(ExpectedConditions.elementToBeClickable(verifyButton)).click();
        } else {
            System.out.println("Password step skipped (MFA or passwordless)");
        }
    }

    // ================= COMPLETE LOGIN =================
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
    }
}
