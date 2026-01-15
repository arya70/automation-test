package generic;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseLoginElements {

    // WebDriver declaration (to be used by child test classes)
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        // Browser initialization
        driver = Driver.getBrowser();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        System.out.println("Fresh Browser Launched");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        // ❌ NO SCREENSHOT LOGIC HERE
        // ✅ ONLY driver cleanup

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("🧹 Browser closed successfully");
            } catch (Exception e) {
                System.out.println("⚠️ Error while closing browser: " + e.getMessage());
            }
        }
    }
}
