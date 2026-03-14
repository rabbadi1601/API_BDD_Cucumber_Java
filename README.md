# API BDD Framework with Cucumber and Java

This project is a Behavior-Driven Development (BDD) framework for testing REST APIs using Cucumber, Java, and RestAssured. It supports testing various HTTP methods like GET, POST, PUT, PATCH, and DELETE with data parameterization and response validation.

## Features

- **Feature Files**: Gherkin-based feature files for defining test scenarios.
  - `GetProducts.feature`: Tests GET requests to retrieve products.
  - `PostProducts.feature`: Tests POST requests to create new products with parameterized data.
  - `PutProducts.feature`: Tests PUT requests to update existing products.
  - `PatchProducts.feature`: Tests PATCH requests for partial updates.
  - `DeleteProducts.feature`: Tests DELETE requests to remove products.

- **Step Definitions**: Java classes implementing the steps defined in feature files.
  - `Products.java`: Contains all step definitions for the API tests, including setup, requests, and validations. Also includes Cucumber hooks (@Before and @After) for scenario setup and teardown.

- **Configuration**: Centralized configuration using properties files.
  - `config.properties`: Stores the base URL for the API.

- **Utilities**: Helper classes for common operations.
  - `ConfigReader.java`: Reads configuration properties.
  - `APIUtils.java`: Provides utility methods for API interactions, such as setting base URI, building request specifications, and constructing JSON payloads.

- **Test Runner**: `TestRunner.java` executes the Cucumber tests and generates HTML, JSON, and Allure reports.

## Prerequisites

- Java 8 or higher
- Maven 3.x
- IDE (e.g., IntelliJ IDEA, Eclipse) with Cucumber and RestAssured plugins

## Setup

1. Clone or download the project.
2. Open the project in your IDE.
3. Ensure Maven dependencies are resolved. The `pom.xml` includes:
   - Cucumber Java
   - Cucumber JUnit
   - RestAssured
   - JUnit

## Configuration

- Edit `src/test/resources/config.properties` to set the base URL:
  ```
  baseURL=https://fakestoreapi.com
  ```
- The framework reads this URL dynamically using `ConfigReader`.

## Running Tests

- Run tests via Maven: `mvn test`
- Or run specific feature files through your IDE's Cucumber runner.
- Tests use the Fake Store API (https://fakestoreapi.com) for demonstration.
- **Reports**: After running tests, HTML and JSON reports are generated in `target/cucumber-reports.html` and `target/cucumber-json/Cucumber.json`. Open the HTML report in a browser for detailed test results. Additionally, Allure reports are generated in `target/allure-results` – run `mvn allure:serve` to view the interactive Allure report in a browser.

## Project Structure

```
API_BDD_Cucumber_Java/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── App.java
│   │   └── resources/
│   │       ├── archetype-resources/
│   │       │   ├── pom.xml
│   │       │   └── src/
│   │       │       ├── main/
│   │       │       │   └── java/
│   │       │       │       └── App.java
│   │       │       └── test/
│   │       │           └── java/
│   │       │               └── AppTest.java
│   │       └── META-INF/
│   │           └── maven/
│   │               └── archetype.xml
│   └── test/
│       ├── java/
│       │   ├── features/
│       │   │   ├── DeleteProducts.feature
│       │   │   ├── GetProducts.feature
│       │   │   ├── PatchProducts.feature
│       │   │   ├── PostProducts.feature
│       │   │   └── PutProducts.feature
│       │   ├── pages/
│       │   ├── runner/
│       │   │   └── TestRunner.java
│       │   ├── stepdefinitions/
│       │   │   └── Products.java
│       │   └── utlites/
│       │       ├── APIUtils.java
│       │       └── ConfigReader.java
│       └── resources/
│           └── config.properties
└── target/
    └── ...
```

### TestRunner Configuration

The `TestRunner.java` is the entry point for running Cucumber tests:

```java
package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = "stepdefinitions",
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber-json/Cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    monochrome = true
)
public class TestRunner {
}
```

- **features**: Path to the `.feature` files.
- **glue**: Package containing step definitions.
- **plugin**: Report formats (console, HTML, JSON, Allure).
- **monochrome**: Cleaner console output.

## Usage

### Writing New Features

1. Create a new `.feature` file in `src/test/java/features/`.
2. Define scenarios using Gherkin syntax with Scenario Outline for parameterization.
3. Implement corresponding step definitions in `Products.java` or a new class.

### Adding New Utilities

- Extend `APIUtils.java` with additional helper methods.
- Use `ConfigReader.java` for any new configuration needs.

### Using Cucumber Hooks

Cucumber hooks are methods that run before or after scenarios or steps, useful for setup, teardown, or logging.

- `@Before`: Runs before each scenario (e.g., set up base URI).
- `@After`: Runs after each scenario (e.g., log results or clean up).
- `@BeforeStep` / `@AfterStep`: Run before/after each step.

Example in `Products.java`:

```java
@Before
public void setUp() {
    RestAssured.baseURI = ConfigReader.getBaseURL();
    System.out.println("Setting up base URI.");
}

@After
public void tearDown() {
    System.out.println("Scenario completed.");
}
```

Hooks ensure consistent behavior across tests.

## Example Test Scenario

From `PostProducts.feature`:

```gherkin
Scenario Outline: Create a new product with valid data
  Given I have the Products API endpoint
  When I send a POST request to the endpoint with title "<title>", price "<price>", and description "<description>"
  Then I should receive a response with status code 201
  And the response body should contain the created product with title "<title>", price "<price>", and description "<description>"

  Examples:
    | title    | price | description          |
    | Laptop   | 999   | High-performance laptop |
```

This scenario creates products with different data sets and validates the response.

## Contributing

- Follow BDD best practices.
- Ensure all tests pass before committing.
- Update documentation for any new features.

## License

This project is for educational purposes. Use at your own risk.

### Allure Reports Configuration

Allure is an open-source framework for test reporting that provides interactive, detailed reports with timelines, attachments, and step-by-step breakdowns.

#### 1. **Dependency Configuration in `pom.xml`**
   - **Allure Cucumber JVM Dependency**:
     ```xml
     <dependency>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-cucumber7-jvm</artifactId>
       <version>2.24.0</version>
     </dependency>
     ```
     - Integrates Allure with Cucumber 7.

   - **Maven Surefire Plugin** (for result collection):
     ```xml
     <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-surefire-plugin</artifactId>
       <version>3.2.5</version>
       <configuration>
         <systemPropertyVariables>
           <allure.results.directory>target/allure-results</allure.results.directory>
         </systemPropertyVariables>
       </configuration>
     </plugin>
     ```
     - Sets the directory where Allure stores raw test results (JSON files, attachments).

   - **Allure Maven Plugin** (for report generation):
     ```xml
     <plugin>
       <groupId>io.qameta.allure</groupId>
       <artifactId>allure-maven</artifactId>
       <version>2.12.0</version>
     </plugin>
     ```
     - Provides goals like `allure:serve` to generate and view reports.

#### 2. **TestRunner Plugin Configuration**
   In `TestRunner.java`, the Allure plugin is added to the `@CucumberOptions` plugin array:
   ```java
   plugin = {
       "pretty",
       "html:target/cucumber-reports.html",
       "json:target/cucumber-json/Cucumber.json",
       "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
   }
   ```
   - **Purpose**: This plugin hooks into Cucumber's execution, collecting test data (steps, results, logs, screenshots) and writing it to the configured results directory.

#### 3. **How Allure Reports Are Generated**
   - **During Test Execution** (`mvn test`):
     - The Allure plugin captures data from each scenario/step (e.g., Gherkin steps, assertions, failures).
     - Raw results are saved as JSON files in `target/allure-results` (e.g., `*-result.json`, `*-container.json`).
     - Attachments (e.g., request/response bodies) can be added programmatically if needed.

   - **Report Generation and Viewing**:
     - Run `mvn allure:serve` after tests.
     - The Allure Maven plugin reads from `target/allure-results`, processes the data, and generates an interactive HTML report.
     - Opens the report in a browser (default: `http://localhost:8080`) with features like:
       - Test timelines and trends.
       - Step-by-step breakdowns.
       - Screenshots and logs.
       - Filtering by status, tags, or suites.

   - **Alternative**: `mvn allure:report` generates a static HTML site in `target/site/allure-maven-plugin/` without serving.

#### Example Workflow:
1. Run tests: `mvn test` → Results saved to `target/allure-results`.
2. View report: `mvn allure:serve` → Interactive report in browser.
3. For CI/CD, integrate with tools like Jenkins for automated report publishing.

Allure provides richer insights than basic HTML/JSON reports, making it ideal for detailed test analysis. If you need code examples for adding attachments or custom steps, let me know!
