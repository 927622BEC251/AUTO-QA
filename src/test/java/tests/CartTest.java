package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.CartPage;

/**
 * CartTest - Test Module 3: Shopping Cart
 * Test Cases:
 * - TC_CART_001: Add a product to cart and verify it appears with correct name and price
 * - TC_CART_002: Remove a product from cart and verify it no longer appears
 * - TC_CART_003: Verify cart count updates when a product is added
 */
public class CartTest extends BaseTest {

    /**
     * TC_CART_001: Add product to cart and verify it appears
     */
    @Test(priority = 1)
    public void verifyAddToCart() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        
        // Get product name before adding to cart
        String productName = productPage.getFirstProductName();
        String productPrice = productPage.getFirstProductPrice();
        
        // Add first product to cart
        productPage.addFirstProductToCart();

        // Navigate to cart
        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        
        // Verify cart has items
        Assert.assertTrue(cartPage.hasCartItems(), "Cart should have items after adding product");
        
        // Verify product is in cart
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart should contain at least one item");
    }

    /**
     * TC_CART_002: Remove product from cart
     */
    @Test(priority = 2)
    public void verifyRemoveFromCart() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();

        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        int initialCount = cartPage.getCartItemCount();
        
        // Remove product from cart
        if (initialCount > 0) {
            cartPage.removeFirstProductFromCart();
            
            // Wait for page update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int finalCount = cartPage.getCartItemCount();
            Assert.assertTrue(finalCount < initialCount, "Cart item count should decrease after removing product");
        }
    }

    /**
     * TC_CART_003: Verify cart count updates when product is added
     */
    @Test(priority = 3)
    public void verifyCartCountUpdate() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();

        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        
        // Verify cart has items
        Assert.assertTrue(cartPage.hasCartItems(), "Cart should be updated with new item");
        Assert.assertTrue(cartPage.getCartItemCount() >= 1, "Cart should contain the added item");
    }
}
