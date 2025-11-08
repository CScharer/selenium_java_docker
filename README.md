# CJS QA Automation Framework

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.26.0-green.svg)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-brightgreen.svg)](https://cucumber.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9.11-blue.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Security](https://img.shields.io/badge/Security-Google%20Cloud-blue.svg)](https://cloud.google.com/secret-manager)

A comprehensive Selenium-based test automation framework supporting **30+ test suites** across multiple domains including Google, Microsoft, LinkedIn, Vivit, BTS, and more. Built with enterprise-grade security, modern dependencies, and Page Object Model architecture.

---

## 📋 Table of Contents

- [Features](#features)
- [Quick Start](#quick-start)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Test Suites](#test-suites)
- [Architecture](#architecture)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [Security](#security)
- [License](#license)

---

## ✨ Features

### Core Capabilities
- 🎯 **30+ Test Suites** - Comprehensive coverage across multiple domains
- 🔐 **Secure Credential Management** - Google Cloud Secret Manager integration
- 🚀 **Parallel Execution** - Native Cucumber parallel test support (5 threads)
- 🌐 **Multi-Browser Support** - Chrome, Firefox, Edge, Safari, IE
- 📊 **Rich Reporting** - JUnit, TestNG, Cucumber, and Allure reports
- 🎨 **Page Object Model** - Clean, maintainable test architecture
- ☁️ **Selenium Grid** - Remote execution support
- 🔄 **CI/CD Ready** - GitHub Actions, Travis CI compatible

### Modern Technology Stack
- **Java 17** - Latest LTS version
- **Selenium 4.26.0** - Modern WebDriver API
- **Cucumber 7.20.1** - BDD framework with Gherkin
- **Maven 3.9.11** - Build management (wrapper included!)
- **Google Cloud Secret Manager** - Enterprise-grade security
- **JUnit 4 & TestNG** - Flexible test execution

### Recent Improvements
- ✅ Migrated to modern dependencies (Selenium 4, Cucumber 7, Java 17)
- ✅ Google Cloud Secret Manager integration (0 hardcoded passwords!)
- ✅ Comprehensive .gitignore protection
- ✅ Pre-commit hooks for quality control
- ✅ Maven wrapper for reproducible builds
- ✅ Helper scripts for easy test execution

---

## 🚀 Quick Start

```bash
# 1. Clone the repository
git clone https://github.com/CScharer/selenium_java_docker.git
cd selenium_java_docker

# 2. Authenticate with Google Cloud (for password retrieval)
gcloud auth application-default login
gcloud config set project cscharer

# 3. Copy configuration templates
cp XML/Companies.xml.template XML/Companies.xml
cp XML/UserSettings.xml.template XML/UserSettings.xml

# 4. Run tests (Maven wrapper included - no Maven install needed!)
./mvnw clean test

# Or use helper script
./scripts/run-tests.sh Scenarios chrome
```

**That's it!** The framework will automatically fetch passwords from Google Cloud Secret Manager.

---

## 📦 Prerequisites

### Required
- **Java 17+** - [Download JDK](https://adoptium.net/)
- **Google Cloud SDK** - [Install gcloud](https://cloud.google.com/sdk/docs/install)
- **Git** - For version control

### Optional
- **Docker & Docker Compose** - For containerized execution
- **IDE** - IntelliJ IDEA, Eclipse, or VS Code
- **Pre-commit** - Already configured (install with `pip install pre-commit`)

### No Maven Required!
This project includes **Maven Wrapper** (`./mvnw`), so you don't need to install Maven separately.

---

## 💻 Installation

### 1. Clone Repository
```bash
git clone https://github.com/CScharer/selenium_java_docker.git
cd selenium_java_docker
```

### 2. Setup Google Cloud Authentication
```bash
# Authenticate
gcloud auth application-default login

# Set project
gcloud config set project cscharer

# Verify you can access secrets
gcloud secrets versions access latest --secret="AUTO_BTSQA_PASSWORD"
```

### 3. Copy Configuration Templates
```bash
# Copy template files to create working configurations
cp XML/Companies.xml.template XML/Companies.xml
cp XML/UserSettings.xml.template XML/UserSettings.xml
cp Configurations/Environments.xml.template Configurations/Environments.xml
```

### 4. Verify Installation
```bash
# Compile project
./mvnw clean compile test-compile

# Should see: BUILD SUCCESS ✅
```

### 5. (Optional) Install Pre-commit Hooks
```bash
pip install pre-commit
pre-commit install
```

---

## ⚙️ Configuration

### Google Cloud Secret Manager

All passwords are securely stored in Google Cloud Secret Manager. The framework automatically retrieves them at runtime.

**Secrets are organized as**:
- Application passwords: `AUTO_*_PASSWORD`
- Company accounts: `AUTO_COMPANY_*_PASSWORD`
- Test credentials: `AUTO_TEST_*`, `AUTO_SAUCELABS_*`

**View your secrets**:
```bash
gcloud secrets list | grep AUTO
```

### Environment Configuration

Configure test execution in `Configurations/Environments.xml`:
- Browser selection (Chrome, Firefox, Edge)
- Timeouts (page, element, alert)
- Grid settings (local vs remote)
- Logging options

See `Configurations/README.md` for details.

---

## 🧪 Running Tests

### Using Helper Scripts (Recommended)

```bash
# Run default test suite with Chrome
./scripts/run-tests.sh

# Run with specific browser
./scripts/run-tests.sh Scenarios firefox

# Run specific test method
./scripts/run-specific-test.sh Scenarios Google

# Just compile (no tests)
./scripts/compile.sh
```

### Using Maven Wrapper Directly

```bash
# Run all tests
./mvnw clean test

# Run specific test class
./mvnw test -Dtest=Scenarios

# Run specific test method
./mvnw test -Dtest=Scenarios#Google

# Run with specific browser
./mvnw test -Dtest=Scenarios#Microsoft -Dbrowser=chrome

# Skip tests during build
./mvnw clean install -DskipTests
```

### Parallel Execution

Tests run in parallel by default (5 threads):

```bash
# Parallel execution is configured in pom.xml
./mvnw test
# Runs with 5 parallel threads automatically
```

---

## 📁 Project Structure

```
selenium_java_docker/
├── src/
│   ├── main/java/com/cjs/qa/app/          # Main application code
│   └── test/java/com/cjs/qa/              # Test suites (30+ packages)
│       ├── google/                         # Google test suite
│       ├── microsoft/                      # Microsoft test suite (33 files)
│       ├── linkedin/                       # LinkedIn test suite
│       ├── vivit/                          # Vivit community tests (25 files)
│       ├── bts/                            # BTS internal apps (60 files)
│       ├── core/                           # Core framework
│       ├── selenium/                       # Selenium wrappers
│       ├── utilities/                      # Helper utilities (43 files)
│       └── ...                             # 25+ more test suites
├── scripts/                                # Helper scripts
│   ├── run-tests.sh                        # Easy test execution
│   ├── run-specific-test.sh                # Run specific test
│   └── compile.sh                          # Compile only
├── docs/                                   # Documentation
│   ├── ANALYSIS.md                         # Project analysis
│   ├── ANALYSIS_SUGGESTIONS.md             # 150-task roadmap
│   ├── INTEGRATION_COMPLETE.md             # Secret Manager guide
│   └── NEXT_STEPS.md                       # Quick action guide
├── XML/                                    # Configuration files
│   ├── Companies.xml.template              # Company config template
│   └── UserSettings.xml.template           # User settings template
├── Configurations/                         # Environment configs
├── Data/                                   # Test data and SQL scripts
├── .github/                                # GitHub templates
│   ├── ISSUE_TEMPLATE/                     # Issue templates
│   ├── pull_request_template.md            # PR template
│   └── CODEOWNERS                          # Code ownership
├── pom.xml                                 # Maven configuration
├── .editorconfig                           # Editor settings
├── .pre-commit-config.yaml                 # Pre-commit hooks
└── mvnw                                    # Maven wrapper (no install needed!)
```

---

## 🎯 Test Suites

| Suite | Files | Description |
|-------|-------|-------------|
| **Google** | 5 | Search, Maps, Flights |
| **Microsoft** | 33 | Azure, Office365, OneDrive, Rewards |
| **LinkedIn** | 8 | Profile, Connections, Jobs |
| **Vivit** | 25 | Community portal testing |
| **BTS** | 60 | Internal PolicyStar applications |
| **Atlassian** | 22 | Jira, Confluence, Bamboo, Stash |
| **Bitcoin** | 1 | Cryptocurrency testing |
| **Dropbox** | 3 | File sharing |
| **United Airlines** | 8 | Booking, Account management |
| **Wellmark** | 8 | Healthcare portal |
| **YourMembership** | 61 | API testing suite |
| ... | ... | 25+ more domains |

**Total**: 394+ test files across 30+ domains

---

## 🏗️ Architecture

### Design Pattern: Page Object Model (POM)

```
Test Layer (Scenarios.java)
    ↓
Page Objects (LoginPage, SearchPage, etc.)
    ↓
Selenium Wrapper (SeleniumWebDriver, Page)
    ↓
WebDriver (Chrome, Firefox, Edge, etc.)
```

### Key Components

- **Core Framework** (`com.cjs.qa.core`)
  - `Environment.java` - Environment management
  - `AutGui.java` - Application under test interface
  - `QAException.java` - Custom exception handling

- **Selenium Layer** (`com.cjs.qa.selenium`)
  - `SeleniumWebDriver.java` - WebDriver wrapper
  - `Page.java` - Base page object
  - `ISelenium.java` - Selenium interface

- **Utilities** (`com.cjs.qa.utilities`)
  - `SecureConfig.java` - Google Cloud Secret Manager integration
  - `GoogleCloud.java` - Secret retrieval
  - `JavaHelpers.java` - Helper methods
  - `FSO.java` - File system operations
  - `Email.java` - Email utilities

- **Page Objects** (per domain)
  - Domain-specific page objects
  - Organized by application/site

---

## 🔐 Security

### Secure Credential Management

This framework uses **Google Cloud Secret Manager** for all credentials:

- ✅ **Zero hardcoded passwords** in source code
- ✅ **AES-256 encryption** at rest
- ✅ **TLS encryption** in transit
- ✅ **IAM-based access control**
- ✅ **Full audit logging**
- ✅ **Secret versioning and rotation**

**43 secrets** stored securely with `AUTO_` prefix naming.

### Usage in Tests
```java
// Passwords retrieved securely from Google Cloud
String password = EPasswords.BTSQA.getValue();
// No hardcoded credentials! ✅
```

### Protected Files

These files are **protected by .gitignore** and never committed:
- `XML/Companies.xml` - Company passwords
- `XML/UserSettings.xml` - Test credentials
- `Configurations/Environments.xml` - Environment configs
- Any `*-key.json` - Service account keys

**See**: `docs/INTEGRATION_COMPLETE.md` for full security details.

---

## 📊 Test Reporting

Test reports are generated in multiple formats:

### JUnit Reports
Location: `target/surefire-reports/`
```bash
./mvnw test
open target/surefire-reports/index.html
```

### Cucumber Reports
Location: `target/cucumber-reports/`
```bash
./mvnw test
open target/cucumber-reports/cucumber.html
```

### TestNG Reports
Location: `target/surefire-reports/`

---

## 🔧 Development

### Build Commands

```bash
# Clean build
./mvnw clean

# Compile main code
./mvnw compile

# Compile tests
./mvnw test-compile

# Run all tests
./mvnw test

# Package (skip tests)
./mvnw package -DskipTests

# Run specific test suite
./mvnw test -Dtest=Scenarios#Google
```

### Helper Scripts

```bash
# Run tests with browser selection
./scripts/run-tests.sh Scenarios chrome

# Run specific test method
./scripts/run-specific-test.sh Scenarios Microsoft

# Compile without tests (faster)
./scripts/compile.sh
```

### Code Quality

Pre-commit hooks automatically check:
- ✅ Trailing whitespace
- ✅ File endings
- ✅ YAML/XML/JSON syntax
- ✅ Hardcoded secrets detection
- ✅ Sensitive file blocking
- ✅ Large file warnings

```bash
# Install hooks
pip install pre-commit
pre-commit install

# Run manually
pre-commit run --all-files
```

---

## 🌐 Selenium Grid

### Local Grid
```bash
# Framework supports Selenium Grid for parallel execution
# Configure in Configurations/Environments.xml
<RUN_REMOTE>true</RUN_REMOTE>
```

### Remote Execution
```bash
# Run on Selenium Grid
./mvnw test -DrunRemote=true -DgridHub=http://localhost:4444/wd/hub
```

---

## 📚 Documentation

Comprehensive documentation available in `/docs`:

### Getting Started
- **[NEXT_STEPS.md](docs/NEXT_STEPS.md)** - What to do next
- **[INTEGRATION_COMPLETE.md](docs/INTEGRATION_COMPLETE.md)** - Secret Manager setup

### Planning & Analysis
- **[ANALYSIS.md](docs/ANALYSIS.md)** - Full project analysis
- **[ANALYSIS_SUGGESTIONS.md](docs/ANALYSIS_SUGGESTIONS.md)** - 150-task roadmap (40/150 complete)

### Implementation Guides
- **[ANALYSIS_PS_RESULTS.md](docs/ANALYSIS_PS_RESULTS.md)** - Password migration results
- **[QUICK_WINS_COMPLETE.md](docs/QUICK_WINS_COMPLETE.md)** - Quick wins summary

### Configuration
- **[XML/README.md](XML/README.md)** - XML configuration setup
- **[Configurations/README.md](Configurations/README.md)** - Environment configuration
- **[scripts/README.md](scripts/README.md)** - Script usage guide

---

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Make your changes**
4. **Run tests** (`./mvnw test`)
5. **Commit your changes** (pre-commit hooks will run automatically)
6. **Push to branch** (`git push origin feature/amazing-feature`)
7. **Open a Pull Request** (template will auto-populate)

### Pull Request Guidelines
- Use the PR template provided
- Ensure all tests pass
- Update documentation if needed
- No hardcoded secrets (pre-commit hooks will block)
- Follow code style (.editorconfig)

See **[CODE_OF_CONDUCT.md](docs/CODE_OF_CONDUCT.md)** for community guidelines.

---

## 👥 Team Setup

### New Team Member Onboarding

```bash
# 1. Clone repository
git clone https://github.com/CScharer/selenium_java_docker.git
cd selenium_java_docker

# 2. Authenticate with Google Cloud
gcloud auth application-default login
gcloud config set project cscharer

# 3. Copy templates
cp XML/Companies.xml.template XML/Companies.xml
cp XML/UserSettings.xml.template XML/UserSettings.xml

# 4. Run tests!
./mvnw clean test
```

**No password sharing needed!** All credentials are fetched from Google Cloud Secret Manager.

---

## 🔬 Test Examples

### Run Specific Test Suite
```bash
# Google tests
./mvnw test -Dtest=Scenarios#Google

# Microsoft tests
./mvnw test -Dtest=Scenarios#Microsoft

# LinkedIn tests
./mvnw test -Dtest=Scenarios#LinkedIn
```

### Run with Different Browsers
```bash
# Chrome (default)
./mvnw test -Dbrowser=chrome

# Firefox
./mvnw test -Dbrowser=firefox

# Edge
./mvnw test -Dbrowser=edge
```

### Using Helper Scripts
```bash
# Easy syntax for running tests
./scripts/run-tests.sh Scenarios chrome
./scripts/run-tests.sh Scenarios firefox

# Run specific test method
./scripts/run-specific-test.sh Scenarios Google
```

---

## 🛠️ Technology Details

### Dependencies (Key Libraries)

| Category | Library | Version |
|----------|---------|---------|
| **WebDriver** | Selenium | 4.26.0 |
| **BDD** | Cucumber | 7.20.1 |
| **Testing** | JUnit | 4.13.2 |
| **Testing** | TestNG | 7.20.1 |
| **Database** | JDBC (Multi-DB) | Various |
| **HTTP** | Apache HttpClient | 4.5.14 |
| **JSON** | Gson | 2.11.0 |
| **Excel** | Apache POI | 5.3.0 |
| **PDF** | PDFBox | 3.0.3 |
| **Security** | Google Cloud Secret Manager | 2.48.0 |
| **Database** | H2, SQLite, MSSQL | Various |

**Total**: 50+ dependencies, all managed via Maven

---

## 📈 Project Stats

- **Test Files**: 394+ Java files
- **Test Suites**: 30+ domains
- **Page Objects**: 150+ pages
- **Utilities**: 43 helper classes
- **Lines of Code**: 100,000+ lines
- **Compilation**: 100% success rate
- **Security**: 43 secrets in Google Cloud
- **Documentation**: 200+ pages

---

## 🏆 Recent Achievements

### November 8, 2025
- ✅ **Security Overhaul Complete**
  - Migrated 43 passwords to Google Cloud Secret Manager
  - Removed all hardcoded credentials
  - Security status: CRITICAL → SECURE

- ✅ **Quick Wins Complete**
  - Maven wrapper added
  - Helper scripts created
  - Pre-commit hooks configured
  - GitHub templates added
  - License and Code of Conduct added

- ✅ **Progress**
  - 40/150 tasks completed (27%)
  - 4/10 milestones achieved
  - 7 commits, 500+ files added

---

## 🐛 Troubleshooting

### Common Issues

#### "Failed to fetch secret"
```bash
# Solution: Authenticate with Google Cloud
gcloud auth application-default login
```

#### "Maven not found"
```bash
# Solution: Use Maven wrapper (included in repo)
./mvnw clean test
# NOT: mvn clean test
```

#### "Driver not found" (Windows)
```bash
# Solution: Drivers are managed automatically
# No manual driver installation needed
```

#### "Configuration file not found"
```bash
# Solution: Copy template files
cp XML/Companies.xml.template XML/Companies.xml
```

**More help**: See `docs/` directory for comprehensive guides.

---

## 📞 Support

### Getting Help
- **Documentation**: Check `/docs` directory
- **Issues**: Use GitHub issue templates
- **Questions**: Create a discussion on GitHub

### Resources
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs)
- [Google Cloud Secret Manager](https://cloud.google.com/secret-manager/docs)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

Copyright © 2025 CJS Consulting, L.L.C

---

## 🙏 Acknowledgments

- **Framework**: Selenium WebDriver, Cucumber BDD
- **Security**: Google Cloud Secret Manager
- **Build Tool**: Apache Maven
- **Testing**: JUnit, TestNG
- **CI/CD**: Travis CI, GitHub Actions (planned)

---

## 📞 Contact

**Organization**: CJS Consulting, L.L.C
**Website**: http://www.cjsconsulting.com
**CI**: Jenkins at http://cscharer-laptop:8080/

---

## 🚀 What's Next?

Check out our roadmap in `docs/ANALYSIS_SUGGESTIONS.md`:
- 40/150 tasks completed (27%)
- Phase 1 (Security): ✅ COMPLETE
- Quick Wins: ✅ COMPLETE
- Phase 2 (Docker): In progress
- Phase 3 (Documentation): Planned
- Phase 4 (Advanced Features): Planned

**Want to contribute?** See `docs/ANALYSIS_SUGGESTIONS.md` for the full roadmap!

---

<div align="center">

**Built with ❤️ by the CJS QA Team**

⭐ Star this repo if you find it useful!

</div>
