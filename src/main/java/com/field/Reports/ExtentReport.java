package com.field.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.field.Enums.TestCaseCategory;
import com.field.Exceptions.FrameWorkExceptions;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static com.field.Constants.Constants.getExtentReportfilePath;


/**
 * The {@code ExtentReport} class provides functionality for generating and managing Extent Reports.
 * <p>This class includes methods to initialize reports, flush and save reports, and create test cases with
 * associated authors and categories. It ensures that reports are properly set up and can be accessed after
 * test execution.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class ExtentReport {

    // Private constructor to prevent instantiation
    private ExtentReport() {}

    // Static instance of ExtentReports
    public static ExtentReports extent;
    // Static instance of ExtentTest
    public static ExtentTest test;

    /**
     * Initializes the Extent Reports instance if it is not already initialized.
     * <p>Sets up the ExtentReports with a Spark reporter and configures its theme, document title, and report name.</p>
     */
    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(getExtentReportfilePath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Test Report");
        }
    }

    /**
     * Flushes the Extent Reports to the output file and opens the report in the default web browser.
     * <p>Ensures that the report data is saved and attempts to open the report file. If the file cannot be opened,
     * an exception is thrown.</p>
     */
    public static void flushReports() throws InterruptedException, AWTException {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.Unload();
        /*try {
            //Desktop.getDesktop().browse(new File(getExtentReportfilePath()).toURI());
        } catch (IOException e) {
            throw new FrameWorkExceptions("Extent Report you are trying to open is not found -->", e);
        }*/
    }

    /**
     * Creates a new test case with the specified name.
     * 
     * @param testcasename the name of the test case to be created.
     */
    public static void createtest(String testcasename) {
        ExtentManager.setExtentTest(extent.createTest(testcasename));
    }

    /**
     * Assigns authors to the current test case.
     * 
     * @param authors an array of author names to be assigned to the test case.
     */
    public static void addAuthors(String[] authors) {
        for (String temp : authors) {
            ExtentManager.getExtentTest().assignAuthor(temp);
        }
    }

    /**
     * Assigns categories to the current test case.
     * 
     * @param categories an array of {@code TestCaseCategory} values to be assigned to the test case.
     */
    public static void addCategories(TestCaseCategory[] categories) {
        for (TestCaseCategory category : categories) {
            ExtentManager.getExtentTest().assignCategory(category.toString());
        }
    }

}
