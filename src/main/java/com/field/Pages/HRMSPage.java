package com.field.Pages;

import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import com.field.Reports.ExtentLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.field.Utils.DecodeUtils.getDecodedString;

/**
 * This class represents the HRMS page and provides methods for interacting
 * with elements on the HRMS web login page and performing login actions.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class HRMSPage extends BasePage {

    private static final Logger logger = Logger.getLogger(HRMSPage.class);

    // Locator for the username textbox
    private final By txtboxusername = By.id("txtUsername");

    // Locator for the password textbox
    private final By txtboxpassword = By.id("txtPassword");

    // Locator for the company/client textbox
    private final By txtboxcompany = By.id("txtClient");

    // Locator for the login button
    private final By btnlogin = By.id("btnLogin");

    // Locator for the 'Bandhu OB Initiate' element on the homepage
    private final By onboard = By.xpath("//*[text()='Bandhu OB Initiate']");

    // Locator for the 'Employee Onboard Process' element
    private final By empobprocess = By.xpath("//*[text()='Employee Onboard Process']");

    // Locator for the header element within an iframe
    private final By header = By.id("headerID");

    /**
     * Sets the username in the username textbox.
     *
     * @param username the username to be entered
     * @return the current instance of HRMSPage for method chaining
     */
    private HRMSPage setUsername(String username) {
        logger.info("Setting username: " + username);
        enter(txtboxusername, username, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the password in the password textbox.
     *
     * @param password the password to be entered
     * @return the current instance of HRMSPage for method chaining
     */
    private HRMSPage setPassword(String password) {
        logger.info("Setting password.");
        enter(txtboxpassword, getDecodedString(password), WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the company/client in the company textbox.
     *
     * @param hrmsclient the client or company name to be entered
     * @return the current instance of HRMSPage for method chaining
     */
    private HRMSPage setcompany(String hrmsclient) {
        logger.info("Setting company/client: " + hrmsclient);
        enter(txtboxcompany, hrmsclient, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return the current instance of HRMSPage for method chaining
     */
    private HRMSPage clickLogin() {
        logger.info("Clicking login button.");
        click(btnlogin, WaitStrategy.CLICKABLE);
        return this;
    }

    /**
     * Performs login into the application by setting the username, password,
     * and company/client, and clicking the login button.
     *
     * @param username the username to be entered
     * @param password the password to be entered
     * @param client   the client or company to be selected
     * @return the current instance of HRMSPage for method chaining
     */
    public HRMSPage loginintoApplication(String username, String password, String client) {
        logger.info("Performing login with username: " + username + " and client: " + client);
        setUsername(username).setPassword(password).setcompany(client).clickLogin();
        return this;
    }

    /**
     * Verifies if the homepage is displayed after login and performs additional checks
     * for the presence of specific elements.
     *
     * @return true if the homepage is displayed and the elements are present, false otherwise
     */
    public boolean verifyHomepageisDisplayed() {
        logger.info("Verifying homepage display.");
        // Wait for the 'Bandhu OB Initiate' element to be present
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, onboard);

        // Check if the 'Bandhu OB Initiate' element is displayed
        if (isDisplayed(onboard)) {
            logger.info("'Bandhu OB Initiate' element is displayed. Navigating to 'Employee Onboard Process'.");
            navigateToEmployeeOnboardProcess();
            waitForHeader();
            try {
                // Additional pause to ensure actions have taken effect
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during sleep.", e);
                throw new RuntimeException(e);
            }
            // Log success message if homepage is displayed
            ExtentLogger.pass("HRMS Web Logged in Successfully", true);
            logger.info("Homepage is displayed successfully.");
            return true;
        } else {
            // Log failure message if homepage is not displayed
            ExtentLogger.fail("HRMS Web Failed to login", true);
            logger.error("Homepage is not displayed.");
            return false;
        }
    }

    /**
     * Navigates to the 'Employee Onboard Process' page by clicking the relevant elements.
     */
    private void navigateToEmployeeOnboardProcess() {
        logger.info("Navigating to 'Employee Onboard Process'.");
        click(onboard, WaitStrategy.CLICKABLE);
        click(empobprocess, WaitStrategy.CLICKABLE);
    }

    /**
     * Switches to the iframe and waits for the header element to be present.
     */
    private void waitForHeader() {
        logger.info("Switching to iframe and waiting for header element.");
        DriverManager.getDriver().switchTo().frame("myIframe");
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, header);
    }
}


