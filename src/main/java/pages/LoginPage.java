package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * LoginPage - Automation Exercise login and signup page POM
 * Handles login, signup, and related validations
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By signupNameField = By.name("name");
    private By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    
    private By errorMessage = By.xpath("//p[contains(text(), 'Your email or password is incorrect')]");
    private By signupNameError = By.xpath("//span[contains(text(), 'Name')]");

    /**
     * Register a new user
     */
    public void registerUser(String name, String email) {
        WebElement nameField = driver.findElement(signupNameField);
        sendKeysToElement(nameField, name);
        
        WebElement emailField = driver.findElement(signupEmailField);
        sendKeysToElement(emailField, email);
        
        WebElement signupBtn = driver.findElement(signupButton);
        clickElement(signupBtn);
    }

    /**
     * Login with credentials
     */
    public void login(String email, String password) {
        WebElement emailField = driver.findElement(loginEmailField);
        sendKeysToElement(emailField, email);
        
        WebElement passwordField = driver.findElement(loginPasswordField);
        sendKeysToElement(passwordField, password);
        
        WebElement loginBtn = driver.findElement(loginButton);
        clickElement(loginBtn);
    }

    /**
     * Verify error message for invalid login
     */
    public boolean isErrorMessageDisplayed() {
        try {
            WebElement element = driver.findElement(errorMessage);
            waitForElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        WebElement element = driver.findElement(errorMessage);
        return getElementText(element);
    }

    /**
     * Verify signup name field error
     */
    public boolean isSignupNameErrorDisplayed() {
        try {
            WebElement element = driver.findElement(signupNameError);
            waitForElement(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
