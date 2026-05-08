package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage provides common utilities for all Page classes
 * - WebDriverWait functionality
 * - Explicit wait strategies
 * - Shared action methods
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Wait for an element to be visible
     */
    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for an element to be clickable
     */
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Get text from element after waiting for visibility
     */
    public String getElementText(WebElement element) {
        waitForElement(element);
        return element.getText();
    }

    /**
     * Click element after waiting for it to be clickable
     */
    public void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Send keys to an element after waiting for visibility
     */
    public void sendKeysToElement(WebElement element, String text) {
        waitForElement(element);
        element.clear();
        element.sendKeys(text);
    }
}
