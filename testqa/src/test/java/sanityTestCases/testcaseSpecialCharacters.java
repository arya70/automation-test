package sanityTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import generic.Constants;
import generic.Driver;
import pageObjects.SpecialCharacters;

public class testcaseSpecialCharacters {

     WebDriver driver;

    @Test(priority = 2)
    public void specialchars() throws IOException, InterruptedException {

        driver = Driver.getBrowser();
        driver.get(Constants.appurl1);

        SpecialCharacters sc = new SpecialCharacters(driver);

        sc.verifySpecialCharactersLogin();

        driver.quit();
    }
}
