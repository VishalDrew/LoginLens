package com.field.Pages;

import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import com.field.Reports.ExtentLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.field.Utils.DecodeUtils.getDecodedString;

/**
 * This class represents the Payroll page and provides methods for interacting
 * with elements on the Payroll web login page and performing login actions.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class PayrollPage extends BasePage {

    private static final Logger logger = Logger.getLogger(PayrollPage.class);

    // Locator for the username textbox
    private final By txtboxusername = By.id("txtUsername");

    // Locator for the password textbox
    private final By txtboxpassword = By.id("txtPassword");

    // Locator for the company/client textbox
    private final By txtboxcompany = By.id("txtClient");

    // Locator for the login button
    private final By btnlogin = By.id("btnLogin");

    // Locator for the 'Salary Reports' element on the homepage
    private final By reports = By.xpath("//*[text()='Salary Reports']");

    // Locator for the 'PaySlip' element on the homepage
    private final By payslip = By.xpath("//*[text()='PaySlip']");

    /**
     * Sets the username in the username textbox.
     *
     * @param username the username to be entered
     * @return the current instance of PayrollPage for method chaining
     */
    private PayrollPage setUsername(String username) {
        logger.info("Setting username: " + username);
        enter(txtboxusername, username, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the password in the password textbox.
     *
     * @param password the password to be entered
     * @return the current instance of PayrollPage for method chaining
     */
    private PayrollPage setPassword(String password) {
        logger.info("Setting password.");
        enter(txtboxpassword, getDecodedString(password), WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Sets the company/client in the company textbox.
     *
     * @param client the client or company name to be entered
     * @return the current instance of PayrollPage for method chaining
     */
    private PayrollPage setCompany(String client) {
        logger.info("Setting company : " + client);
        enter(txtboxcompany, client, WaitStrategy.PRESENCE);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     *
     * @return the current instance of PayrollPage for method chaining
     */
    private PayrollPage clickLogin() {
        logger.info("Clicking the login button.");
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
     * @return the current instance of PayrollPage for method chaining
     */
    public PayrollPage loginintoApplication(String username, String password, String client) {
        logger.info("Logging into application with username: " + username + ", client: " + client);
        setUsername(username).setPassword(password).setCompany(client).clickLogin();
        return this;
    }

    /**
     * Verifies if the homepage is displayed after login and interacts with
     * the 'Salary Reports' and 'PaySlip' elements.
     *
     * @return true if the homepage is displayed and elements are clicked, false otherwise
     */
    public boolean verifyHomepageisDisplayed() {

        logger.info("Verifying if the homepage is displayed.");


        // Wait for the 'Salary Reports' element to be present
        logger.info("Waiting for 'Salary Reports' element to be present.");
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, reports);

        // Check if the 'Salary Reports' element is displayed
        if (isDisplayed(reports)) {
            logger.info("Homepage is displayed. Clicking on 'Salary Reports' and 'PaySlip'.");
            // Click on 'Salary Reports' and 'PaySlip' elements
            click(reports, WaitStrategy.CLICKABLE);
            click(payslip, WaitStrategy.CLICKABLE);
            try {
                // Pause to ensure that the actions have taken effect
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Log success message if homepage is displayed
            logger.info("Payroll Web logged in successfully.");
            ExtentLogger.pass("Payroll Web Logged in Successfully", true);
            return true;
        } else {
            // Log failure message if homepage is not displayed
            logger.error("Payroll Web failed to login. Homepage not displayed.");
            ExtentLogger.fail("Payroll Web Failed to login", true);
            return false;
        }
    }
}

