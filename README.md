# AutoQA Pro – Easy Explanation

## 🔹 What is this project?

Idhu oru Selenium Java Automation Framework project.
Website → [Automation Exercise](https://automationexercise.com?utm_source=chatgpt.com)

Purpose:

* Login test panna
* Product search panna
* Cart la add/remove panna
* Checkout panna
* Validation check panna

---

# 🔹 Project Structure

```text
pages/        → Website pages code
tests/        → Test cases
utilities/    → Common helper classes
listeners/    → Screenshot & report handling
resources/    → Config + JSON data
screenshots/  → Failed test screenshots
reports/      → Extent report
```

---

# 🔹 Main Concepts

## ✅ POM (Page Object Model)

Every page ku separate class.

Example:

* LoginPage.java
* ProductPage.java
* CartPage.java

Use:

* Clean code
* Reusable code
* Easy maintenance

---

# 🔹 BasePage

Common methods store pannuvom.

Example:

```java
waitForElement()
clickElement()
sendKeys()
```

All pages inherit BasePage.

---

# 🔹 BaseTest

Browser launch & close.

```java
@BeforeMethod
→ browser open

@AfterMethod
→ browser close
```

---

# 🔹 config.properties

All common values here.

```properties
browser=chrome
baseUrl=https://automationexercise.com
headless=false
```

Advantage:

* Hardcoding avoid pannalam

---

# 🔹 JSON Test Data

Data separate file la store pannuvom.

```json
{
 "email":"test@gmail.com",
 "password":"12345"
}
```

Use:

* Data-driven testing

---

# 🔹 Test Modules

## 1. Login Module

* Valid login
* Invalid login
* Logout
* Registration

## 2. Product Module

* Search product
* Category browse
* Product details

## 3. Cart Module

* Add cart
* Remove cart
* Cart count verify

## 4. Checkout Module

* Complete order
* Checkout without login

## 5. Validation Module

* Empty field validation
* Invalid email validation

---

# 🔹 WebDriverManager

Driver manually download panna theva illa.

```java
WebDriverManager.chromedriver().setup();
```

---

# 🔹 Wait Strategy

❌ `Thread.sleep()` use panna koodadhu

✅ Use:

```java
WebDriverWait
ExpectedConditions
```

---

# 🔹 Screenshot on Failure

Test fail aana automatically screenshot edukkum.

Save location:

```text
/screenshots/
```

---

# 🔹 Extent Report

HTML report generate aagum.

Contains:

* Pass/Fail
* Screenshot
* Error details

Location:

```text
/reports/ExtentReport.html
```

---

# 🔹 Run Test

Terminal la run pannunga:

```bash
mvn clean test
```

Chrome automatically open aagum.

---

# 🔹 Important Rules

✅ No Thread.sleep()

✅ No hardcoded URL

✅ POM strictly follow

✅ Use testng.xml

✅ Use WebDriverWait

---

# 🔹 Easy Workflow

```text
1. Read config file
2. Open Chrome
3. Open website
4. Run test cases
5. Capture screenshot if fail
6. Generate report
7. Close browser
```

---

# 🔹 Final Idea

Indha framework:

* Clean ah irukanum
* Reusable ah irukanum
* Easy maintain panna mudiyanum
* Professional automation framework madhiri irukanum
