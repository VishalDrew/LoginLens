package com.field.Listeners;

import com.field.Driver.Driver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * The {@code Retryfailedtests} class implements the {@code IRetryAnalyzer} interface to provide retry logic for
 * failed test cases.
 * <p>This class allows a test to be retried a specified number of times if it fails, based on configuration settings.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public class Retryfailedtests implements IRetryAnalyzer {

    private int count = 0;
    private int retries = Driver.config.retrycount();

    /**
     * Determines whether a failed test should be retried based on the configured retry count.
     * <p>If the retry configuration in the {@code Driver.config.retryFailedTests()} is set to "Yes", the method checks
     * if the current retry count is less than the maximum number of retries specified. If so, it allows the test to be retried.</p>
     * 
     * @param result the {@code ITestResult} instance containing the result of the test.
     * @return {@code true} if the test should be retried, {@code false} otherwise.
     */
    @Override
    public boolean retry(ITestResult result) {
        boolean value = false;

        if (Driver.config.retryFailedTests().equalsIgnoreCase("Yes")) {
            value = count < retries;
            count++;
        }
        return value;
    }
}
