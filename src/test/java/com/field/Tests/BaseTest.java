package com.field.Tests;

import com.field.DataProviderUtils.Dataprovider;
import com.field.Driver.Driver;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.awt.*;
import java.util.Map;

/**
 * The {@code BaseTest} class provides setup and teardown methods for test classes.
 * <p>This class initializes the WebDriver before each test method and quits the WebDriver after each test method.</p>
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class);
    /**
     * Constructs a {@code BaseTest} instance.
     * <p>This constructor is protected to restrict instantiation to subclasses.</p>
     */
    protected BaseTest() {}

    /**
     * Initializes the WebDriver based on the browser specified in the test data before each test method.
     * <p>This method extracts the browser type from the test data and initializes the WebDriver accordingly.</p>
     *
     * @param data an array of objects containing test data, where the first element is expected to be a {@code Map} of test data.
     */
    @SuppressWarnings("unchecked")
    @BeforeMethod
    protected void setup(Object[] data) {
        logger.info("Starting setup for test method.");

        Map<String, String> map = (Map<String, String>) data[0];
        logger.debug("Test data for setup: Browser - " + map.get("Browser") + ", Version - " + map.get("Version") + ", URL - " + map.get("URL"));

        Driver.initDriver(map.get("Browser"), map.get("Version"), map.get("URL"));
        logger.info("Driver initialized successfully.");
    }

    /**
     * Quits the WebDriver after each test method.
     * <p>This method ensures that the WebDriver session is closed and cleaned up after each test method execution.</p>
     */
    @AfterMethod
    protected void teardown() throws InterruptedException, AWTException {
        logger.info("Starting teardown for test method.");

        Driver.quitDriver();
        logger.info("Driver quit successfully.");
    }




}