package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtil - Captures screenshots on test failure
 * Screenshots are stored in /screenshots/ folder with timestamp
 */
public class ScreenshotUtil {

    /**
     * Capture screenshot with timestamp
     */
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot captured: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error capturing screenshot");
        }

        return path;
    }

    /**
     * Ensure screenshots directory exists
     */
    public static void createScreenshotDirectory() {
        File directory = new File(System.getProperty("user.dir") + "/screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
