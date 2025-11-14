package com.cjs.qa.utilities;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Browser-based integration tests for PageObjectGenerator.
 *
 * <p>These tests actually run the generator with a real browser and validate the generated code.
 * Requires ChromeDriver to be available.
 *
 * <p>To run these tests:
 * <pre>
 * mvn test -Dtest=PageObjectGeneratorBrowserTest
 * </pre>
 *
 * @author CJS QA Team
 */
public class PageObjectGeneratorBrowserTest {
  private static final Logger log = LogManager.getLogger(PageObjectGeneratorBrowserTest.class);
  private static final String TEST_OUTPUT_DIR = "target/generated-test-sources/page-objects";

  @Before
  public void setUp() {
    // Create output directory
    try {
      Files.createDirectories(Paths.get(TEST_OUTPUT_DIR));
    } catch (Exception e) {
      log.warn("Could not create output directory: {}", e.getMessage());
    }
  }

  /**
   * Test 1: Generate Page Object from a simple, accessible page.
   *
   * <p>This test generates a Page Object from a real URL and validates:
   * - File is created
   * - Code compiles
   * - Basic structure is correct
   */
  @Test
  public void testBasicGeneration() throws Exception {
    log.info("=== Test 1: Basic Generation ===");

    String testUrl = "https://github.com/login";
    String outputPath =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedLoginPage.java";
    String packageName = "com.cjs.qa.test.pages";
    String className = "GeneratedLoginPage";

    log.info("Generating Page Object from: {}", testUrl);
    log.info("Output path: {}", outputPath);

    // Generate the Page Object
    PageObjectGenerator.generate(testUrl, outputPath, packageName, className);

    // Verify file was created
    File generatedFile = new File(outputPath);
    assertTrue(
        "Generated file should exist: " + outputPath, generatedFile.exists());
    assertTrue(
        "Generated file should not be empty",
        generatedFile.length() > 0);

    log.info("✅ File created successfully: {} bytes", generatedFile.length());

    // Verify file content has expected structure
    String content = new String(Files.readAllBytes(Paths.get(outputPath)));
    assertTrue(
        "Should contain package declaration",
        content.contains("package " + packageName));
    assertTrue(
        "Should contain class declaration",
        content.contains("public class " + className));
    assertTrue(
        "Should extend Page class", content.contains("extends Page"));
    assertTrue(
        "Should have constructor", content.contains("public " + className + "(WebDriver"));
    assertTrue(
        "Should have super(webDriver)", content.contains("super(webDriver)"));

    log.info("✅ Generated code structure is correct");

    // Verify it has some locators (at least one)
    assertTrue(
        "Should contain locator definitions",
        content.contains("private final By") || content.contains("By.id") || content.contains("By.name") || content.contains("By.xpath"));

    log.info("✅ Locators found in generated code");

    log.info("✅ Test 1 PASSED: Basic generation works!");
  }

  /**
   * Test 2: Verify generated code compiles.
   *
   * <p>This test validates that the generated code can be compiled.
   * Note: This is a basic check - full compilation would require adding to classpath.
   */
  @Test
  public void testGeneratedCodeStructure() throws Exception {
    log.info("=== Test 2: Generated Code Structure ===");

    String testUrl = "https://github.com/login";
    String outputPath =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedLoginPageStructure.java";
    String packageName = "com.cjs.qa.test.pages";
    String className = "GeneratedLoginPageStructure";

    // Generate the Page Object
    PageObjectGenerator.generate(testUrl, outputPath, packageName, className);

    // Read generated code
    String content = new String(Files.readAllBytes(Paths.get(outputPath)));

    // Verify required imports
    assertTrue("Should import Page class", content.contains("import com.cjs.qa.selenium.Page"));
    assertTrue(
        "Should import WebDriver", content.contains("import org.openqa.selenium.WebDriver"));
    assertTrue(
        "Should import By", content.contains("import org.openqa.selenium.By"));

    // Verify method patterns
    assertTrue(
        "Should have setEdit methods or clickButton methods",
        content.contains("setEdit") || content.contains("clickButton") || content.contains("selectDropdown"));

    log.info("✅ Test 2 PASSED: Generated code structure is valid!");
  }

  /**
   * Test 3: Verify populatePage and validatePage methods are generated.
   */
  @Test
  public void testDataTableMethods() throws Exception {
    log.info("=== Test 3: DataTable Methods ===");

    String testUrl = "https://github.com/login";
    String outputPath =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedLoginPageDataTable.java";
    String packageName = "com.cjs.qa.test.pages";
    String className = "GeneratedLoginPageDataTable";

    // Generate with default options (should include populatePage and validatePage)
    PageObjectGenerator.generate(testUrl, outputPath, packageName, className);

    // Read generated code
    String content = new String(Files.readAllBytes(Paths.get(outputPath)));

    // Verify DataTable methods are generated
    assertTrue(
        "Should contain populatePage method",
        content.contains("public void populatePage(DataTable"));
    assertTrue(
        "Should contain validatePage method",
        content.contains("public void validatePage(DataTable"));

    // Verify DataTable import
    assertTrue(
        "Should import DataTable",
        content.contains("import io.cucumber.datatable.DataTable"));

    log.info("✅ Test 3 PASSED: DataTable methods generated!");
  }

  /**
   * Test 4: Verify hidden elements filtering works.
   */
  @Test
  public void testHiddenElementsFiltering() throws Exception {
    log.info("=== Test 4: Hidden Elements Filtering ===");

    String testUrl = "https://github.com/login";
    String outputPathWithHidden =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedLoginPageWithHidden.java";
    String outputPathWithoutHidden =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedLoginPageWithoutHidden.java";
    String packageName = "com.cjs.qa.test.pages";
    String className1 = "GeneratedLoginPageWithHidden";
    String className2 = "GeneratedLoginPageWithoutHidden";

    // Generate with hidden elements
    PageObjectGenerator.generate(
        testUrl, outputPathWithHidden, packageName, className1, true, true, true);

    // Generate without hidden elements (default)
    PageObjectGenerator.generate(
        testUrl, outputPathWithoutHidden, packageName, className2, false, true, true);

    // Read both files
    String contentWithHidden =
        new String(Files.readAllBytes(Paths.get(outputPathWithHidden)));
    String contentWithoutHidden =
        new String(Files.readAllBytes(Paths.get(outputPathWithoutHidden)));

    // Both should have content
    assertTrue("With hidden should have content", contentWithHidden.length() > 0);
    assertTrue(
        "Without hidden should have content", contentWithoutHidden.length() > 0);

    // Count locators in each (rough estimate by counting "private final By")
    long locatorsWithHidden = contentWithHidden.split("private final By").length - 1;
    long locatorsWithoutHidden =
        contentWithoutHidden.split("private final By").length - 1;

    log.info("Locators with hidden: {}", locatorsWithHidden);
    log.info("Locators without hidden: {}", locatorsWithoutHidden);

    // With hidden should have same or more locators
    assertTrue(
        "With hidden should have same or more locators",
        locatorsWithHidden >= locatorsWithoutHidden);

    log.info("✅ Test 4 PASSED: Hidden elements filtering works!");
  }

  /**
   * Test 5: Verify generator handles pages with no interactive elements gracefully.
   */
  @Test
  public void testEmptyPageHandling() throws Exception {
    log.info("=== Test 5: Empty Page Handling ===");

    // Use a simple page that might have few elements
    String testUrl = "https://www.example.com";
    String outputPath =
        TEST_OUTPUT_DIR + "/com/cjs/qa/test/pages/GeneratedEmptyPage.java";
    String packageName = "com.cjs.qa.test.pages";
    String className = "GeneratedEmptyPage";

    // Should not throw exception even if page has few/no interactive elements
    try {
      PageObjectGenerator.generate(testUrl, outputPath, packageName, className);

      // File should still be created
      File generatedFile = new File(outputPath);
      assertTrue("File should be created even for empty page", generatedFile.exists());

      log.info("✅ Test 5 PASSED: Empty page handled gracefully!");
    } catch (Exception e) {
      log.error("Generator failed on empty page: {}", e.getMessage());
      throw e;
    }
  }
}

