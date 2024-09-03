package com.field.Driver;

import org.openqa.selenium.WebDriver;

/**
 * The {@code DriverManager} class provides thread-safe management of a {@code WebDriver} instance
 * using {@code ThreadLocal}. It ensures that each thread has its own instance of {@code WebDriver},
 * which is useful in concurrent testing scenarios.
 * 
 * <p>This class is marked as {@code final} to prevent inheritance and its constructor is private
 * to restrict instantiation. It uses {@code ThreadLocal} to store and manage the WebDriver instance
 * per thread.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class DriverManager {

    /**
     * Private constructor to prevent instantiation.
     */
    private DriverManager() {}

    /**
     * A {@code ThreadLocal} instance to hold a {@code WebDriver} instance for each thread.
     */
    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    /**
     * Retrieves the {@code WebDriver} instance for the current thread.
     * 
     * @return the {@code WebDriver} instance associated with the current thread, or {@code null} if not set.
     */
    public static WebDriver getDriver() {
        return dr.get();
    }

    /**
     * Sets the {@code WebDriver} instance for the current thread.
     * 
     * @param driverref the {@code WebDriver} instance to be associated with the current thread.
     */
    public static void setDriver(WebDriver driverref) {
        dr.set(driverref);
    }

    /**
     * Removes the {@code WebDriver} instance associated with the current thread.
     * <p>This method is used to clean up the WebDriver instance after the test execution is completed.</p>
     */
    public static void Unload() {
        dr.remove();
    }
}

