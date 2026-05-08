package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;
import utilities.ExtentManager;

/**
 * BaseTest provides setup and teardown for all test classes
 * - Browser initialization using WebDriverManager
 * - Browser selection from config.properties
 * - Extent Report initialization
 */
public class BaseTest {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        String browserName = ConfigReader.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);

        ExtentManager.createTest(this.getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
