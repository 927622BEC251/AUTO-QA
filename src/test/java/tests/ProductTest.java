package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import utilities.JsonDataReader;

/**
 * ProductTest - Test Module 2: Product Search and Browse
 * Test Cases:
 * - TC_PRODUCT_001: Search product by keyword
 * - TC_PRODUCT_002: Verify category products are displayed
 * - TC_PRODUCT_003: Verify product name and price
 */
public class ProductTest extends BaseTest {

    /**
     * TC_PRODUCT_001: Search for a product by keyword and verify results
     */
    @Test(priority = 1)
    public void verifyProductSearch() {
        HomePage homePage = new HomePage(driver);
        String keyword = JsonDataReader.getValue("productSearch", "keyword");
        
        // Search for product
        homePage.searchProduct(keyword);

        ProductPage productPage = new ProductPage(driver);
        
        // Verify products are displayed
        Assert.assertTrue(productPage.areProductsDisplayed(), "Products should be displayed in search results");
        Assert.assertTrue(productPage.getProductCount() > 0, "Product count should be greater than 0");
    }

    /**
     * TC_PRODUCT_002: Navigate to a category and verify products are listed
     */
    @Test(priority = 2)
    public void verifyProductCategory() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        
        // Click on a category (Women)
        productPage.clickCategory("Women");

        // Verify products are displayed
        Assert.assertTrue(productPage.areProductsDisplayed(), "Products should be displayed for selected category");
        Assert.assertTrue(productPage.getProductCount() > 0, "Category should have products");
    }

    /**
     * TC_PRODUCT_003: Open a product detail page and verify name and price are shown
     */
    @Test(priority = 3)
    public void verifyProductDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.clickProductsLink();

        ProductPage productPage = new ProductPage(driver);
        
        // Get product details from list
        String productNameFromList = productPage.getFirstProductName();
        String productPriceFromList = productPage.getFirstProductPrice();
        
        Assert.assertNotNull(productNameFromList, "Product name should not be null");
        Assert.assertNotNull(productPriceFromList, "Product price should not be null");
        Assert.assertFalse(productNameFromList.isEmpty(), "Product name should not be empty");
        Assert.assertFalse(productPriceFromList.isEmpty(), "Product price should not be empty");
    }
}
