package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExchangeDashboard {

    WebDriver driver;

    // Stable dashboard locator (adjust as per your app)
    private By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard')]");

    public ExchangeDashboard(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardLoaded() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
