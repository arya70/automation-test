package pageObjects;

import org.openqa.selenium.WebDriver;

public class MultipleBrowserTabsPage {
	 private WebDriver driver;

	    public MultipleBrowserTabsPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public String getCurrentURL() {
	        return driver.getCurrentUrl();
	    }
	}


