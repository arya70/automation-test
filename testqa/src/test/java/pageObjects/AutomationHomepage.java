package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationHomepage {

    WebDriver driver;

    // REAL CONSTRUCTOR (MUST NOT have a void return type!)
    public AutomationHomepage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // LOGIN PAGE IDENTIFIER
    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailTextbox;

    // LOGGED-IN IDENTIFIER
    @FindBy(xpath = "//a[contains(text(),'Logged in as')]")
    private WebElement loggedInAs;

    // -------------------------------
    // Check user NOT logged in
    // -------------------------------
    public boolean isUserNotLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginEmailTextbox));
        return loginEmailTextbox.isDisplayed();
    }

    // -------------------------------
    // Check user IS logged in
    // -------------------------------
    public boolean isUserLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(loggedInAs));
            return true;  // user is logged in
        } 
        catch (Exception e) {
            return false; // not logged in
        }
    }
}
