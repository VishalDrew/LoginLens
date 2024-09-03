package com.field.DataProviderUtils;

import com.field.Exceptions.InvalidpathforExcelException;
import com.field.Utils.Excelutils;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The {@code Dataprovider} class provides data for test methods using TestNG's {@code @DataProvider} annotation.
 * <p>This class retrieves test data from an Excel file and filters it based on the test method name and execution flag.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class Dataprovider {

    private static final Logger logger = Logger.getLogger(Dataprovider.class);

    // Static list to store test data
    private static List<Map<String, String>> testDataList = new ArrayList<>();

    /**
     * Provides test data for a test method. Filters data based on the method name and execution flag.
     * <p>This method retrieves data from an Excel sheet and returns a filtered list of maps based on the
     * test method name and whether the test is marked for execution.</p>
     *
     * @param method the test method for which data is to be provided.
     * @return an array of objects representing the filtered test data.
     * @throws InvalidpathforExcelException if the Excel file is not found or cannot be read.
     */
    @DataProvider(parallel = false)
    public static Object[] getData(Method method) {
        logger.info("Retrieving test data for method: " + method.getName());

        List<Map<String, String>> filteredData = new ArrayList<>();
        String testName = method.getName();
        String sheetName = "LoginData";

        try {
            if (testDataList.isEmpty() || !Excelutils.getCurrentSheetName().equals(sheetName)) {
                logger.debug("Test data list is empty or sheet name has changed. Loading data from sheet: " + sheetName);
                testDataList = Excelutils.getTestDetails(sheetName);
                logger.info("Test data loaded successfully from sheet: " + sheetName);
            }

            filteredData = testDataList.stream()
                    .filter(data -> data.get("Testname").equalsIgnoreCase(testName)
                            && data.get("Execute").equalsIgnoreCase("yes"))
                    .collect(Collectors.toList());

            if (filteredData.isEmpty()) {
                logger.warn("No data found for test: " + testName + " in sheet: " + sheetName);
            } else {
                logger.info("Filtered data found for test: " + testName + " with " + filteredData.size() + " entries.");
            }

        } catch (FileNotFoundException e) {
            logger.error("The Excel file could not be found or read for the test method: " + testName, e);
            throw new InvalidpathforExcelException("The Excel file could not be found or read for the test method: " + testName, e);
        }

        return filteredData.toArray();
    }
}
