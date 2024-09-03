package com.field.Constants;


import com.field.Utils.ApiUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.field.Driver.Driver.config;

/*
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */

public final class Constants {

    // Private constructor to prevent instantiation
    private Constants(){}

    // Constant for explicit wait time in seconds
    private static final int EXPLICITWAIT = 4;

    // Path to the folder where Extent Reports will be saved
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "\\extent-test-output\\";

    // Variable to store the dynamically generated Extent Report file path
    private static String extentReportfilepath = "";

    // Path to the Excel data file
    private static final String EXCELDATAPATH = System.getProperty("user.dir") + "\\src\\test\\resources\\Testdatas\\testdata.xlsx";

    // Sheet name in the Excel file used for test run management
    private static final String RUNMANAGERSHEET = "RunManager";

    /**
     * Returns the explicit wait time in seconds.
     *
     * @return The explicit wait time.
     */
    public static int getExplicittimeout() {
        return EXPLICITWAIT;
    }

    /**
     * Synchronized method to get the Extent Report file path.
     * Generates the file path lazily if not already created.
     *
     * @return The Extent Report file path.
     */
    public static synchronized String getExtentReportfilePath() {
        if (extentReportfilepath.isEmpty()) {
            extentReportfilepath = createReportPath();
        }
        return extentReportfilepath;
    }

    /**
     * Returns the Excel data file path.
     *
     * @return The path to the Excel data file.
     */
    public static String getExceldatafilepath() {
        return EXCELDATAPATH;
    }

    /**
     * Returns the sheet name in the Excel file used for test run management.
     *
     * @return The Run Manager sheet name.
     */
    public static String getRunManagersheet() {
        return RUNMANAGERSHEET;
    }

    /**
     * Helper method to create the report path dynamically based on the timestamp or override setting.
     *
     * @return The generated report path.
     */
    private static String createReportPath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        // Check if the reports should not be overridden
        if (config.overRideReports().equalsIgnoreCase("No")) {
            return EXTENTREPORTFOLDERPATH + "DailyTestReport" + timestamp + ".html";
        } else {
            return EXTENTREPORTFOLDERPATH + "DailyTestReport.html";
        }
    }

    // Redipae Android application parameters in JSON format
    public static final String redipae_params = "{\"username\":\"radha\", \"password\":\"radha\", \"version\":\"5.8\"}";

    // FrameX Flutter application query parameters
    public static final String frameXflutter_queryParams;

    // Static block to initialize FrameX Flutter query parameters with URL encoding
    static {
        try {
            frameXflutter_queryParams = "versionno=" + URLEncoder.encode("V 3.2.8", "UTF-8") + "&version=" + URLEncoder.encode("new", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and returns FrameX Mobile parameters with URL encoding.
     *
     * @return The encoded query string for FrameX Mobile parameters.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    public static String createFrameXMobileParams() throws UnsupportedEncodingException {
        return "username=" + URLEncoder.encode("nw1754", "UTF-8") +
                "&password=" + URLEncoder.encode("nw1754", "UTF-8") +
                "&imeino=" + URLEncoder.encode("a78605dbb9564b19", "UTF-8") +
                "&project=" + URLEncoder.encode("framedemo_d1", "UTF-8") +
                "&mobileno=" + URLEncoder.encode("8148729703", "UTF-8");
    }

    // BandhuHR application parameters in JSON format
    public static final String bandhuhr_params = "{\"username\": \"55378\", \"password\": \"553781999\", \"fcm_key\": \"\", \"ClientId\": \"1008\"}";

    // BandhuHR attendance parameters in JSON format, including dynamically generated date
    public static final String bandhuhr_Attendanceparams = "{\n" +
            "    \"AttendanceType\": \"In Time\",\n" +
            "    \"AttendanceDate\": \""+ ApiUtils.generateTodayDate()+"\",\n" +
            "    \"InTime\": \"9:00\",\n" +
            "    \"OutTime\": \"\",\n" +
            "    \"InputType\": \"IN\",\n" +
            "    \"Image\": \"\",\n" +
            "    \"Longitude\": \"80.2239248\",\n" +
            "    \"Latitude\": \"13.0823291\",\n" +
            "    \"GeoTag\": \"19/10,1st Main Road,Shenoy Nagar,Chennai,Tamil Nadu,India,600102\",\n" +
            "    \"ClientId\": \"1008\",\n" +
            "    \"EmployeeFrameID\": 48261\n" +
            "}";

    /**
     * Creates and returns Vendo mobile parameters with URL encoding.
     *
     * @return The encoded query string for Vendo mobile parameters.
     * @throws UnsupportedEncodingException If the encoding is not supported.
     */
    public static String createVendoMobileParams() throws UnsupportedEncodingException {
        return "UserName=" + URLEncoder.encode("8454548848", "UTF-8") +
                "&password=" + URLEncoder.encode("Test@123", "UTF-8") +
                "&Platform=" + URLEncoder.encode("I", "UTF-8") +
                "&Deviceid=" + URLEncoder.encode("", "UTF-8") +
                "&grant_type=" + URLEncoder.encode("password", "UTF-8");
    }
}


