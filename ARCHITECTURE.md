# AutoQA Pro - Architecture & Design Document

## 🏛️ Framework Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                         TEST CLASSES                             │
│  (LoginTest, ProductTest, CartTest, CheckoutTest, ValidationTest) │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                      PAGE OBJECT CLASSES                         │
│  (HomePage, LoginPage, ProductPage, CartPage, CheckoutPage)     │
└────────────────────────┬────────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────────┐
│                   BASEPAGE (Common Utilities)                    │
│  - waitForElement()                                              │
│  - clickElement()                                                │
│  - sendKeysToElement()                                           │
│  - getElementText()                                              │
│  - waitForElementToBeClickable()                                 │
└────────────────────────┬────────────────────────────────────────┘
                         │
        ┌────────────────┼────────────────┬──────────────────┐
        ▼                ▼                ▼                  ▼
┌──────────────┐  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│BaseTest      │  │ConfigReader  │  │ExtentManager │  │ScreenshotUtil│
│- @Before     │  │- getProperty │  │- createTest  │  │- capture()   │
│- @After      │  │- URL from    │  │- addLog      │  │- timestamp   │
│- setup()     │  │  properties  │  │- flush()     │  │- save path   │
│- teardown()  │  │              │  │              │  │              │
└──────────────┘  └──────────────┘  └──────────────┘  └──────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│                    WEBDRIVER & BROWSER                           │
│  (Managed by WebDriverManager - Automatic Driver Setup)          │
└─────────────────────────────────────────────────────────────────┘
        │
        ▼
┌─────────────────────────────────────────────────────────────────┐
│                     APPLICATION UNDER TEST                       │
│               https://automationexercise.com                     │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📦 Design Patterns Used

### 1. Page Object Model (POM)
Each page has a dedicated class containing:
- **Locators**: Private `By` variables for each element
- **Actions**: Public methods for user interactions
- **Validations**: Methods to verify page state

**Benefits:**
- Easy maintenance - update locators in one place
- Reusability - use same methods across tests
- Readability - clear what each test does
- Scalability - add new pages easily

### 2. Base Class Pattern
**BasePage**
- Common waits and utilities
- Reusable element interaction methods
- Single place to update wait strategies

**BaseTest**
- Browser setup/teardown
- Configuration reading
- Report initialization

**Benefits:**
- DRY principle - no duplicate code
- Consistent behavior across tests
- Easy to update wait strategies
- Centralized browser management

### 3. Factory Pattern
**WebDriverManager**
- Automatically provides correct driver
- No manual driver setup needed
- Version compatibility handled

### 4. Singleton Pattern
**ExtentManager**
- Single report instance
- Centralized report management
- Prevents multiple report generation

### 5. Listener Pattern
**TestListener (ITestListener)**
- Listens to test events
- Captures screenshots on failure
- Logs to extent report
- Tracks execution progress

---

## 🔄 Execution Flow

### Test Execution Sequence

```
1. TestNG reads testng.xml
   ↓
2. TestListener.onStart() - Initialize Extent Report
   ↓
3. For Each Test Method:
   ├─ @BeforeMethod → BaseTest.setup()
   │  ├─ Read browser from config.properties
   │  ├─ Use WebDriverManager to setup driver
   │  ├─ Maximize window
   │  ├─ Navigate to baseUrl
   │  └─ Create test in Extent Report
   │
   ├─ Test Execution
   │  ├─ Create page object
   │  ├─ Call page methods (no driver code in test)
   │  └─ Perform assertions
   │
   ├─ TestListener.onTestSuccess() or onTestFailure()
   │  ├─ If success → Log PASS to report
   │  └─ If failure → Capture screenshot + Log FAIL + Add to report
   │
   └─ @AfterMethod → BaseTest.tearDown()
      └─ driver.quit()
   
4. TestListener.onFinish() - Flush Extent Report
   ↓
5. Report generated at: reports/ExtentReport.html
   Screenshots saved at: screenshots/
```

---

## 🎯 Wait Strategy

### No Thread.sleep()

**Instead, using WebDriverWait with ExpectedConditions:**

```java
// ❌ BAD - Don't do this
Thread.sleep(5000);
element.click();

// ✅ GOOD - Do this
wait.until(ExpectedConditions.elementToBeClickable(element));
element.click();

// ✅ GOOD - Use BasePage method
clickElement(element);  // Already has wait
```

### Wait Types in BasePage

1. **Visibility Wait** - Element is displayed
2. **Clickable Wait** - Element is enabled and visible
3. **Presence Wait** - Element exists in DOM
4. **Invisible Wait** - Element disappears

---

## 📊 Configuration Management

### config.properties

```
┌─────────────────────────────┐
│   config.properties         │
├─────────────────────────────┤
│ browser=chrome              │
│ baseUrl=...                 │
│ implicitWait=10             │
│ explicitWait=20             │
│ headless=false              │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│    ConfigReader.java        │
├─────────────────────────────┤
│ - Load properties on static │
│ - getProperty(key)          │
│ - getIntProperty(key)       │
│ - getBooleanProperty(key)   │
└────────┬────────────────────┘
         │
         ▼
┌─────────────────────────────┐
│   Used Throughout Code      │
├─────────────────────────────┤
│ - BaseTest                  │
│ - ExtentManager             │
│ - Any Test Class            │
└─────────────────────────────┘
```

**Benefits:**
- Easy environment switching
- No hardcoded values
- Centralized configuration
- Quick adjustments

---

## 📋 Test Data Management

### testdata.json Structure

```json
{
  "validLogin": { "email": "...", "password": "..." },
  "invalidLogin": { "email": "...", "password": "..." },
  "registration": { "name": "...", "email": "..." },
  "productSearch": { "keyword": "..." },
  "checkout": { "name": "...", "cardNumber": "..." }
}
```

### JsonDataReader Usage

```java
// Single value
String email = JsonDataReader.getValue("validLogin", "email");

// Full object
JSONObject loginData = JsonDataReader.getTestData("validLogin");

// In DataProvider
@DataProvider
public Object[][] loginData() {
    return new Object[][]{
        {JsonDataReader.getValue("validLogin", "email"), ...}
    };
}
```

---

## 📸 Screenshot & Reporting

### Screenshot Capture Flow

```
Test Fails
   ↓
TestListener.onTestFailure() triggered
   ↓
Extract WebDriver from test instance
   ↓
ScreenshotUtil.captureScreenshot()
   ├─ Generate timestamp
   ├─ Use TakesScreenshot interface
   ├─ Save to /screenshots/ folder
   └─ Return path
   ↓
Add to Extent Report with path
   ↓
Report displays screenshot in HTML
```

### Report Generation

```
ExtentManager.getInstance()
   ├─ Initialize ExtentReports
   ├─ Configure ExtentSparkReporter
   └─ Set system info
         ↓
ExtentManager.createTest()
   ├─ Create test node
   └─ Return ExtentTest
         ↓
During Test:
   ├─ Test passes → ExtentTest.log(Status.PASS)
   ├─ Test fails → ExtentTest.log(Status.FAIL)
   ├─ Screenshot → ExtentTest.addScreenCaptureFromPath()
   └─ Exception → ExtentTest.log(Status.FAIL, throwable)
         ↓
ExtentManager.flushReport()
   └─ Generate reports/ExtentReport.html
```

---

## 🧪 Test Structure

### Test Class Hierarchy

```
BaseTest (abstract setup/teardown)
   ↑
   └─ Implemented by:
       ├─ LoginTest extends BaseTest
       ├─ ProductTest extends BaseTest
       ├─ CartTest extends BaseTest
       ├─ CheckoutTest extends BaseTest
       └─ ValidationTest extends BaseTest
```

### Test Method Structure

```java
@Test(priority = 1)
public void testName() {
    // 1. Create page object
    HomePage homePage = new HomePage(driver);
    
    // 2. Call page methods (no locators here)
    homePage.clickLoginLink();
    
    // 3. Perform assertions
    Assert.assertTrue(condition, "Message");
    
    // 4. No driver code - all in page class
}
```

---

## 🔒 Best Practices Enforced

### ✅ POM Strictly Followed
```
✓ No findElement() in test classes
✓ No locators hardcoded in tests
✓ All locators in page classes
✓ Reusable page methods
```

### ✅ No Thread.sleep()
```
✓ WebDriverWait everywhere
✓ ExpectedConditions used
✓ BasePage methods include waits
✓ Explicit timeouts configurable
```

### ✅ Configuration Management
```
✓ No hardcoded URLs
✓ No hardcoded credentials
✓ Browser name from config.properties
✓ All config in one place
```

### ✅ Error Handling
```
✓ Try-catch for optional elements
✓ Proper exception logging
✓ Screenshot on failure
✓ Meaningful error messages
```

### ✅ Code Quality
```
✓ Meaningful variable names
✓ Clear method names
✓ Single responsibility principle
✓ DRY - no code duplication
✓ Comprehensive documentation
```

---

## 🚀 Scalability

### Adding New Pages

```java
// 1. Create new page class extending BasePage
public class NewPage extends BasePage {
    // 2. Define locators
    private By element = By.id("elementId");
    
    // 3. Add action methods
    public void clickElement() {
        clickElement(driver.findElement(element));
    }
}

// 4. Use in tests
NewPage page = new NewPage(driver);
page.clickElement();
```

### Adding New Tests

```java
public class NewTest extends BaseTest {
    @Test
    public void newTestMethod() {
        NewPage page = new NewPage(driver);
        // Test logic
    }
}

// Register in testng.xml
<class name="tests.NewTest"/>
```

### Adding New Test Data

```json
{
  "newData": {
    "field1": "value1",
    "field2": "value2"
  }
}
```

---

## 📈 Performance Considerations

### Parallel Execution
```xml
<!-- In testng.xml -->
<suite parallel="tests" thread-count="2">
```
Benefits:
- Faster overall execution
- Independent tests run in parallel
- Optimal resource utilization

### Wait Optimization
```properties
# Adjust based on application
explicitWait=20  # Balance between speed and reliability
```

---

## 🔍 Debugging Support

### Built-in Logging

```java
// In TestListener
System.out.println("Test Started: " + result.getName());
System.out.println("Test Failed: " + result.getName());
System.out.println("Screenshot captured: " + path);
```

### Report Details

```
ExtentReport includes:
- Test execution time
- Pass/Fail status
- Screenshots on failure
- Exception stack trace
- System information
```

### Screenshots for Analysis

```
/screenshots/TestName_timestamp.png
Each failure has visual evidence
```

---

## 🎓 Learning Resources

### Understanding Each Layer

1. **Page Layer** - Understand locators and interactions
2. **Test Layer** - Understand test logic
3. **Base Layer** - Understand common utilities
4. **Utility Layer** - Understand helpers
5. **Listener Layer** - Understand event tracking

### Key Concepts

- **Locator Strategies** - ID, XPath, CSS Selector
- **Wait Strategies** - Visibility, Clickability, Presence
- **POM Pattern** - Separation of concerns
- **TestNG** - Annotations, DataProviders, Listeners
- **Extent Reports** - Report generation
- **WebDriverManager** - Automatic driver setup

---

## 📌 Summary

The AutoQA Pro framework demonstrates:

✅ **Enterprise-grade design** - Following industry standards  
✅ **Scalability** - Easy to add tests and pages  
✅ **Maintainability** - Clear structure and documentation  
✅ **Reliability** - Proper wait strategies and error handling  
✅ **Reporting** - Comprehensive test reports with evidence  
✅ **Best Practices** - POM, no Thread.sleep(), configuration management  
✅ **Team Collaboration** - Well-organized, documentation, clear responsibilities  

This framework can be used as a template for any Selenium automation project.

---

**Framework Version:** 1.0  
**Created:** May 8, 2026  
**Framework Type:** Selenium + TestNG + ExtentReports  
**Support:** Fully documented and maintained
