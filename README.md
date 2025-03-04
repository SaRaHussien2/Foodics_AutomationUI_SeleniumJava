# Foodics_AutomationUI_SeleniumJava

# UI Automation Framework using Selenium Java

## Overview

This automation framework is built using **Selenium WebDriver** with **Java** to perform UI testing on web applications. The framework is designed using **TestNG** for test execution and follows the **Page Object Model (POM)** for maintainability and reusability. It includes a modular structure with configuration management, reusable components, and test scripts.

## Project Structure

```
project-root/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── abstractComponents/
│   │   │   │   ├── AbstractComponent
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── ProductPage.java
│   │   │   │   ├── CartPage.java
│   │   │   │   ├── CheckoutPage.java
│   │   │   ├── utils/
│   │   │   │   ├── Logger.java
│   ├── test/
│   │   ├── java/
│   │   │   ├── testsuits/
│   │   │   │   ├── BaseTest.java
│   │   │   │   ├── AmazonTest.java
│── pom.xml
│── README.md
```

## Prerequisites

Ensure the following dependencies are installed:

- Java 8 or later
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager

## Components

### 1. LoginPage.java
Handles user login functionalities.

### 2. HomePage.java
Manages navigation and search functionalities.

### 3. VideoGamePage.java
Handles product selection and adding items to the cart.

### 4. CartPage.java
Verifies cart contents.

### 5. CheckoutPage.java
Verifies checkout process.

### 6. BaseTest.java
Sets up the WebDriver and initializes test configurations.

### 7. UITestCases.java
Contains UI test cases for login, product search, add to cart, and checkout functionalities.


## Test Cases Implemented

- **Login Test**: Validates successful user login.
- **Invalid Login Test**: Ensures proper error handling for incorrect credentials.
- **Search Product Test**: Validates search functionality.
- **Add to Cart Test**: Ensures products are added correctly.
- **Checkout Test**: Simulates checkout process and verifies order summary.

## Running the Tests

Use Eclipse Maven to execute the tests:

## Conclusion

This framework provides a structured approach to UI automation using Selenium WebDriver, making it scalable and easy to maintain.

