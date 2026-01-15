package generic;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebLibrary {

    private WebDriver driver;
    private WebDriverWait wait;

    public WebLibrary(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.globalwait));
    }

    // Maximize window
    public void maximize() {
        driver.manage().window().maximize();
        System.out.println("Window maximized successfully!");
    }

    // Implicit wait (page load)
    public void waitForPageToLoad() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.globalwait));
    }

    // Wait for element visible
    public void waitForElementPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            System.out.println("Element not found during wait!");
            e.printStackTrace();
        }
    }
}
