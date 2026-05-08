package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * HomePage - Automation Exercise home page POM
 * Handles navigation and home page elements
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By signupLoginLink = By.xpath("//a[contains(text(), 'Signup / Login')]");
    private By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");
    private By productsLink = By.xpath("//a[contains(text(), 'Products')]");
    private By cartLink = By.xpath("//a[contains(text(), 'Cart')]");
    private By searchBox = By.id("search_product");
    private By searchButton = By.id("submit_search");

    /**
     * Navigate to signup/login page
     */
    public void clickSignupLoginLink() {
        WebElement element = driver.findElement(signupLoginLink);
        clickElement(element);
    }

    /**
     * Logout from application
     */
    public void logout() {
        WebElement element = driver.findElement(logoutLink);
        clickElement(element);
    }

    /**
     * Navigate to Products page
     */
    public void clickProductsLink() {
        WebElement element = driver.findElement(productsLink);
        clickElement(element);
    }

    /**
     * Navigate to Cart page
     */
    public void clickCartLink() {
        WebElement element = driver.findElement(cartLink);
        clickElement(element);
    }

    /**
     * Search for a product
     */
    public void searchProduct(String productName) {
        WebElement searchElement = driver.findElement(searchBox);
        sendKeysToElement(searchElement, productName);
        WebElement searchBtn = driver.findElement(searchButton);
        clickElement(searchBtn);
    }

    /**
     * Verify logout link is visible
     */
    public boolean isLogoutLinkVisible() {
        try {
            WebElement element = driver.findElement(logoutLink);
            waitForElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
