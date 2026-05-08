package listeners;

import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import base.BaseTest;

/**
 * TestListener - Implements ITestListener for test execution tracking
 * Captures screenshots on failure and logs to Extent Report
 */
public class TestListener implements ITestListener {

    private static WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        System.out.println("========== Test Execution Started ==========");
        ExtentManager.getInstance();
        ScreenshotUtil.createScreenshotDirectory();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("========== Test Execution Completed ==========");
        ExtentManager.flushReport();
        System.out.println("Report generated at: " + ExtentManager.getReportPath());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        ExtentManager.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        ExtentManager.getTest().log(Status.PASS, result.getName() + " - PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        
        // Try to capture screenshot
        try {
            Object testClass = result.getInstance();
            if (testClass instanceof BaseTest) {
                driver = ((BaseTest) testClass).driver;
                if (driver != null) {
                    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
                    ExtentManager.getTest().log(Status.FAIL, result.getName() + " - FAILED");
                    ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
                }
            }
        } catch (Exception e) {
            System.out.println("Error capturing screenshot: " + e.getMessage());
        }

        // Add exception to report
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        ExtentManager.getTest().log(Status.SKIP, result.getName() + " - SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test Failed but within success percentage: " + result.getName());
    }
}
