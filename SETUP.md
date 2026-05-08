# AutoQA Pro – Easy Setup Guide

## 🔹 Required Software

Install pannirukanum:

* Java 11+
* Maven
* Chrome Browser
* IDE (IntelliJ recommended)

---

# 🔹 Step 1 – Install Java

Download Java:
[Java Download](https://www.oracle.com/java/technologies/downloads/?utm_source=chatgpt.com)

Check installed ah nu:

```bash
java -version
```

---

# 🔹 Step 2 – Install Maven

Download Maven:
[Maven Download](https://maven.apache.org/download.cgi?utm_source=chatgpt.com)

Check:

```bash
mvn -version
```

---

# 🔹 Step 3 – Open Project

```bash
cd AutoQAPro
```

---

# 🔹 Step 4 – Install Dependencies

```bash
mvn clean install
```

Idhu:

* Selenium download pannum
* TestNG download pannum
* Reports library install pannum

---

# 🔹 Step 5 – Config File

File:

```text
src/main/resources/config.properties
```

Inside:

```properties
browser=chrome
baseUrl=https://automationexercise.com
headless=false
```

---

# 🔹 Step 6 – JSON Test Data

File:

```text
src/main/resources/testdata.json
```

Example:

```json
{
 "email":"test@gmail.com",
 "password":"12345"
}
```

---

# 🔹 Step 7 – Run Tests

All tests run panna:

```bash
mvn clean test
```

Specific test:

```bash
mvn test -Dtest=LoginTest
```

---

# 🔹 Step 8 – Browser Open Aagum

Chrome automatically:

* open aagum
* website open pannum
* tests execute pannum

---

# 🔹 Step 9 – Reports

Report location:

```text
/reports/ExtentReport.html
```

Contains:

* Pass
* Fail
* Screenshot
* Error details

---

# 🔹 Step 10 – Screenshots

Failed tests screenshot save aagum:

```text
/screenshots/
```

---

# 🔹 Important Concepts

## ✅ POM

Every page ku separate class.

Example:

* LoginPage
* ProductPage
* CartPage

---

## ✅ BasePage

Common methods:

```java
waitForElement()
click()
sendKeys()
```

---

## ✅ BaseTest

```java
@BeforeMethod
→ browser open

@AfterMethod
→ browser close
```

---

## ✅ No Thread.sleep()

❌ Wrong:

```java
Thread.sleep(3000);
```

✅ Correct:

```java
WebDriverWait
```

---

# 🔹 Main Test Modules

## Login Module

* Valid login
* Invalid login
* Logout

## Product Module

* Search product
* Product details

## Cart Module

* Add cart
* Remove cart

## Checkout Module

* Complete order

## Validation Module

* Empty field validation
* Invalid email validation

---

# 🔹 Easy Workflow

```text
1. Read config
2. Open Chrome
3. Open website
4. Run tests
5. Capture screenshot if fail
6. Generate report
7. Close browser
```

---

# 🔹 Final Goal

Framework:

* Clean ah irukanum
* Reusable ah irukanum
* Professional ah irukanum
* Easy maintain panna mudiyanum

🎉 End result → Full Selenium Automation Framework ready.
