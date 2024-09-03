package com.field.Listeners;

import com.field.Annotations.FrameworkAnnotations;
import com.field.Pages.WhatsappPage;
import com.field.Reports.ExtentLogger;
import com.field.Reports.ExtentReport;
import org.testng.*;

import java.awt.*;
import java.util.Arrays;

import static com.field.Reports.ExtentReport.extent;

/**
 * The {@code Listeners} class implements TestNG's {@code ITestListener} and {@code ISuiteListener} interfaces
 * to provide custom behavior during test execution.
 * <p>This class integrates with the ExtentReports framework to log test execution details and manage test reports.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class Listeners implements ITestListener, ISuiteListener {

    private static final String PASSED_MSG = " is Passed";
    private static final String FAILED_MSG = " is Failed";
    private static final String SKIPPED_MSG = " is Skipped";

    /**
     * Called before any test methods are invoked in the suite. Initializes the ExtentReports.
     * 
     * @param suite the {@code ISuite} instance representing the test suite.
     */
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    /**
     * Called after all test methods in the suite have been executed. Finalizes the ExtentReports and opens the report.
     * 
     * @param suite the {@code ISuite} instance representing the test suite.
     */
    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReports();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        if ("DailyTests".equals(context.getName())) {
            extent.flush();
        }
    }

    /**
     * Called before each test method is executed. Creates a new test entry in ExtentReports and adds authors and categories.
     * 
     * @param result the {@code ITestResult} instance representing the result of the test method.
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createtest(result.getMethod().getMethodName());
        FrameworkAnnotations annotations = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class);
        ExtentReport.addAuthors(annotations.author());
        ExtentReport.addCategories(annotations.category());
    }

    /**
     * Called when a test method succeeds. Logs the test success in ExtentReports.
     * 
     * @param result the {@code ITestResult} instance representing the result of the test method.
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + PASSED_MSG);
    }

    /**
     * Called when a test method fails. Logs the test failure, including failure message and stack trace, in ExtentReports.
     * 
     * @param result the {@code ITestResult} instance representing the result of the test method.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() + FAILED_MSG, true);
        ExtentLogger.fail(result.getThrowable().toString());
        ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
    }

    /**
     * Called when a test method is skipped. Logs the test skip in ExtentReports.
     * 
     * @param result the {@code ITestResult} instance representing the result of the test method.
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName() + SKIPPED_MSG);
    }
}
