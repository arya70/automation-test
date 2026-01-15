package generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver {

    private static WebDriver driver;   // keep static because multiple test cases use same accessor

    public static WebDriver getBrowser() {

        // -----------------------------
        // FIX 1: RECOVER FROM DEAD SESSION
        // -----------------------------
        if (driver != null) {
            try {
                driver.getTitle();  // simple command to check if session is alive
            } catch (Exception e) {
                System.out.println("⚠️ Old WebDriver session is dead → Creating new browser session...");
                driver = null;  // force recreation below
            }
        }

        // -----------------------------
        // EXISTING LOGIC (unchanged)
        // -----------------------------
        if (driver == null) {

            if (Constants.browser.equalsIgnoreCase("chrome")) {

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en-US");
                options.addArguments("--disable-features=Translate");
                options.addArguments("--force-device-scale-factor=1");

                driver = new ChromeDriver(options);
            }

            else if (Constants.browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }

            else {
                throw new RuntimeException("Invalid browser in Constants.browser!");
            }

            System.out.println("Fresh Browser Launched");

            WebLibrary lib = new WebLibrary(driver);
            lib.maximize();

            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Constants.globalwait)
            );
        }

        return driver;
    }

    // -----------------------------------------
    // ✅ ADDED: REQUIRED FOR SCREENSHOT LISTENER
    // -----------------------------------------
    public static WebDriver getDriver() {
        return driver;
    }

    // -----------------------------------------
    // ✅ ADDED: SAFE QUIT METHOD
    // -----------------------------------------
    public static void quitDriver() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println(" Exception during driver quit");
        } finally {
            driver = null;
        }
    }

    // Wrapper for driver.get()
    public static void get(String url) {
        driver.get(url);
    }
    
    
}
