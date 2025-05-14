# Demo Playwright SharePoint Automation Framework

This demo repository contains a Playwright-based end-to-end automation framework built for validating SharePoint-integrated enterprise applications. The goal is to support scalable, maintainable, and efficient automated testing across web platforms with SharePoint and Azure AD integration.

---

## Technologies used:

- **Language:** Java 17+
- **Test Runner:** TestNG
- **Automation Engine:** Playwright (Java)
- **Build Tool:** Maven
- **Reporting:** ExtentReports (HTML)
- **Email Utility:** Mailosaur (OTP/2FA support)
- **CI/CD Integration:** Azure DevOps Pipelines
- **Version Control:** Git + Azure Repos

---

This project aims to:

- Automate regression and core functional workflows on SharePoint-based applications.
- Minimize manual test effort and increase test coverage using reusable components.
- Enable CI-integrated automation for fast feedback and scalable delivery.
- Provide a reusable, modular framework template for future internal projects.

---

## Key Features

- **Locator Strategy:** Index-based XPath selectors generated through internal auto-indexing scripts.
- **Page Object Model:** Clean abstraction for maintainable scripts.
- **Data-Driven Execution:** Supports external JSON/Excel datasets.
- **CI/CD Pipeline Ready:** PR-based, nightly, and on-demand runs using Azure Pipelines.
- **Visual Reporting:** HTML reports with logs, timestamps, and screenshots (ExtentReports).

---
