package sanityTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.ClearBrowserCacheDuringLogin;

public class testcaseClearBrowserCacheDuringLogin {


     WebDriver driver;

    @Test(priority = 6)
    public void clearCacheDuringLogin() throws Exception {

        driver = Driver.getBrowser();
        driver.get(Constants.appurl1);

        ClearBrowserCacheDuringLogin cacheTest = new ClearBrowserCacheDuringLogin(driver);


        cacheTest.clearCacheMidLogin();

        driver.quit();
    }
}
