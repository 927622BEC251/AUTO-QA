package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * CheckoutPage - Automation Exercise checkout page POM
 * Handles checkout form filling and order placement
 */
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By firstNameField = By.name("first_name");
    private By lastNameField = By.name("last_name");
    private By companyField = By.name("company");
    private By addressField = By.name("address1");
    private By stateField = By.name("state");
    private By cityField = By.name("city");
    private By zipCodeField = By.name("zipcode");
    private By phoneField = By.name("mobile_number");
    private By commentField = By.name("comments");
    
    private By cardNameField = By.name("name_on_card");
    private By cardNumberField = By.name("card_number");
    private By cardCvcField = By.name("cvc");
    private By cardExpiryMonthField = By.name("expiry_month");
    private By cardExpiryYearField = By.name("expiry_year");
    
    private By placeOrderButton = By.id("submit");
    private By orderConfirmationMessage = By.xpath("//p[contains(text(), 'Order Placed Successfully')]");

    /**
     * Fill delivery address form
     */
    public void fillDeliveryAddress(String firstName, String lastName, String address, 
                                   String city, String state, String zipCode, String phone) {
        WebElement firstNameElem = driver.findElement(firstNameField);
        sendKeysToElement(firstNameElem, firstName);
        
        WebElement lastNameElem = driver.findElement(lastNameField);
        sendKeysToElement(lastNameElem, lastName);
        
        WebElement addressElem = driver.findElement(addressField);
        sendKeysToElement(addressElem, address);
        
        WebElement cityElem = driver.findElement(cityField);
        sendKeysToElement(cityElem, city);
        
        WebElement stateElem = driver.findElement(stateField);
        sendKeysToElement(stateElem, state);
        
        WebElement zipElem = driver.findElement(zipCodeField);
        sendKeysToElement(zipElem, zipCode);
        
        WebElement phoneElem = driver.findElement(phoneField);
        sendKeysToElement(phoneElem, phone);
    }

    /**
     * Fill payment details
     */
    public void fillPaymentDetails(String cardName, String cardNumber, String cvc, String month, String year) {
        WebElement cardNameElem = driver.findElement(cardNameField);
        sendKeysToElement(cardNameElem, cardName);
        
        WebElement cardNumElem = driver.findElement(cardNumberField);
        sendKeysToElement(cardNumElem, cardNumber);
        
        WebElement cvcElem = driver.findElement(cardCvcField);
        sendKeysToElement(cvcElem, cvc);
        
        WebElement monthElem = driver.findElement(cardExpiryMonthField);
        sendKeysToElement(monthElem, month);
        
        WebElement yearElem = driver.findElement(cardExpiryYearField);
        sendKeysToElement(yearElem, year);
    }

    /**
     * Place order
     */
    public void placeOrder() {
        WebElement placeOrderBtn = driver.findElement(placeOrderButton);
        clickElement(placeOrderBtn);
    }

    /**
     * Verify order confirmation
     */
    public boolean isOrderConfirmationDisplayed() {
        try {
            WebElement confirmMsg = driver.findElement(orderConfirmationMessage);
            waitForElement(confirmMsg);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get confirmation message
     */
    public String getConfirmationMessage() {
        WebElement element = driver.findElement(orderConfirmationMessage);
        return getElementText(element);
    }
}
