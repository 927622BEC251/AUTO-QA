# AutoQA Pro - Selenium Java Automation Framework

## 📋 Project Overview

AutoQA Pro is a comprehensive, enterprise-grade Selenium automation framework built for the Automation Exercise e-commerce website. The framework follows industry best practices including the Page Object Model (POM), TestNG, and Extent Reports.

**Application Under Test:** [Automation Exercise](https://automationexercise.com)

---

## 🏗️ Project Structure

```
AutoQAPro/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # Common page utilities and waits
│   │   │   │   └── BaseTest.java          # Common test setup/teardown
│   │   │   │
│   │   │   ├── pages/
│   │   │   │   ├── HomePage.java          # Home page POM
│   │   │   │   ├── LoginPage.java         # Login/Signup page POM
│   │   │   │   ├── ProductPage.java       # Products page POM
│   │   │   │   ├── CartPage.java          # Shopping cart page POM
│   │   │   │   └── CheckoutPage.java      # Checkout page POM
│   │   │   │
│   │   │   ├── utilities/
│   │   │   │   ├── ConfigReader.java      # Read config.properties
│   │   │   │   ├── ScreenshotUtil.java    # Capture screenshots
│   │   │   │   ├── ExtentManager.java     # Extent Reports management
│   │   │   │   └── JsonDataReader.java    # JSON test data reader
│   │   │   │
│   │   │   └── listeners/
│   │   │       └── TestListener.java      # ITestListener implementation
│   │   │
│   │   └── resources/
│   │       ├── config.properties          # Browser, URL, timeouts config
│   │       └── testdata.json              # Test data in JSON format
│   │
│   └── test/
│       └── java/tests/
│           ├── LoginTest.java             # Authentication tests
│           ├── ProductTest.java           # Product search/browse tests
│           ├── CartTest.java              # Shopping cart tests
│           ├── CheckoutTest.java          # Checkout flow tests
│           └── ValidationTest.java        # Form validation tests
│
├── screenshots/                            # Failure screenshots directory
├── reports/                                # Extent Report output
├── pom.xml                                 # Maven configuration
└── testng.xml                              # TestNG suite configuration
```

---

## ✨ Key Features

✅ **Page Object Model (POM)** - Separate page classes for each page  
✅ **No Thread.sleep()** - Uses WebDriverWait and explicit waits  
✅ **WebDriverManager** - Automatic driver management  
✅ **TestNG Framework** - Annotations, DataProviders, Listeners  
✅ **Extent Reports** - HTML test reports with screenshots  
✅ **Screenshot on Failure** - Automatic screenshot capture  
✅ **JSON Data-Driven** - Test data from testdata.json  
✅ **Configuration Management** - Centralized config.properties  
✅ **Parallel Execution** - Multi-threaded test execution  

---

## 📦 Dependencies

- **Selenium WebDriver 4.20.0** - Browser automation
- **TestNG 7.10.2** - Test framework
- **WebDriverManager 5.8.0** - Driver management
- **ExtentReports 5.1.1** - Test reporting
- **JSON 20240303** - JSON processing
- **Apache Commons IO 2.15.1** - File utilities

---

## 🔧 Configuration

### config.properties

Located at: `src/main/resources/config.properties`

```properties
browser=chrome                          # Browser: chrome or firefox
baseUrl=https://automationexercise.com  # Application URL
implicitWait=10                         # Implicit wait in seconds
explicitWait=20                         # Explicit wait in seconds
headless=false                          # Headless mode: true or false
```

### testdata.json

Located at: `src/main/resources/testdata.json`

Contains test data for all test scenarios:
- Valid login credentials
- Invalid login credentials
- Registration data
- Product search keywords
- Checkout/payment details

---

## 🧪 Test Modules

### Module 1: User Authentication (LoginTest.java)
- **TC_LOGIN_001** - Login with valid credentials
- **TC_LOGIN_002** - Login failure with invalid password
- **TC_LOGIN_003** - Logout functionality
- **TC_LOGIN_004** - User registration

### Module 2: Product Search & Browse (ProductTest.java)
- **TC_PRODUCT_001** - Search product by keyword
- **TC_PRODUCT_002** - Browse products by category
- **TC_PRODUCT_003** - View product details

### Module 3: Shopping Cart (CartTest.java)
- **TC_CART_001** - Add product to cart
- **TC_CART_002** - Remove product from cart
- **TC_CART_003** - Verify cart count updates

### Module 4: Checkout Flow (CheckoutTest.java)
- **TC_CHECKOUT_001** - Complete checkout process
- **TC_CHECKOUT_002** - Checkout without login

### Module 5: Form Validations (ValidationTest.java)
- **TC_VALIDATION_001** - Empty field validation
- **TC_VALIDATION_002** - Invalid email validation
- **TC_VALIDATION_003** - Error message display

---

## 🚀 How to Run Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome or Firefox browser

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Method

```bash
mvn test -Dtest=LoginTest#verifyLogin
```

### Run with Different Browser

Edit `config.properties`:
```properties
browser=firefox
```

Then run:
```bash
mvn clean test
```

### Run in Headless Mode

Edit `config.properties`:
```properties
headless=true
```

---

## 📊 Test Reports

### Extent Report

Location: `reports/ExtentReport.html`

The report includes:
- Test execution summary
- Individual test status (Pass/Fail/Skip)
- Test execution time
- Screenshots on failure
- Exception details and stack traces
- Environment information

### Screenshots

Location: `screenshots/`

Screenshots are captured automatically on test failures with timestamp:
- Format: `TestName_yyyyMMddHHmmss.png`
- Example: `verifyLogin_20260508123011.png`

---

## 🏗️ Framework Architecture

### BasePage
Provides common utilities for all page classes:
- `waitForElement()` - Wait for element visibility
- `clickElement()` - Click with wait
- `sendKeysToElement()` - Type with clear and wait
- `getElementText()` - Get text with wait

### BaseTest
Provides common test setup/teardown:
- `@BeforeMethod` - Browser initialization
- `@AfterMethod` - Browser cleanup
- Reads browser from config.properties
- Uses WebDriverManager for automatic driver setup

### Page Classes
Each page has dedicated class with:
- Locators defined as private By variables
- Action methods for user interactions
- Validation methods to verify page state
- Inherits utilities from BasePage

### Utilities
- **ConfigReader** - Property file management
- **ScreenshotUtil** - Screenshot capture
- **ExtentManager** - Report initialization and management
- **JsonDataReader** - JSON test data loading

### Listeners
- **TestListener** - Implements ITestListener
- Captures screenshots on failure
- Logs events to Extent Report
- Tracks test execution start/end

---

## 📝 DataProvider Usage

### Inline DataProvider

```java
@DataProvider(name = "loginData")
public Object[][] loginData() {
    return new Object[][]{
        {"email@gmail.com", "password123", true},
        {"wrong@gmail.com", "wrongpass", false}
    };
}

@Test(dataProvider = "loginData")
public void testLogin(String email, String password, boolean shouldSucceed) {
    // Test code
}
```

### JSON DataProvider

```java
String email = JsonDataReader.getValue("validLogin", "email");
String password = JsonDataReader.getValue("validLogin", "password");
```

---

## 🔍 Key Implementation Details

### Page Object Model
- No WebDriver code in test classes
- All locators in page classes
- Reusable action methods
- Clear separation of concerns

### Wait Strategy
- No `Thread.sleep()` anywhere
- Uses `WebDriverWait` with `ExpectedConditions`
- Configurable timeout in config.properties
- Explicit waits for dynamic elements

### Screenshot Capture
- Automatic capture on test failure
- Timestamp in filename
- Stored in `/screenshots/` folder
- Attached to Extent Report

### Configuration Management
- All URLs and credentials in config.properties
- No hardcoded values in code
- Easy to switch between environments
- Centralized configuration

---

## 🎯 Best Practices Implemented

✅ Clear naming conventions  
✅ Single Responsibility Principle  
✅ DRY (Don't Repeat Yourself)  
✅ Explicit waits instead of Thread.sleep()  
✅ POM pattern strictly followed  
✅ Configuration externalization  
✅ Comprehensive error handling  
✅ Detailed logging and reporting  
✅ Data-driven testing  
✅ Reusable framework components  

---

## 📌 Important Rules

⚠️ **No Thread.sleep()** - Use WebDriverWait only  
⚠️ **No hardcoded URLs** - Use config.properties  
⚠️ **No WebDriver in tests** - Use Page classes only  
⚠️ **All locators in pages** - Not in test classes  
⚠️ **Meaningful names** - Clear method and variable names  

---

## 🐛 Troubleshooting

### Tests are slow
- Adjust `explicitWait` in config.properties
- Check internet connection
- Ensure no other processes using the browser

### Screenshots not captured
- Check `/screenshots/` folder permissions
- Verify driver is not null when test fails
- Check TestListener is registered in testng.xml

### Driver not initializing
- Update WebDriverManager: `mvn dependency:update-snapshots`
- Check Java version (needs Java 11+)
- Verify browser is installed

### Elements not found
- Check if site structure changed
- Update locators in page classes
- Increase explicit wait timeout
- Use developer tools to inspect elements

---

## 📚 Additional Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/)
- [ExtentReports Guide](https://www.extentreports.com/)
- [WebDriverManager](https://bonigarcia.dev/webdrivermanager/)
- [Page Object Model Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## 👥 Team Collaboration

### Recommended Task Distribution

**Member 1: Authentication & Registration**
- LoginPage.java
- LoginTest.java

**Member 2: Product Management**
- ProductPage.java
- ProductTest.java

**Member 3: Shopping Cart & Checkout**
- CartPage.java
- CartTest.java
- CheckoutPage.java
- CheckoutTest.java

**Member 4: Framework & Reporting**
- BasePage.java
- BaseTest.java
- All Utilities
- TestListener.java
- ValidationTest.java

---

## 📝 License

This project is created for educational purposes during the hackathon.

---

## 📞 Support

For framework-related issues, check:
1. config.properties settings
2. Locators in page classes
3. Wait timeout values
4. Browser compatibility
5. Network connectivity

---

**Happy Testing! 🎉**
