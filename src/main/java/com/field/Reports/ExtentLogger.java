package com.field.Reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import static com.field.Driver.Driver.config;
import static com.field.Utils.ScreenshotUtils.getBase64Image;
/**
 * The {@code ExtentLogger} class provides static methods to log messages with various statuses (pass, fail, info, skip)
 * in Extent Reports. It also includes functionality to capture screenshots based on the configuration settings.
 * 
 * <p>This class is intended for use in automated test reporting to provide detailed logs and screenshots for different
 * test outcomes.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class ExtentLogger {

    // Private constructor to prevent instantiation
    private ExtentLogger() {}

    /**
     * Logs a message with a "pass" status in the Extent Report.
     * 
     * @param message the message to be logged.
     */
    public static void pass(String message) {
        ExtentManager.getExtentTest().pass(message);
    }

    /**
     * Logs a message with a "pass" status in the Extent Report and optionally captures a screenshot.
     * 
     * @param message the message to be logged.
     * @param isScreenshotneeded indicates whether a screenshot should be captured. 
     *                            The screenshot is captured only if the configuration allows it.
     */
    public static void pass(String message, boolean isScreenshotneeded) {
        if (config.passedStepScreenshot().equalsIgnoreCase("Yes") && isScreenshotneeded) {
            ExtentManager.getExtentTest().pass(message, 
                MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }
    }

    public static void pass(String message, String base64img) {
        ExtentManager.getExtentTest().log(Status.PASS, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64img).build());
    }

    /**
     * Logs a message with a "fail" status in the Extent Report.
     * 
     * @param message the message to be logged.
     */
    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
    }

    /**
     * Logs a message with a "fail" status in the Extent Report and optionally captures a screenshot.
     * 
     * @param message the message to be logged.
     * @param isScreenshotneeded indicates whether a screenshot should be captured.
     *                            The screenshot is captured only if the configuration allows it.
     */
    public static void fail(String message, boolean isScreenshotneeded) {
        if (config.failedStepScreenshot().equalsIgnoreCase("Yes") && isScreenshotneeded) {
            ExtentManager.getExtentTest().fail(message, 
                MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
        }
    }

    /**
     * Logs a message with an "info" status in the Extent Report.
     * 
     * @param message the message to be logged.
     */
    public static void info(String message) {
        ExtentManager.getExtentTest().info(message);
    }

    /**
     * Logs a message with a "skip" status in the Extent Report.
     * 
     * @param message the message to be logged.
     */
    public static void skip(String message) {
        ExtentManager.getExtentTest().skip(message);
    }


  
}

