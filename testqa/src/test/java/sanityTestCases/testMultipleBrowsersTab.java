package sanityTestCases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.MultipleBrowserTabsPage;

public class testMultipleBrowsersTab {
	
	 WebDriver driver;
    @Test(priority = 4)
    public void verifyMultipleTabsBehaviour() throws InterruptedException {

        driver = Driver.getBrowser();
        driver.get(Constants.appurl1); // example: https://automationexercise.com/login

        MultipleBrowserTabsPage page1 = new MultipleBrowserTabsPage(driver);

        // After login → homepage should be opened (URL shortens & removes /login)
        Thread.sleep(2000);
        String tab1URL = page1.getCurrentURL();
        Assert.assertTrue(tab1URL.contains("automationexercise.com"),
                "Tab 1 should redirect to homepage after login");

        // Step 2: Open new tab
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.open();");

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // Step 3: Open homepage in Tab 2
        driver.get("https://automationexercise.com/");
        MultipleBrowserTabsPage page2 = new MultipleBrowserTabsPage(driver);

        String tab2URL = page2.getCurrentURL();

        // Validation: session must be preserved → homepage visible
        Assert.assertTrue(tab2URL.contains("automationexercise.com"),
                "Tab 2 should open homepage and preserve session");

        driver.quit();
    }

}
