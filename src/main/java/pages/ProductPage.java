package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * ProductPage - Automation Exercise products page POM
 * Handles product search, browse, and product details
 */
public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private By productList = By.xpath("//div[@class='product-image-wrapper']");
    private By productNames = By.xpath("//p[@class='product-name']/a");
    private By productPrices = By.xpath("//p[@class='product-name']/a/following::h2[@class='product-price']");
    private By addToCartButtons = By.xpath("//a[contains(text(), 'Add to cart')]");
    private By viewProductButton = By.xpath("//a[contains(text(), 'View Product')]");
    private By productTitle = By.xpath("//h2");
    private By productPrice = By.xpath("//span/span");
    private By categoryLinks = By.xpath("//div[@class='left-sidebar']//a");
    private By noProductsMessage = By.xpath("//p[contains(text(), 'No products found')]");

    /**
     * Get count of products displayed
     */
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productList);
        return products.size();
    }

    /**
     * Get first product name
     */
    public String getFirstProductName() {
        WebElement element = driver.findElements(productNames).get(0);
        return getElementText(element);
    }

    /**
     * Get first product price
     */
    public String getFirstProductPrice() {
        WebElement element = driver.findElements(productPrices).get(0);
        return getElementText(element);
    }

    /**
     * Click on first product to view details
     */
    public void clickFirstProduct() {
        List<WebElement> viewButtons = driver.findElements(viewProductButton);
        if (!viewButtons.isEmpty()) {
            clickElement(viewButtons.get(0));
        }
    }

    /**
     * Add first product to cart
     */
    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            clickElement(buttons.get(0));
        }
    }

    /**
     * Get product detail title
     */
    public String getProductDetailTitle() {
        WebElement element = driver.findElement(productTitle);
        return getElementText(element);
    }

    /**
     * Get product detail price
     */
    public String getProductDetailPrice() {
        WebElement element = driver.findElement(productPrice);
        return getElementText(element);
    }

    /**
     * Click on a category
     */
    public void clickCategory(String categoryName) {
        List<WebElement> categories = driver.findElements(categoryLinks);
        for (WebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                clickElement(category);
                break;
            }
        }
    }

    /**
     * Verify if products are displayed
     */
    public boolean areProductsDisplayed() {
        return getProductCount() > 0;
    }
}
