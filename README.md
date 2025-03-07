# Introduction 
This project is a Playwright-based Java test automation framework designed for testing the BREEAM platform. It replaces the existing Selenium TestNG framework with a modern, faster, and more reliable solution using Playwright Java.

This framework enables:

-End-to-end UI automation testing for the BREEAM platform

-SSO login & OTP authentication testing (Mailosaur API for email verification)

-Asset creation, assessment workflows, and user role validation

-Parallel test execution for faster test runs

-Detailed reporting using Extent Reports


With Playwright, tests are executed with faster performance, better stability, and improved cross-browser compatibility compared to traditional Selenium WebDriver.

# Getting Started
1️⃣ Prerequisites

Ensure the following are installed before running the tests:

-Java 11+

-Maven 3.6+

-Node.js (for Playwright CLI) (Optional but recommended)

-Playwright Dependencies

-TestNG (for structured test execution)

-ExtentReports (for reporting)

-Mailosaur API Key (if running email verification tests)

2️⃣ Installation
Clone the repository from your CLI or command prompt:

```git clone https://cielocosta-projects@dev.azure.com/cielocosta-projects/BRE/_git/BreeamPlaywright