# Hybrid API & UI Automation Framework (Banking Workflow Simulation)
This project demonstrates a hybrid automation framework built using Java, RestAssured, Selenium WebDriver, and TestNG. The framework automates end-to-end banking-style workflows including user onboarding, transaction operations, and balance verification.

It includes API chaining, negative scenario validation, response assertions, and reporting integration using Allure.

## How to run this project

- Clone This project
- open build.gradle file in IntelliJ IDEA
- Type gradle clean test in Terminal and Hit Enter
- Then Hit the following command in terminal

```bash
  allure generate allure-results --clean -o allure-report
  allure serve allure-results
```

## Tools and Tech
- Java Programming Language
- IntelliJ IDEA
- Selenium Tool
- Rest Assured Library
- TestNG Framework
- Page Object Model (for UI layer)
- Gradle Build Automation
- Allure Reporting


##The framework automates the following end-to-end scenarios:
- Admin authentication
- Customer and agent onboarding
- System-to-agent fund transfer
- Agent-to-customer deposit
- Customer withdrawal
- Customer-to-customer transfer
- Merchant payment
- Balance verification


1. Do Login by admin
2. Create 2 new customers and an agent
3. Give 2000 tk from the System account to the newly created agent
4. Deposit 1500 tk to a customer from the agent account
5. Withdraw 500 tk by the customer to the agent
6. Send money 500 tk to another customer
7. Payment of 100 tk to a merchant (01686606905) by the recipient customer
8. Check the balance of the recipient customer

Hints:
1. Keep the baseUrl, partnerKey, and token in config.properties file
2. Keep the new customer's and agents' necessary  info in a json array file for chaining APIs

## Test Coverage
- Authentication validation (positive & negative)
- Customer & agent creation validation
- Token validation checks
- Transaction workflow validation
- Method validation checks
- Balance verification
- Negative payload validation
- Invalid authorization scenarios

## Framework Architecture
- Modular package structure
- Reusable request specifications
- Config-driven environment management
- JSON-based test data handling
- API chaining support
- Centralized assertion handling


 ## Reporting
Allure reporting is integrated for execution visualization, including test status, logs, and failure traces.

Generate report using:
allure generate allure-results --clean -o allure-report
allure serve allure-results


# Project Demonstration
https://github.com/foysal619/Dmoney-Rest-Assured-API-Automation-using-Selenium-TestNG/assets/61048879/0e3e970f-1e23-4f5e-b215-b4c228bdf4c6










