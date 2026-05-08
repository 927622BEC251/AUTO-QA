package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

/**
 * ExtentManager - Manages Extent Reports for test execution
 * Generates HTML report with pass/fail status and screenshots
 */
public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static final String REPORT_PATH = System.getProperty("user.dir") + "/reports/ExtentReport.html";

    /**
     * Initialize Extent Report
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createReport();
        }
        return extent;
    }

    /**
     * Create new report
     */
    private static void createReport() {
        File reportDir = new File(System.getProperty("user.dir") + "/reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH);
        sparkReporter.config().setReportName("AutoQA Pro - Test Report");
        sparkReporter.config().setDocumentTitle("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        extent.setSystemInfo("Base URL", ConfigReader.getProperty("baseUrl"));
    }

    /**
     * Create test in report
     */
    public static ExtentTest createTest(String testName) {
        test = getInstance().createTest(testName);
        return test;
    }

    /**
     * Get current test
     */
    public static ExtentTest getTest() {
        return test;
    }

    /**
     * Flush the report
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Get report path
     */
    public static String getReportPath() {
        return REPORT_PATH;
    }
}
