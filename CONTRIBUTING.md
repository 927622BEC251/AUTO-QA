# Contributing to AutoQA Pro

## 🎯 Guidelines

Thank you for contributing to AutoQA Pro! This document provides guidelines and instructions for contributing.

---

## 📋 Before You Start

1. Read the [README.md](README.md) - Understand the project
2. Read the [ARCHITECTURE.md](ARCHITECTURE.md) - Understand the design
3. Fork the repository
4. Create a new branch: `git checkout -b feature/your-feature-name`

---

## 🏗️ Code Structure

### Adding a New Test

1. **Create Page Class** (if needed)
   ```java
   package pages;
   
   public class NewPage extends BasePage {
       private By element = By.id("elementId");
       
       public void doAction() {
           clickElement(driver.findElement(element));
       }
   }
   ```

2. **Create Test Class**
   ```java
   package tests;
   
   public class NewTest extends BaseTest {
       @Test
       public void testNewFeature() {
           NewPage page = new NewPage(driver);
           page.doAction();
           Assert.assertTrue(condition);
       }
   }
   ```

3. **Register in testng.xml**
   ```xml
   <class name="tests.NewTest"/>
   ```

---

## ✅ Checklist Before Commit

- [ ] Code follows POM pattern (no driver code in tests)
- [ ] No Thread.sleep() used
- [ ] All waits use WebDriverWait
- [ ] No hardcoded URLs or credentials
- [ ] Meaningful variable and method names
- [ ] Added/updated documentation
- [ ] Tests pass locally: `mvn clean test`
- [ ] No console errors or warnings
- [ ] Code formatted properly

---

## 🔍 Code Review Standards

### Best Practices
✅ Clear method names describing action  
✅ Comprehensive JavaDoc comments  
✅ Proper exception handling  
✅ Reusable methods in BasePage  
✅ No code duplication  
✅ Meaningful assertions  

### Avoid
❌ Thread.sleep() anywhere  
❌ findElement() in test classes  
❌ Hardcoded values  
❌ Too many assertions per test  
❌ Unclear variable names  
❌ No comments on complex logic  

---

## 📝 Commit Message Format

```
type(scope): brief description

Detailed explanation of the change.
- What was changed
- Why it was changed
- Any side effects or considerations

Closes #issue-number
```

**Types:**
- `feat` - New feature
- `fix` - Bug fix
- `test` - Test additions/modifications
- `docs` - Documentation updates
- `refactor` - Code refactoring
- `chore` - Maintenance tasks

**Example:**
```
feat(pages): add payment validation methods

Added validation methods to CheckoutPage for payment form:
- verifyCardErrorMessage()
- isPaymentButtonDisabled()

Closes #25
```

---

## 🧪 Testing Guidelines

### Test Naming Convention
```java
// Pattern: verb + subject + expected result
@Test
public void verifyLogin()  // ✅ Good
public void testLogin()    // ✅ Acceptable
public void test1()        // ❌ Bad
```

### Assertion Guidelines
```java
// ✅ Good - Clear and meaningful
Assert.assertTrue(condition, "User should be logged in");
Assert.assertEquals(expected, actual, "Product names should match");

// ❌ Bad - No message
Assert.assertTrue(condition);
Assert.assertEquals(expected, actual);

// ❌ Bad - Too many assertions
@Test
public void testUserJourney() {
    // 50 lines of assertions - break into multiple tests
}
```

---

## 📚 Documentation Requirements

### JavaDoc for Classes
```java
/**
 * LoginPage - Handles user login and signup operations
 * 
 * @author Your Name
 * @version 1.0
 */
```

### JavaDoc for Methods
```java
/**
 * Login with provided credentials
 * 
 * @param email - User email address
 * @param password - User password
 * @throws TimeoutException if elements not found
 */
public void login(String email, String password) {
    // Implementation
}
```

---

## 🔄 Pull Request Process

1. **Fork & Clone**
   ```bash
   git clone https://github.com/your-username/AutoQAPro.git
   cd AutoQAPro
   ```

2. **Create Feature Branch**
   ```bash
   git checkout -b feature/add-new-tests
   ```

3. **Make Changes**
   - Add tests or features
   - Update documentation
   - Ensure all tests pass

4. **Commit Changes**
   ```bash
   git add .
   git commit -m "feat(tests): add new test scenarios"
   ```

5. **Push to GitHub**
   ```bash
   git push origin feature/add-new-tests
   ```

6. **Create Pull Request**
   - Clear title and description
   - Link related issues
   - Add screenshots if UI changes

7. **Address Review Comments**
   - Make requested changes
   - Respond to feedback
   - Keep commits clean

---

## 🐛 Bug Reporting

### Report Template
```
**Title:** [BUG] Brief description

**Environment:**
- OS: [e.g., Windows 11, macOS 12.x]
- Java Version: [e.g., 11, 17]
- Browser: [e.g., Chrome 120]

**Steps to Reproduce:**
1. First step
2. Second step
3. Expected result
4. Actual result

**Screenshots:**
[Attach screenshot or video]

**Logs:**
[Attach error logs or console output]
```

---

## ✨ Feature Requests

```
**Title:** [FEATURE] Brief description

**Problem:**
What problem does this solve?

**Solution:**
How should it be implemented?

**Alternative Solutions:**
Are there other approaches?

**Additional Context:**
Any other relevant information
```

---

## 📚 Development Setup

### Quick Start
```bash
# Clone
git clone https://github.com/your-username/AutoQAPro.git
cd AutoQAPro

# Install dependencies
mvn clean install

# Run tests
mvn test

# Build without tests
mvn clean install -DskipTests
```

### Using VS Code
1. Install Extension Pack for Java
2. Open project folder
3. Maven explorer auto-detects project
4. Run tasks via VS Code command palette

---

## 🎯 Code Style

### Java Naming Convention
```java
// Classes - PascalCase
public class LoginPage

// Methods - camelCase
public void clickLoginButton()

// Constants - UPPER_SNAKE_CASE
private static final long TIMEOUT = 20L;

// Variables - camelCase
String emailAddress;
```

### Formatting
- 4 spaces indentation
- Max line length: 120 characters
- One class per file
- Opening braces on same line

---

## 🚀 Performance Considerations

- Minimize browser navigation
- Reuse elements where possible
- Use efficient XPath expressions
- Parallelize independent tests
- Keep test data minimal

---

## 🔐 Security

- Never commit credentials
- Use config.properties for sensitive data
- Follow OWASP guidelines
- Validate user inputs
- Use secure communication (HTTPS)

---

## 🤝 Community

- Be respectful and inclusive
- Help others with questions
- Share knowledge and best practices
- Celebrate contributions
- Follow Code of Conduct

---

## 📞 Questions?

- Check existing GitHub Issues
- Read the documentation
- Ask in Discussions section
- Contact maintainers

---

## 📄 License

By contributing, you agree that your contributions will be licensed under the project's license.

---

**Thank you for contributing to AutoQA Pro! 🎉**
