package com.field.Pages;

import com.field.Driver.Driver;
import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import com.field.Reports.ExtentLogger;
import com.field.Utils.ScreenshotUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static com.field.Utils.DecodeUtils.getDecodedString;


/**
 * This class represents the Redipae page and provides methods for interacting
 * with elements on the Redipae web login page and performing login actions.
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class RedipaePage extends BasePage {

    private static final Logger logger = Logger.getLogger(RedipaePage.class);


    // Locator for the username textbox
    private final By txtboxusername = By.id("username");

    // Locator for the password textbox
    private final By txtboxpassword = By.id("password");

    // Locator for the client list dropdown
    private final By clientlist = By.id("ClientList");

    // Locator for the login button
    private final By btnlogin = By.xpath("//*[text()='Login']");

    // Locator for the 'User Master' element
    private final By usermaster = By.xpath("//*[text()='User Master']");

    // Locator for the 'Add User' link
    private final By adduser = By.xpath("//*[@href='https://Redipae.in//User/UploadUser']");

    // Locator for the upload button
    private final By btnupload = By.id("btUpload");

    /**
     * Sets the username in the username textbox.
     *
     * @param username the username to be entered
     * @return the current instance of RedipaePage for method chaining
     */
    private RedipaePage setUsername(String username) {
        logger.info("Setting username: " + username);
        enter(txtboxusername, username, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the password in the password textbox.
     *
     * @param password the password to be entered
     * @return the current instance of RedipaePage for method chaining
     */
    private RedipaePage setPassword(String password) {
        logger.info("Setting password.");
        enter(txtboxpassword, getDecodedString(password), WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Selects a client from the client list dropdown.
     *
     * @param redipaeclient the client to be selected from the dropdown
     * @return the current instance of RedipaePage for method chaining
     */
    private RedipaePage setclient(String redipaeclient) {
        logger.info("Selecting client from dropdown: " + redipaeclient);
        selectDropdown(clientlist, "Visibletext", redipaeclient);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return the current instance of RedipaePage for method chaining
     */
    private RedipaePage clickLogin() {
        logger.info("Clicking the login button.");
        click(btnlogin, WaitStrategy.CLICKABLE);
        return this;
    }

    /**
     * Performs login into the application by setting the username, password,
     * and client, and clicking the login button.
     *
     * @param username the username to be entered
     * @param password the password to be entered
     * @param client   the client to be selected
     * @return the current instance of RedipaePage for method chaining
     */
    public RedipaePage loginintoApplication(String username, String password, String client) {
        logger.info("Logging into application with username: " + username + ", client: " + client);
        setUsername(username).setPassword(password).setclient(client).clickLogin();
        return this;
    }

    /**
     * Verifies if the homepage is displayed after login and performs additional checks
     * for the presence of specific elements.
     *
     * @return true if the homepage is displayed and the elements are present, false otherwise
     */
    public boolean verifyHomepageisDisplayed() {
        // Check if the 'User Master' element is displayed
        logger.info("Verifying if the homepage is displayed.");
        if (isDisplayed(usermaster)) {
            logger.info("Homepage is displayed. Navigating to Add User page.");
            navigateToAddUser();
            logger.info("Waiting for the upload button to be present.");
            // Wait for the upload button to be present
            ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, btnupload);
            try {
                // Additional pause to ensure actions have taken effect
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            logger.info("Redipae Web logged in successfully.");
            // Log success message if homepage is displayed
            ExtentLogger.pass("Redipae Web Logged in Successfully", true);
            return true;
        } else {
            // Log failure message if homepage is not displayed
            logger.warn("Redipae Web failed to login. Homepage not displayed.");
            ExtentLogger.fail("Redipae Web Failed to login", true);
            return false;
        }
    }

    /**
     * Navigates to the 'Add User' page by clicking the relevant elements.
     */
    private void navigateToAddUser() {
        logger.info("Navigating to Add User page.");
        click(usermaster, WaitStrategy.CLICKABLE);
        click(adduser, WaitStrategy.CLICKABLE);
    }
}



