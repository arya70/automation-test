package pageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutomationDashboard {

    public AutomationDashboard(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//a[normalize-space()='Delete Account']")
    private WebElement  DeleteAccount ;

    // Wait for dashboard to load
    public void waitForDashboardToLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(DeleteAccount));
    }

    // Validate dashboard display
    public void Logged(WebDriver driver) {
        waitForDashboardToLoad(driver); // important
        Assert.assertTrue(DeleteAccount.isDisplayed(),
            "User is NOT on the Dashboard — Already Logged-in check failed.");
    }

	public void LoginToApp1(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}
