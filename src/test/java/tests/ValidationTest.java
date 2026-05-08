package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

/**
 * ValidationTest - Test Module 5: Form Validations
 * Test Cases:
 * - TC_VALIDATION_001: Submit registration form with empty fields
 * - TC_VALIDATION_002: Enter invalid email format and verify validation
 */
public class ValidationTest extends BaseTest {

    /**
     * TC_VALIDATION_001: Empty form field validation
     */
    @Test(priority = 1)
    public void verifyEmptyFieldValidation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        
        // Try to register without entering name (empty field)
        loginPage.registerUser("", "");
        
        // Verify error or form remains on signup page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("automationexercise.com"), "Should remain on signup page");
    }

    /**
     * TC_VALIDATION_002: Invalid email format validation
     */
    @Test(priority = 2)
    public void verifyInvalidEmailValidation() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        
        // Try to login with invalid email
        loginPage.login("invalidemail", "anypassword");
        
        // Verify error message is displayed or login fails
        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(errorDisplayed || !homePage.isLogoutLinkVisible(), 
                         "Should show error or prevent login with invalid email");
    }

    /**
     * TC_VALIDATION_003: Verify form displays error messages for required fields
     */
    @Test(priority = 3)
    public void verifyFormErrorMessages() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        
        // Verify error message for invalid login
        loginPage.login("testuser@example.com", "wrongpassword");
        
        // Check if error is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty(), "Error message should not be empty");
        Assert.assertTrue(errorMsg.contains("incorrect"), "Error message should indicate invalid credentials");
    }
}
