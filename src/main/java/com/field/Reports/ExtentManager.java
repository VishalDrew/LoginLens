package com.field.Reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * The {@code ExtentManager} class manages {@code ExtentTest} instances for reporting with Extent Reports.
 * <p>This class provides thread-safe access to the {@code ExtentTest} object using {@code ThreadLocal},
 * ensuring that each thread has its own instance of {@code ExtentTest}. It includes methods to retrieve,
 * set, and remove the {@code ExtentTest} instance.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class ExtentManager {

    // Private constructor to prevent instantiation
    private ExtentManager() {}

    // ThreadLocal instance to hold ExtentTest for each thread
    private static ThreadLocal<ExtentTest> ext = new ThreadLocal<>();

    /**
     * Retrieves the {@code ExtentTest} instance associated with the current thread.
     * 
     * @return the {@code ExtentTest} instance for the current thread.
     */
    static ExtentTest getExtentTest() {
        return ext.get();
    }

    /**
     * Sets the {@code ExtentTest} instance for the current thread.
     * 
     * @param test the {@code ExtentTest} instance to be set for the current thread.
     */
    static void setExtentTest(ExtentTest test) {
        ext.set(test);
    }

    /**
     * Removes the {@code ExtentTest} instance for the current thread.
     * <p>This method is used to clean up the {@code ExtentTest} instance after test execution.</p>
     */
    public static void Unload() {
        ext.remove();
    }
}

