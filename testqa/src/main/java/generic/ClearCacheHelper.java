package generic;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ClearCacheHelper {
	  public static void clearBrowserCache(WebDriver driver) {
	        try {
	            ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
	            ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
	            driver.manage().deleteAllCookies();
	            Thread.sleep(500);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
