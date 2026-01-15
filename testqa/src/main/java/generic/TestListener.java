package generic;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private WebDriver resolveDriver(ITestResult result) {
        try {
            Object testClass = result.getInstance();

            if (testClass instanceof BaseLoginElements) {
                return ((BaseLoginElements) testClass).driver;
            }
        } catch (Exception e) {
            System.out.println("⚠️ Unable to fetch driver from BaseLoginElements");
        }

        // ✅ Fallback – SAFE
        return Driver.getDriver();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        WebDriver driver = resolveDriver(result);

        if (driver != null) {
            ScreenshotHelper.captureScreenshot(driver, result.getName());
            System.out.println("📸 Screenshot captured for PASSED test: " + result.getName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = resolveDriver(result);

        if (driver != null) {
            ScreenshotHelper.captureScreenshot(driver, result.getName());
            System.out.println("📸 Screenshot captured for FAILED test: " + result.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭ Test skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}
