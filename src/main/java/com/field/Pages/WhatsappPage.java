package com.field.Pages;

import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static com.field.Constants.Constants.getExtentReportfilePath;
import static com.field.Driver.Driver.config;

/**
 * This class represents the WhatsApp page and provides methods to interact with
 * elements on the WhatsApp web interface for sending files.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class WhatsappPage extends BasePage {

    private static final Logger log = Logger.getLogger(WhatsappPage.class);

    // Locator for the side panel containing chat elements
    private final By sidepanel = By.id("pane-side");

    // Locator for the search textbox used to find a chat or group
    private final By txtboxsearch = By.xpath("//*[@id='side']/div[1]/div/div[2]/div[2]/div/div[1]/p");

    // Locators for different chat groups
    private final By codegroup = By.xpath("//*[@title='Code Fieldlytics']");
    private final By devgroup = By.xpath("//*[@title='DevTeam-Fieldlytics(PPMS)']");
    private final By you = By.xpath("//*[@title='+91 63834 21413']");

    // Locator for the attach button
    private final By btnattach = By.xpath("//*[@title='Attach']");
    private final By addacaption = By.xpath("//*[@id='app']/div/div[2]/div[2]/div[2]/span/div/div/div/div[2]/div/div[1]/div[3]/div/div/div[1]/div[1]");

    // Locator for the document option in the attachment menu
    private final By documents = By.xpath("//*[text()='Document']");

    // Locator for the send button
    private final By btnsend = By.xpath("//*[@data-icon='send']");

    /**
     * Clicks on the appropriate chat based on the configuration.
     * - For 'Dev' group, clicks on the chat with title 'You'.
     * - For other groups, clicks on the 'Code Fieldlytics' chat.
     *
     * @return the current instance of WhatsappPage for method chaining
     */
    public WhatsappPage clickchat() {
        log.info("Clicking on chat based on configuration.");
        // Wait for the side panel to be visible
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, sidepanel);
        log.info("Side panel is visible.");

        // Get the group name from configuration and trim it for comparison
        String groupName = config.whatsappGroup().trim().toLowerCase();
        log.info("Group name from configuration: " + groupName);

        if (groupName.equals("dev")) {
            // Search and click on 'You' chat for the 'Dev' group
            log.info("Searching for 'DevTeam-Fieldlytics(PPMS)' chat.");
            enter(txtboxsearch, "DevTeam-Fieldlytics(PPMS)", WaitStrategy.CLICKABLE);
            ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, devgroup);
            log.info("'You' chat found and clicking.");
            click(devgroup, WaitStrategy.CLICKABLE);


            /*enter(txtboxsearch, "you", WaitStrategy.CLICKABLE);
            ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, you);
            log.info("'You' chat found and clicking.");
            click(you, WaitStrategy.CLICKABLE);*/



        } else {
            // Search and click on 'Code Fieldlytics' chat for other groups
            log.info("Searching for 'Code Fieldlytics' chat.");
            enter(txtboxsearch, "Code Fieldlytics", WaitStrategy.CLICKABLE);
            ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, codegroup);
            log.info("'Code Fieldlytics' chat found and clicking.");
            click(codegroup, WaitStrategy.CLICKABLE);
        }
        return this;
    }

    /**
     * Clicks the attach button to open the attachment menu.
     *
     * @return the current instance of WhatsappPage for method chaining
     */
    public WhatsappPage clickattach() {
        log.info("Clicking attach button.");
        click(btnattach, WaitStrategy.CLICKABLE);
        return this;
    }

    /**
     * Clicks the documents option in the attachment menu to prepare for file upload.
     *
     * @return the current instance of WhatsappPage for method chaining
     */
    public WhatsappPage clickdocuments() {
        log.info("Clicking documents option.");
        click(documents, WaitStrategy.CLICKABLE);
        return this;
    }


    public WhatsappPage sendmessage() {
        log.info("Typing Message.");
        enter(addacaption, "All applications testing done. Click the Report to view status!", WaitStrategy.CLICKABLE);
        return this;
    }


    /**
     * Clicks the send button to send the file after setting the file path.
     * Pauses for 3 seconds to ensure the message is sent.
     *
     * @return the current instance of WhatsappPage for method chaining
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    public WhatsappPage clickSend() throws InterruptedException {
        log.info("Clicking send button.");
        click(btnsend, WaitStrategy.CLICKABLE);
        // Pause to ensure the file is sent
        Thread.sleep(5000);
        log.info("Send button clicked and waiting for 5 seconds to ensure file is sent.");
        return this;
    }

    /**
     * Sets the file path for the file upload dialog using the Robot class to
     * simulate the keyboard input.
     *
     * @param path the path to the file to be uploaded
     * @return the current instance of WhatsappPage for method chaining
     * @throws AWTException if an error occurs with the Robot class
     * @throws InterruptedException if the thread is interrupted during sleep
     */
    public WhatsappPage setPath(String path) throws InterruptedException, AWTException {
        try {
            log.info("Starting to set file path: " + path);
            // Copy file path to clipboard
            StringSelection filepath = new StringSelection(path);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath, null);
            log.info("File path copied to clipboard.");

            // Pause to ensure clipboard content is available
            Thread.sleep(2000);

            // Use Robot class to simulate key presses to paste file path
            Robot robot1 = new Robot();
            log.info("Simulating key presses to paste file path.");
            robot1.keyPress(KeyEvent.VK_CONTROL);
            robot1.keyPress(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_V);
            robot1.keyRelease(KeyEvent.VK_CONTROL);
            robot1.keyPress(KeyEvent.VK_ENTER);
            robot1.keyRelease(KeyEvent.VK_ENTER);
            log.info("File path set successfully.");
        } catch (AWTException e) {
            log.error("Error with Robot class during file path setting.", e);
            throw e;
        } catch (InterruptedException e) {
            log.error("Thread interrupted while setting file path.", e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error occurred while setting file path.", e);
            throw e;
        }
        return this;
    }
}


