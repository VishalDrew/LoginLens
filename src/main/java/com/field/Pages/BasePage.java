package com.field.Pages;

import com.field.Driver.DriverManager;
import com.field.Enums.WaitStrategy;
import com.field.Factories.ExplicitWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.field.Driver.DriverManager.getDriver;


/**
 * The {@code BasePage} class provides common methods for interacting with web elements on a page.
 * <p>It offers functionality for clicking on elements, entering text, and retrieving the page title.</p>
 * <p>This class is intended to be extended by other page classes in the framework to reuse common
 * page interaction methods.</p>
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class BasePage {

    /**
     * Clicks on a web element located by the specified {@code By} locator.
     * <p>This method uses the {@code ExplicitWaitFactory} to wait for the element based on the provided
     * {@code WaitStrategy} before performing the click action.</p>
     *
     * @param by the {@code By} locator used to find the web element.
     * @param waitstrategy the {@code WaitStrategy} to determine the type of wait to be applied before clicking.
     */
    protected void click(By by, WaitStrategy waitstrategy) {
        ExplicitWaitFactory.performExplicitWait(waitstrategy, by).click();
    }

    /**
     * Enters text into a web element located by the specified {@code By} locator.
     * <p>This method uses the {@code ExplicitWaitFactory} to wait for the element based on the provided
     * {@code WaitStrategy} before sending the specified text to the element.</p>
     *
     * @param by the {@code By} locator used to find the web element.
     * @param value the text to be entered into the web element.
     * @param waitstrategy the {@code WaitStrategy} to determine the type of wait to be applied before entering text.
     */
    protected void enter(By by, String value, WaitStrategy waitstrategy) {
        ExplicitWaitFactory.performExplicitWait(waitstrategy, by).sendKeys(value);
    }

    /**
     * Retrieves the title of the current page.
     *
     * @return the title of the current page as a {@code String}.
     */
    protected String getPageTitle() {
        return getDriver().getTitle();
    }

    protected void selectDropdown(By by,String selectstrategy,String value){

        Select select = new Select(getDriver().findElement(by));
        if(selectstrategy.equalsIgnoreCase("index")){
            select.selectByIndex(Integer.parseInt(value));
        } else if (selectstrategy.equalsIgnoreCase("value")) {
            select.selectByValue(value);
        } else if (selectstrategy.equalsIgnoreCase("VisibleText")) {
            select.selectByVisibleText(value);
        }
    }

    protected boolean isDisplayed(By by) {
        return getDriver().findElement(by).isDisplayed();
    }


    protected String getText(By by) {
        return getDriver().findElement(by).getText();
    }

    public static void webdriverWait(By by, WebDriver driver, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
