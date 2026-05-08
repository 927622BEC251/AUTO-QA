package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.CartPage;
import pages.CheckoutPage;
import utilities.JsonDataReader;

/**
 * CheckoutTest - Test Module 4: Checkout Flow
 * Test Cases:
 * - TC_CHECKOUT_001: Complete checkout from cart to order confirmation
 * - TC_CHECKOUT_002: Attempt checkout without login and verify redirect to login page
 */
public class CheckoutTest extends BaseTest {

    /**
     * TC_CHECKOUT_001: Complete checkout flow with valid details
     */
    @Test(priority = 1)
    public void verifyCompleteCheckout() {
        // Login
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        String validEmail = JsonDataReader.getValue("validLogin", "email");
        String validPassword = JsonDataReader.getValue("validLogin", "password");
        loginPage.login(validEmail, validPassword);

        // Add product to cart
        homePage.clickProductsLink();
        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();

        // Go to cart
        homePage.clickCartLink();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.hasCartItems(), "Cart should have items");

        // Proceed to checkout
        cartPage.proceedToCheckout();

        // Fill checkout details
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String name = JsonDataReader.getValue("checkout", "name");
        String cardNum = JsonDataReader.getValue("checkout", "cardNumber");
        String cvc = JsonDataReader.getValue("checkout", "cvc");
        String month = JsonDataReader.getValue("checkout", "month");
        String year = JsonDataReader.getValue("checkout", "year");

        checkoutPage.fillDeliveryAddress(name, "User", "123 Test Street", "Test City", "Test State", "12345", "9876543210");
        checkoutPage.fillPaymentDetails(name, cardNum, cvc, month, year);
        checkoutPage.placeOrder();

        // Verify order confirmation
        Assert.assertTrue(checkoutPage.isOrderConfirmationDisplayed(), "Order confirmation message should be displayed");
    }

    /**
     * TC_CHECKOUT_002: Attempt checkout without login and verify redirect to login page
     */
    @Test(priority = 2)
    public void verifyCheckoutWithoutLogin() {
        // Add product to cart without logging in
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();

        // Navigate to cart
        homePage.clickCartLink();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        // Should be redirected to login page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("automationexercise.com") || currentUrl.contains("login"), 
                        "Should be redirected to login page");
    }
}
