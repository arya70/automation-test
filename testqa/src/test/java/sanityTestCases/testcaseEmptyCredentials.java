package sanityTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.EmptyCredentials;

public class testcaseEmptyCredentials {

     WebDriver driver;

    @Test(priority = 1)
    public void emptycreds() throws IOException, InterruptedException {

        driver = Driver.getBrowser();
        driver.get(Constants.appurl1); // https://automationexercise.com/login

        EmptyCredentials loginPage = PageFactory.initElements(driver, EmptyCredentials.class);

        loginPage.verifyEmptyCredentials();

        driver.quit();
    }
}
