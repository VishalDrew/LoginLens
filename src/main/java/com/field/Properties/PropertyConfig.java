package com.field.Properties;

import org.aeonbits.owner.Config;

/**
 * The {@code PropertyConfig} interface defines configuration properties for the application
 * using the {@code Owner} library. It provides access to various settings related to browser
 * type, URLs, report options, screenshot settings, and test retries.
 * 
 * <p>This interface is annotated with {@code @Config.Sources} to specify the location of the
 * configuration file and includes default values for several properties.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
@Config.Sources(value = "file:.//src//test//resources//Config//Config.properties")
public interface PropertyConfig extends Config {

    /**
     * Specifies the browser type to be used for tests.
     * 
     * @return the browser type as a {@code String}. Defaults to "CHROME".
     */
    @DefaultValue("CHROME")
    String browser();

    /**
     * Specifies the URL of the application to be tested.
     * 
     * @return the application URL as a {@code String}.
     */
    String url();

    /**
     * Specifies whether to override existing reports.
     * 
     * @return "Yes" or "No" as a {@code String}. Defaults to "No".
     */
    @DefaultValue("No")
    String overRideReports();

    /**
     * Specifies whether to capture screenshots for passed steps.
     * 
     * @return "Yes" or "No" as a {@code String}. Defaults to "No".
     */
    @DefaultValue("Yes")
    String passedStepScreenshot();

    /**
     * Specifies whether to capture screenshots for failed steps.
     * 
     * @return "Yes" or "No" as a {@code String}. Defaults to "Yes".
     */
    @DefaultValue("Yes")
    String failedStepScreenshot();

    /**
     * Specifies whether to capture screenshots for skipped steps.
     * 
     * @return "Yes" or "No" as a {@code String}. Defaults to "No".
     */
    @DefaultValue("No")
    String skippedStepScreenshot();

    /**
     * Specifies whether to retry failed tests.
     * 
     * @return "Yes" or "No" as a {@code String}. Defaults to "yes".
     */
    @DefaultValue("yes")
    String retryFailedTests();

    /**
     * Specifies the number of times to retry failed tests.
     * 
     * @return the retry count as an {@code int}. Defaults to 1.
     */
    @DefaultValue("1")
    int retrycount();

    @DefaultValue("Local")
    String Runmode();

    @DefaultValue("Code")
    String whatsappGroup();

}

