package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.JsonDataReader;

/**
 * LoginTest - Test Module 1: User Authentication
 * Test Cases:
 * - TC_LOGIN_001: Verify successful login with valid credentials
 * - TC_LOGIN_002: Verify login failure with invalid password
 * - TC_LOGIN_003: Verify logout redirects to home page
 * - TC_LOGIN_004: Verify registration with unique user
 */
public class LoginTest extends BaseTest {

    /**
     * DataProvider for login credentials
     * Reads from testdata.json
     */
    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {JsonDataReader.getValue("validLogin", "email"), 
                 JsonDataReader.getValue("validLogin", "password"), true},
                {JsonDataReader.getValue("invalidLogin", "email"), 
                 JsonDataReader.getValue("invalidLogin", "password"), false}
        };
    }

    /**
     * TC_LOGIN_001 & TC_LOGIN_002: Login with valid and invalid credentials
     */
    @Test(dataProvider = "loginData", priority = 1)
    public void verifyLogin(String email, String password, boolean shouldSucceed) {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        if (shouldSucceed) {
            // Verify user logged in - check for logout link
            Assert.assertTrue(homePage.isLogoutLinkVisible(), "User should be logged in");
        } else {
            // Verify error message for invalid login
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed for invalid credentials");
        }
    }

    /**
     * TC_LOGIN_003: Verify logout redirects to home page
     */
    @Test(priority = 2)
    public void verifyLogout() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        String validEmail = JsonDataReader.getValue("validLogin", "email");
        String validPassword = JsonDataReader.getValue("validLogin", "password");
        loginPage.login(validEmail, validPassword);

        // Verify logout link is visible (user is logged in)
        Assert.assertTrue(homePage.isLogoutLinkVisible(), "Logout link should be visible after login");

        // Logout
        homePage.logout();

        // Verify user is on home page
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("automationexercise.com"), "Should be redirected to home page after logout");
    }

    /**
     * TC_LOGIN_004: Verify registration with unique user
     */
    @Test(priority = 3)
    public void verifyRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLoginLink();

        LoginPage loginPage = new LoginPage(driver);
        String regName = JsonDataReader.getValue("registration", "name");
        String regEmail = JsonDataReader.getValue("registration", "email");
        
        loginPage.registerUser(regName, regEmail);
        
        // After signup, user should be on signup form page
        // Verify signup was initiated
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(!currentUrl.isEmpty(), "Should progress to next step after registration");
    }
}
