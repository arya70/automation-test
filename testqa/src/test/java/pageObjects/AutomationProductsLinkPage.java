package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AutomationProductsLinkPage {

    WebDriver driver;

    public AutomationProductsLinkPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // LOGIN LINK (Signup/Login)
    @FindBy(xpath = "//a[contains(text(),'Products')]")
    private WebElement productsLink;

    // Click Login link
    public void clickLoginLink() {
    	productsLink.click();
    }

    // Validate redirected to Products Page
    public void verifyRedirectedToProductsPage() {
        String currentURL = driver.getCurrentUrl();
        System.out.println("Redirected URL: " + currentURL);

        Assert.assertTrue(
            currentURL.contains("/products"),
            "User did NOT reach Products Page! Redirect failed."
        );
    }
}
