package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * CartPage - Automation Exercise shopping cart page POM
 * Handles cart operations - add, remove, verify items
 */
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartItems = By.xpath("//tr[@class='cart_item']");
    private By productNameInCart = By.xpath("//td[@class='cart_product']/p");
    private By productPriceInCart = By.xpath("//td[@class='cart_price']/p");
    private By removeButtons = By.xpath("//a[@class='cart_quantity_delete']");
    private By proceedCheckoutButton = By.xpath("//a[@class='btn btn-default check_out']");
    private By emptyCartMessage = By.xpath("//p[contains(text(), 'Cart is empty')]");
    private By cartCount = By.xpath("//span[@id='cart-page']");
    private By quantityField = By.xpath("//input[@class='cart_quantity_input']");

    /**
     * Get number of items in cart
     */
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    /**
     * Get product name from cart
     */
    public String getCartProductName() {
        WebElement element = driver.findElement(productNameInCart);
        return getElementText(element);
    }

    /**
     * Get product price from cart
     */
    public String getCartProductPrice() {
        WebElement element = driver.findElement(productPriceInCart);
        return getElementText(element);
    }

    /**
     * Remove first product from cart
     */
    public void removeFirstProductFromCart() {
        List<WebElement> removeButtons = driver.findElements(this.removeButtons);
        if (!removeButtons.isEmpty()) {
            clickElement(removeButtons.get(0));
        }
    }

    /**
     * Proceed to checkout
     */
    public void proceedToCheckout() {
        WebElement checkoutBtn = driver.findElement(proceedCheckoutButton);
        clickElement(checkoutBtn);
    }

    /**
     * Verify cart is empty
     */
    public boolean isCartEmpty() {
        try {
            WebElement emptyMsg = driver.findElement(emptyCartMessage);
            waitForElement(emptyMsg);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify cart has items
     */
    public boolean hasCartItems() {
        return getCartItemCount() > 0;
    }
}
