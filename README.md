# WBWebAutomatedTests

## Project Overview
WBWebAutomatedTest is a Selenium-based automated testing project for testing the web application available at https://webook.com/en. The project includes automated tests for registration and search features.

## Prerequisites
Before you can run the tests, ensure that you have the following installed:

- **Java Development Kit (JDK):** Version 11 or higher.
- **Apache Maven:** Version 3.6 or higher.
- **Git:** For version control.

## Project Structure
```bash
WBWebAutomatedTest/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/example/
│   │   │       ├── pageobjects/
│   │   │       │   └── BasePage.java
│   │   │       │   └── LoginAndRegistrationPage.java
│   │   │       │   └── WelcomePage.java
│   │   │       ├── utils/
│   │   │       │   └── PropertiesFileReader.java
│   │   │       └── driver/
│   │   │           └── Driver.java
│   │   └── resources/
│   │       └── pagesconfig/
│   │           └── LoginAndRegistrationConfigs.properties
│   │       └── runConfig.properties
│   └── test/
│       └── java/
│           └── tests/
│               └── BaseTest.java
│               └── RegistrationTest.java
│               └── SearchTest.java
├── pom.xml
└── README.md
└── testng.xml
```

- pageobjects/: Contains the Page Object Model (POM) classes for different pages of the web application.
- utils/: Contains utility classes, such as the PropertiesFileReader, for reading configuration files.
- driver/: Contains the DriverFactory class for managing WebDriver instances.
- tests/: Contains the test classes written using JUnit.

## Setup Instructions
1. Clone the Repository
    ``` bash
    git clone https://github.com/yourusername/WBWebAutomatedTest.git
    cd WBWebAutomatedTest
    ```
2. Configure Browser Settings
You can specify the browser to run in the resources/runConfig file.

3. Install Dependencies
Use Maven to install all necessary dependencies:

    ```bash
    mvn clean install
    ```
4. Running the Tests
Run All Tests
To run all tests, use the following Maven command:

    ```bash
    mvn test
    ```
    Run a Specific Test
    To run a specific test class, use:

    ```bash
    mvn -Dtest=RegistrationTest test
    ```
## Reporting
* Test results are generated in the target/surefire-reports/ directory. 

## Future Enhancements
* Implement parallel test execution: Run tests in parallel across different browsers.
* Cover localization Tests Ar/En.
* Add more utils classes to cover different data readers. 
* Enhance the test data manipulation inside the script.
