package stepDefinition;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import generic.Constants;
import generic.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.AutomationLogin1;


public class stepDef {
	static WebDriver driver;
	@Given("user launches the browser")
	public void user_launches_the_browser() {
	    driver = Driver.getBrowser();
	}



@When("user does the valid login")
public void user_does_the_valid_login() throws IOException, Throwable 
{
	
	driver.get(Constants.appurl1);
	AutomationLogin1 lg1 = PageFactory.initElements(driver, AutomationLogin1.class);
	lg1.LoginToApp1(driver);
}



}
