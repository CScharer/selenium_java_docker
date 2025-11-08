# CJS QA Automation Framework

![CI Pipeline](https://github.com/CScharer/selenium_java_docker/workflows/Selenium%20Grid%20CI%2FCD%20Pipeline/badge.svg)
[![Tests](https://img.shields.io/badge/tests-77%20total%20(UI:%2046%20%7C%20API:%2031)-brightgreen)](https://github.com/CScharer/selenium_java_docker/actions)
[![Allure Report](https://img.shields.io/badge/📊_Allure-Report-orange.svg)](https://cscharer.github.io/selenium_java_docker/)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Selenium](https://img.shields.io/badge/Selenium-4.26.0-green.svg)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.20.1-brightgreen.svg)](https://cucumber.io/)
[![REST Assured](https://img.shields.io/badge/REST%20Assured-5.4.0-blue.svg)](https://rest-assured.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9.11-blue.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-ready-blue.svg)](https://www.docker.com/)
[![Code Quality](https://img.shields.io/badge/Code%20Quality-Checkstyle%20%7C%20SpotBugs%20%7C%20PMD-success.svg)](https://github.com/CScharer/selenium_java_docker/actions)
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
- 🎯 **77 Test Scenarios** - UI (46) + API (31)
- 🌐 **REST API Testing** - REST Assured 5.4.0 for API automation
- 📊 **Extended Coverage** - Data-driven, negative tests, advanced features
- 🔐 **Secure Credential Management** - Google Cloud Secret Manager integration (0 hardcoded passwords!)
- ⚡ **Smoke Test Suite** - Fast critical path verification in < 2 minutes
- 🚀 **Parallel Execution** - Native parallel test support (3-5 threads)
- 🌐 **Multi-Browser Support** - Chrome, Firefox, Edge with Selenium Grid
- 📊 **Beautiful Reports** - Allure reports with automatic screenshot capture
- 🐳 **Fully Containerized** - Docker + Docker Compose with 3 environments
- 🤖 **CI/CD Automated** - GitHub Actions pipeline with fail-fast smoke tests
- 🎨 **Page Object Model** - Clean, maintainable test architecture
- 📸 **Visual Evidence** - Screenshots on every test (success & failure)
- 🧪 **Professional Testing** - Log4j 2, Allure, TestNG, REST Assured

### Modern Technology Stack
- **Java 17** - Latest LTS version
- **Selenium 4.26.0** - Modern WebDriver API with Grid support
- **REST Assured 5.4.0** - REST API testing & validation
- **Cucumber 7.20.1** - BDD framework with Gherkin
- **TestNG** - Advanced test framework with data providers
- **Log4j 2.22.0** - Professional structured logging
- **Maven 3.9.11** - Build management (wrapper included!)
- **Docker & Docker Compose** - Complete containerization
- **Allure 2.25.0** - Beautiful test reporting with screenshots
- **GitHub Actions** - Automated CI/CD pipeline
- **Google Cloud Secret Manager** - Enterprise-grade security
- **WebDriverManager 5.9.2** - Automatic driver management

### Recent Improvements (November 8, 2025)
- ✅ **API Testing Integration** - 31 REST API tests with REST Assured 5.4.0
- ✅ **Extended Test Coverage** - 77 total tests (46 UI + 31 API)
- ✅ **Log4j 2 Logging** - Professional structured logging with rotation & archiving
- ✅ **Code Quality Tools** - Checkstyle, SpotBugs, PMD automated analysis
- ✅ **Smoke Test Suite** - 5 fast tests for critical path verification (< 2 min)
- ✅ **Containerized Testing** - Complete Docker + Selenium Grid setup
- ✅ **Allure Reporting** - Beautiful HTML reports with automatic screenshots
- ✅ **GitHub Pages** - Public test reports at https://cscharer.github.io/selenium_java_docker/
- ✅ **GitHub Actions CI/CD** - Automated testing with fail-fast strategy
- ✅ **77 Working Tests** - UI (46) + API (31)
- ✅ **Screenshot Capture** - Visual evidence for every test execution
- ✅ **Multi-Browser** - Matrix testing across Chrome & Firefox
- ✅ **Google Cloud Secrets** - 43 passwords secured (0 hardcoded!)
- ✅ **WebDriverManager** - Automatic driver management (no manual downloads)
- ✅ **Maven Wrapper** - Reproducible builds without Maven installation
- ✅ **Pre-commit Hooks** - Automated code quality checks

---

## 🚀 Quick Start

### **Option 1: Docker (Recommended - No Setup Required!)**

```bash
# 1. Clone the repository
git clone https://github.com/CScharer/selenium_java_docker.git
cd selenium_java_docker

# 2. Start Selenium Grid
docker-compose up -d selenium-hub chrome-node-1

# 3. Run tests with beautiful Allure reports
./scripts/generate-allure-report.sh

# That's it! Tests run in Docker, report opens automatically! 🎉
```

### **Option 2: Local Execution**

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

### Quick Smoke Tests (⚡ < 2 minutes)

Fast critical path verification before committing:

```bash
# Run smoke tests (5 critical tests)
./scripts/run-smoke-tests.sh

# Or with Docker directly
docker-compose up -d selenium-hub chrome-node-1
docker-compose run --rm tests -Dtest=SmokeTests
docker-compose down
```

**What it does:**
- ✅ Verifies Grid connection
- ✅ Tests basic navigation
- ✅ Checks search functionality
- ✅ Validates form interaction
- ✅ Fast feedback (< 2 min vs 15+ min full suite)

### Extended Test Suite (📊 30+ scenarios)

Comprehensive testing with data-driven, negative, and advanced tests:

```bash
# Run all extended tests
docker-compose up -d selenium-hub chrome-node-1
docker-compose run --rm tests -DsuiteXmlFile=testng-extended-suite.xml
docker-compose down
```

**What it includes:**

**Data-Driven Tests (19 scenarios):**
- ✅ Multiple search queries (5 data sets)
- ✅ Website accessibility (5 sites)
- ✅ Edge case inputs (4 scenarios)

**Negative Tests (7 scenarios):**
- ✅ Non-existent element handling
- ✅ Invalid URL navigation
- ✅ Timeout handling
- ✅ Error recovery
- ✅ Stale element handling

**Advanced Features (7 scenarios):**
- ✅ JavaScript execution
- ✅ Cookie management
- ✅ Window management
- ✅ Keyboard actions
- ✅ Browser navigation
- ✅ Performance metrics

### API Testing (🌐 31 REST API tests)

REST API testing with REST Assured - **No Selenium Grid required!**

```bash
# Run all API tests (fast, no browser needed)
./scripts/run-api-tests.sh

# Or with Maven directly
./mvnw test -DsuiteXmlFile=testng-api-suite.xml
```

**What it includes:**

**Basic API Tests (7 tests):**
- ✅ GET requests & status codes
- ✅ Response body validation
- ✅ Header verification
- ✅ Response time testing
- ✅ Query parameters
- ✅ Collection retrieval
- ✅ Error handling (404)

**CRUD Operations (7 tests):**
- ✅ CREATE (POST)
- ✅ READ (GET)
- ✅ UPDATE (PUT)
- ✅ PATCH (partial update)
- ✅ DELETE
- ✅ Nested resources
- ✅ Filtering

**Data-Driven API Tests (10 tests):**
- ✅ Multiple endpoints (6 data sets)
- ✅ Various post IDs (7 data sets)
- ✅ Invalid endpoints (3 data sets)
- ✅ Content types (3 data sets)

**JSON Validation (7 tests):**
- ✅ Structure validation
- ✅ Data type checking
- ✅ Array validation
- ✅ Nested object validation
- ✅ Collection operations
- ✅ Complex JSON paths
- ✅ Response size validation

**Benefits:**
- ⚡ **Fast**: No browser startup overhead
- 🚀 **Lightweight**: Run anywhere (CI/CD, local, Docker)
- 📊 **Integrated**: Same Allure reports as UI tests
- 🔄 **Reusable**: REST Assured for all API testing

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

### Allure Reports (Recommended) 🎯

Beautiful, interactive HTML reports with screenshots, graphs, and trends:

```bash
# Option 1: One-command (starts Grid, runs tests, opens report)
./scripts/generate-allure-report.sh

# Option 2: Manual
docker-compose up -d selenium-hub chrome-node-1
docker-compose run --rm tests -Dtest=SimpleGridTest,EnhancedGridTests
allure serve target/allure-results
docker-compose down
```

**Features:**
- 📊 Interactive dashboards with graphs
- 📸 Screenshots automatically captured
- 📈 Historical trends (track improvements)
- 🏷️ Organized by Epic/Feature/Story
- ⏱️ Performance metrics
- 🎯 Severity-based filtering

**See:** [docs/ALLURE_REPORTING.md](docs/ALLURE_REPORTING.md) for complete guide

### Traditional Reports

**JUnit Reports**
```bash
./mvnw test
open target/surefire-reports/index.html
```

**Cucumber Reports**
```bash
./mvnw test
open target/cucumber-reports/cucumber.html
```

**TestNG Reports**
```bash
# Available in target/surefire-reports/
```

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

## 🐳 Docker & Selenium Grid

### Quick Start with Docker

```bash
# 1. Start Selenium Grid
docker-compose up -d selenium-hub chrome-node-1 firefox-node

# 2. View Grid Console
open http://localhost:4444

# 3. Run tests with Allure report
./scripts/generate-allure-report.sh

# 4. Or run tests manually
docker-compose run --rm tests -Dtest=SimpleGridTest,EnhancedGridTests

# 5. Stop everything
docker-compose down
```

### Three Docker Environments

- **`docker-compose.yml`** - Full setup with monitoring (Prometheus + Grafana)
- **`docker-compose.dev.yml`** - Lightweight for development
- **`docker-compose.prod.yml`** - Production with auto-scaling (4 Chrome + 2 Firefox nodes)

```bash
# Use specific environment
docker-compose -f docker-compose.dev.yml up -d
docker-compose -f docker-compose.prod.yml up -d
```

### Selenium Grid Console & Debugging

- **Grid UI**: http://localhost:4444
- **Prometheus**: http://localhost:9090
- **Grafana**: http://localhost:3000 (admin/admin)
- **Chrome VNC**: vnc://localhost:5900 or http://localhost:7900 (noVNC)
- **Firefox VNC**: vnc://localhost:5902 or http://localhost:7902 (noVNC)

### Docker Features

- ✅ **Selenium Grid** - Hub + 4 browser nodes (2 Chrome, Firefox, Edge)
- ✅ **VNC/noVNC** - Visual debugging (watch tests run live!)
- ✅ **Monitoring** - Prometheus metrics + Grafana dashboards
- ✅ **Multi-stage builds** - Optimized 414MB image
- ✅ **ARM64 support** - Works on Apple Silicon (M1/M2/M3)
- ✅ **Auto-scaling** - Scale nodes with `docker-compose up --scale`
- ✅ **Health checks** - Automatic service monitoring
- ✅ **WebDriverManager** - No manual driver downloads

**See [docs/DOCKER.md](docs/DOCKER.md) for complete Docker guide**

### Grid Management Scripts

```bash
./scripts/docker/grid-start.sh   # Start Grid
./scripts/docker/grid-stop.sh    # Stop Grid
./scripts/docker/grid-health.sh  # Check health
./scripts/docker/grid-scale.sh   # Scale nodes
```

---

## 🤖 CI/CD - GitHub Actions

### Automated Testing Pipeline

Every push to `main` or `develop` triggers:

```
✅ Build & Compile → ✅ Grid Tests (Chrome) → ✅ Allure Report
                   → ✅ Grid Tests (Firefox) → ✅ Code Quality
                                             → ✅ Docker Build
                                             → ✅ Test Summary
```

**Matrix Testing:**
- 11 tests × 2 browsers = **22 test executions**
- Automatic screenshot capture (22 screenshots per run)
- Allure report with graphs and trends
- GitHub Pages deployment

**View Results:**
- [GitHub Actions Tab](https://github.com/CScharer/selenium_java_docker/actions)
- Check build status badge above
- Download artifacts (test results, screenshots, Allure reports)

**See:** [docs/GITHUB_ACTIONS.md](docs/GITHUB_ACTIONS.md) for complete CI/CD guide

---

## 📚 Documentation

Comprehensive documentation available in `/docs`:

### Core Guides
- **[DOCKER.md](docs/DOCKER.md)** - Complete Docker & Grid guide (500+ lines)
- **[ALLURE_REPORTING.md](docs/ALLURE_REPORTING.md)** - Allure setup & usage (500+ lines)
- **[GITHUB_ACTIONS.md](docs/GITHUB_ACTIONS.md)** - CI/CD pipeline guide (400+ lines)

### Getting Started
- **[NEXT_STEPS.md](docs/NEXT_STEPS.md)** - What to do next
- **[INTEGRATION_COMPLETE.md](docs/INTEGRATION_COMPLETE.md)** - Secret Manager setup

### Planning & Analysis
- **[ANALYSIS.md](docs/ANALYSIS.md)** - Full project analysis
- **[ANALYSIS_SUGGESTIONS.md](docs/ANALYSIS_SUGGESTIONS.md)** - 150-task roadmap (65/150 complete)

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
| **Driver Management** | WebDriverManager | 5.9.2 |
| **Database** | H2, SQLite, MSSQL | Various |
| **Docker** | Docker Compose | 3.8 |
| **CI/CD** | GitHub Actions | Latest |

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

### November 8, 2025 - Major Infrastructure Update

**✅ Complete Containerized Testing Infrastructure**
- Docker + Selenium Grid (Hub + 4 browser nodes)
- 3 Docker Compose environments (default, dev, prod)
- Monitoring stack (Prometheus + Grafana)
- VNC/noVNC debugging support
- ARM64 (Apple Silicon) compatibility

**✅ Allure Reporting with Screenshots**
- Allure Framework 2.25.0 integration
- Automatic screenshot capture on every test
- Beautiful HTML dashboards with graphs
- Epic/Feature/Story organization
- Historical trend tracking
- **📊 [View Latest Report](https://cscharer.github.io/selenium_java_docker/)** - Public GitHub Pages

**✅ GitHub Actions CI/CD Pipeline**
- Automated testing on every push
- Matrix testing (Chrome + Firefox)
- 6 parallel jobs (build, test, report, quality, docker, summary)
- Allure report generation and deployment
- Test artifact retention (7-30 days)

**✅ Working Test Suite (11 tests, 100% passing)**
- SimpleGridTest (3 tests) - Basic Grid verification
- EnhancedGridTests (8 tests) - Comprehensive scenarios
- All with Allure annotations and screenshots
- TestNG parallel execution ready

**✅ Security & Quality**
- 43 passwords secured in Google Cloud
- Zero hardcoded credentials
- Pre-commit hooks active
- WebDriverManager (auto driver management)
- AllureHelper utility for enhanced reporting

**✅ Progress Update**
- 65/150 tasks completed (43%)
- Phase 1 (Security): 100% ✅
- Phase 2 (Docker & Infrastructure): 100% ✅
- Phase 3 (Testing & Reporting): 80% ✅
- 12 commits today, 2,000+ lines added

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
- **65/150 tasks completed (43%)** 🎯
- Phase 1 (Security): ✅ COMPLETE
- Quick Wins: ✅ COMPLETE  
- Phase 2 (Docker & Infrastructure): ✅ COMPLETE
- Phase 3 (Testing & Reporting): ✅ 80% Complete
- Phase 4 (Advanced Features): 📋 Planned

### Upcoming Features
- Code quality tools (Checkstyle, SpotBugs, PMD)
- Smoke test suite for fast feedback
- Extended test coverage (50+ tests)
- API testing integration
- Performance testing
- Mobile testing support

**Want to contribute?** See `docs/ANALYSIS_SUGGESTIONS.md` for the full roadmap!

---

<div align="center">

**Built with ❤️ by the CJS QA Team**

⭐ Star this repo if you find it useful!

</div>
