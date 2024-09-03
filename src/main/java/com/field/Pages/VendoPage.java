package com.field.Pages;

import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import com.field.Reports.ExtentLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.field.Utils.DecodeUtils.getDecodedString;

/**
 * This class represents the Vendo page and provides methods for interacting
 * with elements on the Vendo web login page and performing login actions.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class VendoPage extends BasePage {

    private static final Logger logger = Logger.getLogger(VendoPage.class);

    // Locator for the username textbox
    private final By txtboxusername = By.xpath("//*[@name='username']");

    // Locator for the password textbox
    private final By txtboxpassword = By.xpath("//*[@name='password']");

    // Locator for the login button
    private final By btnlogin = By.xpath("//*[@type='submit']");

    // Locator for the 'Look up' text or button on the homepage
    private final By lookUp = By.xpath("//*[text()='Look up ']");

    /**
     * Sets the username in the username textbox.
     *
     * @param username the username to be entered
     * @return the current instance of VendoPage for method chaining
     */
    private VendoPage setUsername(String username) {
        logger.info("Setting username: " + username);
        enter(txtboxusername, username, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the password in the password textbox.
     *
     * @param password the password to be entered
     * @return the current instance of VendoPage for method chaining
     */
    private VendoPage setPassword(String password) {
        logger.info("Setting password.");
        enter(txtboxpassword, getDecodedString(password), WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return the current instance of VendoPage for method chaining
     */
    private VendoPage clickLogin() {
        logger.info("Clicking login button.");
        click(btnlogin, WaitStrategy.CLICKABLE);
        return this;
    }

    /**
     * Performs login into the application by setting the username and password
     * and clicking the login button.
     *
     * @param username the username to be entered
     * @param password the password to be entered
     * @return the current instance of VendoPage for method chaining
     */
    public VendoPage loginintoApplication(String username, String password) {
        logger.info("Performing login with username: " + username);
        setUsername(username).setPassword(password).clickLogin();
        return this;
    }

    /**
     * Verifies if the homepage is displayed after login and clicks the 'Look up' element.
     *
     * @return true if the homepage is displayed and 'Look up' is clicked, false otherwise
     */
    public boolean verifyHomepageisDisplayed() {
        logger.info("Verifying homepage display and clicking 'Look up'.");
        // Wait for the 'Look up' element to be present
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, lookUp);

        // Check if the 'Look up' element is displayed
        if (isDisplayed(lookUp)) {
            logger.info("'Look up' element is displayed. Clicking it.");
            click(lookUp, WaitStrategy.CLICKABLE);
            try {
                // Pause to ensure that the action has taken effect
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep.", e);
                throw new RuntimeException(e);
            }
            // Log success message if homepage is displayed
            ExtentLogger.pass("Vendo Web Logged in Successfully", true);
            logger.info("Homepage is displayed successfully.");
            return true;
        } else {
            // Log failure message if homepage is not displayed
            ExtentLogger.fail("Vendo Web Failed to login", true);
            logger.error("Homepage is not displayed.");
            return false;
        }
    }
}


