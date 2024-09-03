package com.field.Factories;

import com.field.Constants.Constants;
import com.field.Driver.Driver;
import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Properties.PropertyConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The {@code ExplicitWaitFactory} class provides methods for performing explicit waits on web elements
 * based on different waiting strategies.
 *
 * <p>This class uses the {@code WebDriverWait} class to implement various wait strategies, such as waiting
 * for an element to be clickable, present, visible, or to bypass waiting entirely.</p>
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class ExplicitWaitFactory {

    private static final Logger logger = Logger.getLogger(ExplicitWaitFactory.class);
    /**
     * Performs an explicit wait for a web element based on the specified {@code WaitStrategy}.
     *
     * <p>This method waits for a condition to be met before returning the {@code WebElement}. The wait strategy
     * determines the type of condition to wait for. If {@code WaitStrategy.NONE} is used, it will return the element
     * immediately without waiting.</p>
     *
     *
     *
     * @param waitstrategy the {@code WaitStrategy} that defines the condition to wait for. It can be one of the following:
     *                     {@code CLICKABLE}, {@code PRESENCE}, {@code VISIBLE}, or {@code NONE}.
     * @param by the {@code By} locator used to find the element.
     * @return the {@code WebElement} that matches the specified locator, or {@code null} if the element is not found.
     */
    public static WebElement performExplicitWait(WaitStrategy waitstrategy, By by) {

        logger.info("Performing explicit wait with strategy: " + waitstrategy + " for element located by: " + by);

        WebElement element = null;
        try {
            switch (waitstrategy) {
                case CLICKABLE:
                    logger.info("Waiting for element to be clickable.");
                    element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constants.getExplicittimeout())).until(ExpectedConditions.elementToBeClickable(by));
                    break;
                case PRESENCE:
                    logger.info("Waiting for element to be present in the DOM.");
                    element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constants.getExplicittimeout())).until(ExpectedConditions.presenceOfElementLocated(by));
                    break;
                case VISIBLE:
                    logger.info("Waiting for element to be visible.");
                    element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(Constants.getExplicittimeout())).until(ExpectedConditions.visibilityOfElementLocated(by));
                    break;
                case NONE:
                    logger.info("No explicit wait applied, locating element immediately.");
                    element = DriverManager.getDriver().findElement(by);
                    break;
                default:
                    logger.warn("Invalid wait strategy: " + waitstrategy);
                    throw new IllegalArgumentException("Invalid wait strategy: " + waitstrategy);
            }

        } catch (Exception e) {
            logger.error("Error during explicit wait for element located by: " + by, e);
        }
        return element;
    }
}
