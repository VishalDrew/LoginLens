package com.field.Utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for performing API operations using method chaining.
 * This class allows configuring and sending HTTP POST requests with different parameters
 * and handles logging for the API operations.
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class ApiUtils {

    private static final Logger logger = Logger.getLogger(ApiUtils.class);

    private String appUrl;
    private boolean queryParamsStatus;
    private String queryParams;
    private String appParams;
    private String contentType;

    // Private constructor to prevent instantiation
    private ApiUtils() {
        logger.debug("ApiUtils instance created.");
    }

    /**
     * Static method to get an instance of {@code ApiUtils} for method chaining.
     *
     * @return a new instance of {@code ApiUtils}
     */
    public static ApiUtils getInstance() {
        logger.info("Getting new instance of ApiUtils.");
        return new ApiUtils();
    }

    /**
     * Sets the URL for the API request.
     *
     * @param appUrl the URL to send the request to
     * @return the current instance of {@code ApiUtils} for method chaining
     */
    public ApiUtils setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        logger.info("App URL set to: " + appUrl);
        return this;
    }

    /**
     * Sets the status of query parameters inclusion.
     *
     * @param queryParamsStatus {@code true} if query parameters are included in the URL, {@code false} otherwise
     * @return the current instance of {@code ApiUtils} for method chaining
     */
    public ApiUtils setQueryParamsStatus(boolean queryParamsStatus) {
        this.queryParamsStatus = queryParamsStatus;
        logger.info("Query parameters status set to: " + queryParamsStatus);
        return this;
    }

    /**
     * Sets the query parameters to be appended to the URL.
     *
     * @param queryParams the query parameters to be included in the URL
     * @return the current instance of {@code ApiUtils} for method chaining
     */
    public ApiUtils setQueryParams(String queryParams) {
        this.queryParams = queryParams;
        logger.info("Query parameters set to: " + queryParams);
        return this;
    }

    /**
     * Sets the parameters to be sent in the body of the POST request.
     *
     * @param appParams the parameters to be sent in the request body
     * @return the current instance of {@code ApiUtils} for method chaining
     */
    public ApiUtils setAppParams(String appParams) {
        this.appParams = appParams;
        logger.info("App parameters set to: " + appParams);
        return this;
    }

    /**
     * Sets the content type for the request.
     *
     * @param contentType the content type of the request (e.g., "json", "x-www-form-urlencoded")
     * @return the current instance of {@code ApiUtils} for method chaining
     */
    public ApiUtils setContentType(String contentType) {
        this.contentType = contentType;
        logger.info("Content type set to: " + contentType);
        return this;
    }

    /**
     * Performs a POST request to the specified URL with the configured parameters.
     *
     * @return {@code true} if the request was successful and the response was not empty, {@code false} otherwise
     */
    public boolean apiLogin() {
        HttpURLConnection con = null;
        BufferedReader in = null;

        try {
            logger.info("Starting API login process.");
            URL url = new URL(queryParamsStatus ? appUrl + "?" + queryParams : appUrl);
            logger.debug("URL formed: " + url.toString());
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            logger.info("HTTP method set to POST.");

            // Set content type headers based on the provided contentType
            switch (contentType.toLowerCase()) {
                case "json":
                    con.setRequestProperty("Content-Type", "application/json; utf-8");
                    con.setRequestProperty("Accept", "application/json");
                    logger.info("Content-Type and Accept headers set for JSON.");
                    break;
                case "x-www-form-urlencoded":
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    logger.info("Content-Type header set for x-www-form-urlencoded.");
                    break;
                default:
                    logger.warn("Content type not recognized: " + contentType);
                    return false;
            }

            con.setDoOutput(true);
            logger.debug("Connection output stream enabled.");

            // Write parameters to the request body
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = appParams.getBytes(StandardCharsets.UTF_8);
                os.write(input);
                logger.info("Request body written with parameters: " + appParams);
            }

            int responseCode = con.getResponseCode();
            logger.info("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read and log the response
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = reader.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String responseString = response.toString();
                    logger.info("API response received: " + responseString);

                    if (!responseString.isEmpty()) {
                        logger.info("Mobile application logged in successfully!");
                        return true;
                    } else {
                        logger.error("Login failed, response is null or empty.");
                        return false;
                    }
                }
            } else {
                logger.error("POST request not worked, response code: " + responseCode);
                return false;
            }
        } catch (Exception e) {
            logger.error("Exception occurred during API login: ", e);
            return false;
        } finally {
            if (con != null) {
                con.disconnect();
                logger.info("HTTP connection closed.");
            }
        }
    }

    /**
     * Generates the current date in "yyyy-MM-dd" format.
     *
     * @return the formatted current date as a {@code String}
     */
    public static String generateTodayDate() {
        logger.info("Generating today's date in yyyy-MM-dd format.");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        logger.info("Generated date: " + formattedDate);
        return formattedDate;
    }
}

