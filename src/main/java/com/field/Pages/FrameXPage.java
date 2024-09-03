package com.field.Pages;

import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import com.field.Reports.ExtentLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.field.Utils.DecodeUtils.getDecodedString;

/**
 * This class represents the FrameX page and provides methods for interacting
 * with elements on the FrameX web login page and performing login actions.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class FrameXPage extends BasePage {

    private static final Logger logger = Logger.getLogger(FrameXPage.class);

    // Locator for the username textbox
    private final By txtboxusername = By.id("txtUserName");

    // Locator for the password textbox
    private final By txtboxpassword = By.id("txtPassword");

    // Locator for the project/client textbox
    private final By txtboxproject = By.id("txt_projectname");

    // Locator for the login button
    private final By btnlogin = By.id("btnLogin");

    // Locator for the 'Role Management' element on the homepage
    private final By rolemanagement = By.xpath("//*[text()='Role Management']");

    /**
     * Sets the username in the username textbox.
     *
     * @param username the username to be entered
     * @return the current instance of FrameXPage for method chaining
     */
    private FrameXPage setUsername(String username) {
        logger.info("Setting username: " + username);
        enter(txtboxusername, username, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the password in the password textbox.
     *
     * @param password the password to be entered
     * @return the current instance of FrameXPage for method chaining
     */
    private FrameXPage setPassword(String password) {
        logger.info("Setting password.");
        enter(txtboxpassword, getDecodedString(password), WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the project/client in the project textbox.
     *
     * @param client the client or project name to be entered
     * @return the current instance of FrameXPage for method chaining
     */
    private FrameXPage setProject(String client) {
        logger.info("Setting project/client: " + client);
        enter(txtboxproject, client, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return the current instance of FrameXPage for method chaining
     */
    private FrameXPage clickLogin() {
        logger.info("Clicking login button.");
        click(btnlogin, WaitStrategy.CLICKABLE);
        return this;
    }

    /**
     * Performs login into the application by setting the username, password,
     * and project/client, and clicking the login button.
     *
     * @param username the username to be entered
     * @param password the password to be entered
     * @param client   the client or project to be selected
     * @return the current instance of FrameXPage for method chaining
     */
    public FrameXPage loginintoApplication(String username, String password, String client) {
        logger.info("Performing login with username: " + username + ", project/client: " + client);
        setUsername(username).setPassword(password).setProject(client).clickLogin();
        return this;
    }

    /**
     * Verifies if the homepage is displayed after login and checks for the presence
     * of the 'Role Management' element.
     *
     * @return true if the homepage is displayed and 'Role Management' is present, false otherwise
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    public boolean verifyHomepageisDisplayed() throws InterruptedException {
        logger.info("Verifying homepage is displayed.");
        // Wait for the 'Role Management' element to be present
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, rolemanagement);

        // Pause to ensure that the actions have taken effect
        Thread.sleep(1000);

        // Check if the 'Role Management' element is displayed
        if (isDisplayed(rolemanagement)) {
            try {
                // Additional pause to ensure page stability
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep.", e);
                throw new RuntimeException(e);
            }
            // Log success message if homepage is displayed
            ExtentLogger.pass("FrameX Web Logged in Successfully", true);
            logger.info("Homepage is displayed. FrameX Web Logged in Successfully");
            return true;
        } else {
            // Log failure message if homepage is not displayed
            ExtentLogger.fail("FrameX Web Failed to login", true);
            logger.error("Homepage is not displayed.FrameX Web Failed to login");
            return false;
        }
    }
}

