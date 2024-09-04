package com.field.Factories;

import com.field.Driver.Driver;
import com.field.Pages.WhatsappPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


/*
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */

public final class DriverFactory {

    // Private constructor to prevent instantiation of the class
    private DriverFactory() {
    }

    private static final Logger logger = Logger.getLogger(WhatsappPage.class);

    /**
     * Returns a WebDriver instance based on the specified browser name.
     *
     * @param browsername The name of the browser to launch (e.g., "Chrome", "Firefox", "Edge").
     * @param version     The version of the browser to use (currently not used in the method).
     * @return A WebDriver instance corresponding to the specified browser.
     * @throws MalformedURLException If the URL for remote WebDriver is malformed (not currently used in this method).
     */
    public static WebDriver getdriver(String browsername, String version) throws MalformedURLException {
        // Retrieves the run mode from the configuration (currently not used in the method).
        String runmode = Driver.config.Runmode();
        WebDriver driver = null;

        logger.info("Initializing WebDriver for browser: " + browsername + " with version: " + version);

        // Check for Chrome browser
        if (browsername.equalsIgnoreCase("Chrome")) {
            logger.info("Setting up Chrome WebDriver.");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            // Add user data directory to Chrome options
            options.addArguments("user-data-dir=C:\\Path");
            driver = new ChromeDriver(options);  // Initialize ChromeDriver with options
            logger.info("Chrome WebDriver initialized successfully.");
        }
        // Check for Firefox browser
        else if (browsername.equalsIgnoreCase("Firefox")) {
            logger.info("Setting up Firefox WebDriver.");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();  // Initialize FirefoxDriver
            logger.info("Firefox WebDriver initialized successfully.");
        }
        // Check for Edge browser
        else if (browsername.equalsIgnoreCase("Edge")) {
            logger.info("Setting up Edge WebDriver.");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();  // Initialize EdgeDriver
            logger.info("Edge WebDriver initialized successfully.");
        } else {
            logger.warn("Browser not supported: " + browsername);
            throw new IllegalArgumentException("Browser not supported: " + browsername);
        }

        // Return the initialized WebDriver instance
        return driver;
    }

}

