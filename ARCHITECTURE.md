# AutoQA Pro – Architecture Simple Explanation

## 📌 Framework Idea

Indha framework la main goal:

* Clean code
* Reusable code
* Easy maintenance
* Professional Selenium framework create panradhu

Website:
[Automation Exercise](https://automationexercise.com?utm_source=chatgpt.com)

---

# 🔹 Overall Flow

```text id="rk9v4v"
Test Class
   ↓
Page Classes
   ↓
BasePage Utilities
   ↓
WebDriver
   ↓
Website
```

Simple ah sonna:

* Test class la test logic irukum
* Page class la elements + actions irukum
* BasePage la common methods irukum

---

# 🔹 Main Classes

## ✅ Test Classes

Examples:

* LoginTest
* ProductTest
* CartTest

Idhula:

* Test cases mattum irukum
* Assertions irukum

❌ findElement use panna koodadhu

---

## ✅ Page Classes

Examples:

* LoginPage
* ProductPage
* CartPage

Idhula:

* Locators
* Click methods
* SendKeys methods

Example:

```java id="9lk68m"
loginButton.click();
```

---

## ✅ BasePage

Common methods store pannuvom.

Example:

```java id="k9l6j7"
waitForElement()
clickElement()
sendKeys()
```

Advantage:

* Duplicate code avoid
* Reuse panna easy

---

## ✅ BaseTest

Browser open & close handle pannum.

```java id="h9n2ok"
@BeforeMethod
→ browser open

@AfterMethod
→ browser close
```

---

# 🔹 ConfigReader

File:

```text id="pcxg8f"
config.properties
```

Inside:

```properties id="1ojfjw"
browser=chrome
baseUrl=https://automationexercise.com
```

Use:

* Hardcoding avoid
* Browser easy change panna mudiyum

---

# 🔹 JSON Data

File:

```text id="p4d7kw"
testdata.json
```

Example:

```json id="13y9h8"
{
 "email":"test@gmail.com"
}
```

Use:

* Test data separate ah vechikalam

---

# 🔹 WebDriverManager

```java id="c7mlv9"
WebDriverManager.chromedriver().setup();
```

Use:

* Driver manually download panna theva illa

---

# 🔹 Wait Strategy

❌ Wrong:

```java id="cmx3ll"
Thread.sleep(3000);
```

✅ Correct:

```java id="pq0j54"
WebDriverWait
```

Reason:

* Stable automation
* Fast execution

---

# 🔹 Screenshot on Failure

Test fail aana:

* Screenshot automatically edukkum
* `/screenshots/` la save aagum

Example:

```text id="e1m1gz"
verifyLogin_20260508.png
```

---

# 🔹 Extent Report

Test complete aana HTML report generate aagum.

Location:

```text id="sqx70z"
/reports/ExtentReport.html
```

Contains:

* Pass
* Fail
* Screenshot
* Error details

---

# 🔹 Execution Flow

```text id="t7p26q"
1. Read config file
2. Open browser
3. Open website
4. Run tests
5. If fail → screenshot
6. Generate report
7. Close browser
```

---

# 🔹 POM (Page Object Model)

Every page ku separate class.

Example:

```text id="wte18w"
LoginPage.java
CartPage.java
ProductPage.java
```

Benefits:

* Easy maintenance
* Clean structure
* Reusable methods

---

# 🔹 TestNG Usage

Use pannradhu:

* @Test
* @BeforeMethod
* @AfterMethod
* @DataProvider
* Listeners

---

# 🔹 Why This Framework Good?

✅ Clean structure

✅ Reusable code

✅ Easy debugging

✅ Easy report checking

✅ Industry standard approach

✅ Team work easy

---

# 🔹 Final Summary

Indha framework la:

* Test logic separate
* Page logic separate
* Config separate
* Reports separate

So project:

* Professional ah irukum
* Easy maintain panna mudiyum
* Hackathon ku perfect ah irukum 🎯
