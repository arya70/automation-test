package generic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotHelper {

    private static final String BASE_PATH =
            System.getProperty("user.dir") + "/test-output/screenshots/";

    // ✅ For TestListener (PASS / FAIL)
    public static String captureScreenshot(WebDriver driver, String testName) {
        return save(driver, testName, "FINAL");
    }

    // ✅ For step-level screenshots
    public static String captureStep(WebDriver driver,
                                     String testName,
                                     String stepName) {
        return save(driver, testName, stepName);
    }

    // 🔒 Internal reusable method
    private static String save(WebDriver driver,
                               String testName,
                               String stepName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String path = BASE_PATH
                + testName + "_"
                + stepName + "_"
                + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
