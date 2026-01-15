package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SSOSessionPage {

    WebDriver driver;
    WebDriverWait wait;

    public SSOSessionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /* ================= ORGANISATION SELECTION ================= */

    By organisationCards = By.xpath("//div[contains(@class,'org-card')]");
    By continueButton = By.xpath("//button[contains(text(),'Continue')]");

    public boolean isOrganisationSelectionDisplayed() {
        return wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(organisationCards)).size() > 0;
    }

    public void selectFirstOrganisation() {
        List<WebElement> orgs = driver.findElements(organisationCards);
        orgs.get(0).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    /* ================= ROLE & CATEGORY ================= */

    By roleLabel = By.id("user-role");
    By restrictedMenu = By.id("restricted-menu");

    public String getAssignedRole() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(roleLabel))
                .getText();
    }

    public boolean isRestrictedCategoryBlocked() {
        return driver.findElements(restrictedMenu).size() == 0;
    }

    /* ================= SESSION ================= */

    public void refreshBrowser() {
        driver.navigate().refresh();
    }

    public boolean isUserStillLoggedIn() {
        return driver.getCurrentUrl().contains("exchange")
                || driver.getCurrentUrl().contains("dashboard");
    }

    /* ================= TOKEN REFRESH ================= */

    By anyDashboardAction =
            By.xpath("//a[contains(text(),'Orders') or contains(text(),'Trades')]");

    public void waitForTokenRefreshWindow() {
        try {
            // simulate idle window (2 minutes)
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean performActionAfterIdle() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(anyDashboardAction))
                    .click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* ================= SESSION EXPIRY ================= */

    public boolean isRedirectedToSSO() {
        return driver.getCurrentUrl().contains("okta")
                || driver.getCurrentUrl().contains("login");
    }

    public boolean isSessionExpired() {
        return isRedirectedToSSO();
    }
}
