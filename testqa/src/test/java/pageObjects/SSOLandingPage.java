package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SSOLandingPage {

    WebDriver driver;
    WebDriverWait wait;

    public SSOLandingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'ep-login-primary-card')]//a[contains(@class,'ep-btn-primary')]")
    private WebElement proceedToSSO;

    public void clickProceedToSSO() {

        wait.until(ExpectedConditions.elementToBeClickable(proceedToSSO));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", proceedToSSO);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", proceedToSSO);
    }
}
