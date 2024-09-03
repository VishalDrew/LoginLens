package com.field.Driver;

import com.field.Factories.DriverFactory;
import com.field.Exceptions.BrowserInvocationFailedException;
import com.field.Pages.WhatsappPage;
import com.field.Properties.PropertyConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static com.field.Driver.DriverManager.*;
import static java.util.logging.Level.SEVERE;


/**
 * The {@code Driver} class provides a singleton pattern implementation for managing a WebDriver instance.
 * It includes methods for initializing and quitting the WebDriver based on the specified browser.
 *
 * <p>This class is marked as {@code final} to prevent inheritance and its constructor is private
 * to restrict instantiation. It ensures only one WebDriver instance is created and managed.</p>
 *
 * <p>Created on: 23 Aug 2024</p>
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class Driver {

    private static final Logger logger = Logger.getLogger(Driver.class);

    private Driver() {}

    private static WebDriver driver;

    public static PropertyConfig config = ConfigFactory.create(PropertyConfig.class);

    /**
     * Initializes the WebDriver based on the specified browser name.
     * <p>If the WebDriver instance is not already created, this method will set up and instantiate
     * the WebDriver for Chrome, Firefox, or Edge based on the provided {@code browsername}. It also
     * navigates to the URL specified in the configuration and sets an implicit wait timeout.</p>
     *
     * @param browsername The name of the browser to initialize the WebDriver for. Accepted values: "Chrome", "Firefox", "Edge".
     */
    public static void initDriver(String browsername, String version, String URL) {
        PropertyConfigurator.configure("E:\\Automation Workspace\\LoginLens\\Log4j.properties");

        if (Objects.isNull(getDriver())) {
            try {
                logger.info("Initializing WebDriver for browser: " + browsername + " with version: " + version);
                setDriver(DriverFactory.getdriver(browsername, version));
                logger.info("WebDriver initialized successfully.");

                getDriver().get(URL);
                logger.info("Navigated to URL: " + URL);

                getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                logger.info("Implicit wait timeout set to 20 seconds.");

                getDriver().manage().window().maximize();
                logger.info("Browser window maximized.");
            } catch (MalformedURLException e) {
                logger.error( "Browser invocation failed, please check the capabilities.", e);
                throw new BrowserInvocationFailedException("Browser invocation Failed , Please check the Capabilities");
            }
        } else {
            logger.info("WebDriver instance already exists.");
        }
    }

    /**
     * Quits the WebDriver instance and performs any necessary cleanup.
     * <p>If the WebDriver instance is not {@code null}, this method will quit the WebDriver and call
     * the {@code Unload} method to perform additional cleanup tasks.</p>
     */
    public static void quitDriver() throws InterruptedException, AWTException {
        if (Objects.nonNull(getDriver())) {
            try {
                logger.info("Quitting WebDriver instance.");
                getDriver().quit();
                logger.info("WebDriver instance quit successfully.");
            } catch (Exception e) {
                logger.error( "Error occurred while quitting WebDriver instance.", e);
                throw e;
            } finally {
                Unload();
                logger.info("Cleanup tasks performed after quitting WebDriver.");
            }
        } else {
            logger.info("WebDriver instance is already null.");
        }
    }

    // The following methods need to be implemented:
    // - getDriver() : Returns the current WebDriver instance.
    // - setDriver(WebDriver driver) : Sets the WebDriver instance.
    // - Unload() : Performs cleanup tasks after quitting the WebDriver.
}

