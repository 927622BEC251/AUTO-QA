# AutoQA Pro - Setup & Installation Guide

## 📋 System Requirements

### Minimum Requirements
- **OS:** Windows 10+, macOS 10.12+, Ubuntu 16.04+
- **Java:** JDK 11 or higher
- **Maven:** 3.6.0 or higher
- **RAM:** 4GB minimum (8GB recommended)
- **Disk Space:** 1GB for project + dependencies

### Browser Requirements
- **Chrome:** Latest version (automatically downloaded by WebDriverManager)
- **Firefox:** Latest version (automatically downloaded by WebDriverManager)

---

## 🔧 Installation Steps

### Step 1: Install Java

#### Windows
```bash
# Download from Oracle or use: https://www.oracle.com/java/technologies/downloads/
# Add JAVA_HOME to environment variables
# Verify installation
java -version
```

#### macOS
```bash
# Using Homebrew
brew install java

# Verify
java -version
```

#### Ubuntu/Debian
```bash
sudo apt update
sudo apt install openjdk-11-jdk

# Verify
java -version
```

### Step 2: Install Maven

#### Windows
```bash
# Download from: https://maven.apache.org/download.cgi
# Extract and add to PATH
mvn -version
```

#### macOS
```bash
brew install maven
mvn -version
```

#### Ubuntu/Debian
```bash
sudo apt update
sudo apt install maven
mvn -version
```

### Step 3: Install Git (Optional but Recommended)

#### Windows
```bash
# Download from: https://git-scm.com/download/win
choco install git
```

#### macOS
```bash
brew install git
```

#### Ubuntu/Debian
```bash
sudo apt install git
```

### Step 4: Clone or Extract Project

```bash
# Clone from git
git clone <repository-url>
cd AutoQAPro

# Or extract from zip
unzip AutoQAPro.zip
cd AutoQAPro
```

### Step 5: Verify Project Structure

```bash
# Check if all required directories exist
ls -la  # macOS/Linux
dir     # Windows

# Should see:
# - src/
# - target/ (after first build)
# - pom.xml
# - testng.xml
# - config.properties
```

### Step 6: Build Project

```bash
# Download all dependencies
mvn clean install

# This will:
# - Download Selenium, TestNG, ExtentReports, etc.
# - Create target/ directory
# - Verify project structure
```

---

## ✅ Verification Checklist

### Verify Installation

```bash
# 1. Check Java
java -version
# Expected: Java 11 or higher

# 2. Check Maven
mvn -version
# Expected: Maven 3.6+ with Java 11+

# 3. Navigate to project
cd AutoQAPro

# 4. Build project
mvn clean install
# Expected: BUILD SUCCESS

# 5. Run sample test
mvn test -Dtest=LoginTest#verifyLogin
# Expected: Tests execute successfully
```

---

## 🚀 First Test Run

### Basic Test Execution

```bash
# Navigate to project directory
cd AutoQAPro

# Run all tests
mvn clean test

# Expected output:
# - Browser launches
# - Tests execute
# - Report generated at: reports/ExtentReport.html
# - Screenshots saved at: screenshots/ (if failures)
```

### View Results

```bash
# Open extent report in browser
# File path: reports/ExtentReport.html

# Check screenshots
# Directory: screenshots/
```

---

## 📦 Maven Dependencies

The project automatically downloads these dependencies:

```
selenium-java:4.20.0              ← WebDriver
testng:7.10.2                     ← Test Framework
webdrivermanager:5.8.0            ← Driver Management
extentreports:5.1.1               ← Reporting
json:20240303                     ← JSON Processing
commons-io:2.15.1                 ← File Operations
```

**Download Size:** ~200MB (first time)

---

## 🔍 Troubleshooting Installation

### Problem: Java not found
```bash
# Solution: Verify Java installation and PATH
java -version

# If not found:
# 1. Reinstall Java
# 2. Add JAVA_HOME to environment variables
# 3. Add Java bin directory to PATH
```

### Problem: Maven not found
```bash
# Solution: Verify Maven installation and PATH
mvn -version

# If not found:
# 1. Reinstall Maven
# 2. Add Maven bin directory to PATH
```

### Problem: Dependencies download fails
```bash
# Solution: Check internet connection and retry
mvn clean install -U

# Force update of dependencies
mvn dependency:purge-local-repository
mvn clean install
```

### Problem: Build fails with error
```bash
# Solution: Check error messages carefully
mvn clean install -X  # Verbose mode

# Common causes:
# - Wrong Java version
# - Corrupted local repository
# - Network issues
```

### Problem: Tests don't run
```bash
# Solution: Verify testng.xml is valid
mvn test -Dtest=LoginTest  # Run specific test

# Check if test exists
mvn test --help
```

---

## 🔐 System Environment Variables

### Set Java Home

#### Windows
```cmd
# Set permanently
setx JAVA_HOME "C:\Program Files\Java\jdk-11"

# Add to PATH
setx PATH "%PATH%;C:\Program Files\Java\jdk-11\bin"
```

#### macOS/Linux
```bash
# Add to ~/.bash_profile or ~/.zshrc
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.x/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"

# Reload
source ~/.bash_profile
```

---

## 📚 Additional Setup (Optional)

### IDE Setup

#### IntelliJ IDEA
```bash
# File → Open Project
# Select AutoQAPro folder
# Configure SDK:
#   File → Project Structure → Project Settings → SDK → JDK 11
# Maven automatically recognized
```

#### Eclipse
```bash
# File → Import
# Select "Existing Maven Projects"
# Browse to AutoQAPro folder
# Finish
```

#### VS Code
```bash
# Install Extension Pack for Java
# Open AutoQAPro folder
# Maven projects detected automatically
```

### Git Configuration (Optional)
```bash
# Clone project
git clone <repository-url>

# Configure git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

---

## 🌐 Configuration Before First Run

### Edit config.properties

```properties
# File: src/main/resources/config.properties

browser=chrome              # Change to firefox if needed
baseUrl=https://automationexercise.com
implicitWait=10            # Increase if needed
explicitWait=20            # Increase if needed
headless=false             # Set to true for headless mode
```

### Edit testdata.json

```json
{
  "validLogin": {
    "email": "your-test-email@gmail.com",
    "password": "your-test-password"
  }
}
```

---

## 📊 Running Different Test Scenarios

### Run All Tests
```bash
mvn clean test
```

### Run Specific Module
```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test
```bash
mvn test -Dtest=LoginTest#verifyLogin
```

### Run in Headless Mode
```bash
# Edit config.properties: headless=true
mvn test
```

### Run with Firefox
```bash
# Edit config.properties: browser=firefox
mvn test
```

### Run with Parallel Execution
```bash
# Already configured in testng.xml
mvn test
```

---

## 🎯 Post-Installation Checklist

- [ ] Java 11+ installed and verified
- [ ] Maven 3.6+ installed and verified
- [ ] Project cloned/extracted
- [ ] `mvn clean install` ran successfully
- [ ] config.properties configured
- [ ] testdata.json updated with real data
- [ ] First test run successful
- [ ] ExtentReport.html generated
- [ ] IDE configured (if using)
- [ ] Ready to start writing tests

---

## 🚀 Next Steps

1. ✅ Complete all installation steps
2. ✅ Run `mvn clean test`
3. ✅ View report at `reports/ExtentReport.html`
4. ✅ Review test results
5. ✅ Study page classes (LoginPage.java, etc.)
6. ✅ Understand test methods
7. ✅ Add new tests or modify existing ones
8. ✅ Run tests again
9. ✅ Verify reports

---

## 📞 Common Commands Reference

```bash
# Build project
mvn clean install

# Run all tests
mvn clean test

# Run specific test
mvn test -Dtest=LoginTest

# Run specific method
mvn test -Dtest=LoginTest#verifyLogin

# Update dependencies
mvn dependency:resolve

# Clear cache
mvn clean

# Skip tests during build
mvn clean install -DskipTests

# Verbose output
mvn test -X

# Check plugin versions
mvn dependency:tree
```

---

## 🔗 Useful Resources

- [Java Download](https://www.oracle.com/java/technologies/downloads/)
- [Maven Documentation](https://maven.apache.org/)
- [Selenium Documentation](https://www.selenium.dev/)
- [TestNG Documentation](https://testng.org/)
- [ExtentReports Guide](https://www.extentreports.com/)
- [WebDriverManager Guide](https://bonigarcia.dev/webdrivermanager/)

---

## 📝 Notes

- First build takes longer due to dependency downloads (~200MB)
- WebDriverManager automatically downloads correct browser drivers
- No need to manually download ChromeDriver or GeckoDriver
- All configuration in one place: `config.properties`
- Test data in JSON for easy maintenance

---

**Installation Guide Version:** 1.0  
**Last Updated:** May 8, 2026  
**For Questions:** Refer to README.md and ARCHITECTURE.md
