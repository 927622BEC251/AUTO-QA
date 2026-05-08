# AutoQA Pro - Quick Start Guide

## ⚡ Quick Setup (5 minutes)

### 1️⃣ Prerequisites
```bash
# Check Java version (need Java 11+)
java -version

# Check Maven version
mvn -version
```

### 2️⃣ Clone/Navigate to Project
```bash
cd AutoQAPro
```

### 3️⃣ Run All Tests
```bash
mvn clean test
```

That's it! Tests will run and report will be generated.

---

## 📊 View Test Results

### Extent Report
After tests complete, open:
```
reports/ExtentReport.html
```

### Screenshots on Failure
Failed test screenshots are saved in:
```
screenshots/
```

---

## 🔧 Configuration Quick Tips

### Change Browser
Edit `src/main/resources/config.properties`:
```properties
browser=firefox    # Change to firefox or chrome
```

### Change Base URL
```properties
baseUrl=https://automationexercise.com
```

### Increase Wait Time
```properties
explicitWait=30    # Increase from 20 to 30 seconds
```

### Run in Headless Mode
```properties
headless=true      # Change to true
```

---

## 🧪 Run Specific Tests

### Run Single Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run Single Test Method
```bash
mvn test -Dtest=LoginTest#verifyLogin
```

### Run Multiple Test Classes
```bash
mvn test -Dtest=LoginTest,CartTest,ProductTest
```

---

## 📋 Test Modules at a Glance

| Module | Test Class | Scenarios |
|--------|-----------|-----------|
| Authentication | LoginTest | Login, Logout, Register |
| Products | ProductTest | Search, Browse, Details |
| Shopping Cart | CartTest | Add, Remove, Count |
| Checkout | CheckoutTest | Complete Flow, No Login |
| Validations | ValidationTest | Empty Fields, Invalid Email |

---

## 🎯 Key Test Scenarios

### TC_LOGIN_001: Valid Login
```
Login with valid credentials → Verify logout link visible
```

### TC_LOGIN_002: Invalid Login
```
Login with invalid credentials → Verify error message
```

### TC_PRODUCT_001: Search Product
```
Search keyword → Verify results displayed
```

### TC_CART_001: Add to Cart
```
Add product → Verify in cart with correct name/price
```

### TC_CHECKOUT_001: Complete Order
```
Login → Add to cart → Checkout → Place order → Verify confirmation
```

---

## ✅ Verification Checklist

After running tests, verify:

- [ ] Maven build successful
- [ ] All test cases executed
- [ ] ExtentReport.html generated in `/reports/`
- [ ] Screenshots captured in `/screenshots/` for failures
- [ ] No errors in console output
- [ ] Report shows Pass/Fail status
- [ ] Failed tests have screenshot attachments

---

## 🚨 Common Issues & Solutions

### Issue: Tests not running
```bash
# Solution: Run with clean
mvn clean test

# Check Java version is 11+
java -version
```

### Issue: Driver not found
```bash
# Solution: Let WebDriverManager download it
# Or manually: mvn dependency:resolve
```

### Issue: Timeout errors
```bash
# Solution: Increase wait time in config.properties
explicitWait=30
```

### Issue: Locators not found
```bash
# Solution: Website structure may have changed
# Update locators in page classes
```

---

## 📁 Project Files Overview

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies |
| `testng.xml` | Test suite configuration |
| `config.properties` | Browser & URL settings |
| `testdata.json` | Test data |
| `BasePage.java` | Common page utilities |
| `BaseTest.java` | Common test setup |
| `*Page.java` | Page Object Models |
| `*Test.java` | Test classes |

---

## 🎓 Learning Path

1. **Understand POM** - Review `LoginPage.java` and `LoginTest.java`
2. **Check Page Classes** - See how pages are structured
3. **Study BaseTest** - Understand test setup/teardown
4. **Review Utilities** - ConfigReader, ScreenshotUtil, ExtentManager
5. **Check Listeners** - TestListener implementation
6. **Run Tests** - Execute and observe behavior

---

## 💡 Tips & Tricks

### Debug Mode
```bash
# Run single test with detailed output
mvn test -Dtest=LoginTest -X
```

### Generate Report Only
```bash
# Tests cached, re-generates report
mvn clean test
```

### Skip Tests
```bash
mvn clean install -DskipTests
```

### Parallel Execution
Already configured in `testng.xml`:
```xml
<suite parallel="tests" thread-count="2">
```

---

## 📞 Need Help?

1. Check README.md for detailed documentation
2. Review page class for locator details
3. Check config.properties for configuration
4. View test report for detailed failure info
5. Check screenshot for visual failure details

---

## 🏁 Next Steps

1. Run: `mvn clean test`
2. View Report: Open `reports/ExtentReport.html`
3. Review Results: Check pass/fail status
4. Analyze Failures: Check screenshots
5. Update Locators: If elements not found
6. Re-run Tests: Validate fixes

---

**Ready to Automate? Run `mvn clean test` now! 🚀**
