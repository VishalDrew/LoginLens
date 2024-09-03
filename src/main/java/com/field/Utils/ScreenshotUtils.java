package com.field.Utils;

import com.field.Driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * The {@code ScreenshotUtils} class provides utility methods for capturing and handling screenshots.
 * <p>This class includes methods to capture screenshots of the current state of the browser and
 * to convert image files to Base64-encoded strings.</p>
 *
 * <p>All methods in this class are static, and the class itself cannot be instantiated.</p>
 *
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class ScreenshotUtils {

    // Private constructor to prevent instantiation
    private ScreenshotUtils() {}

    /**
     * Captures a screenshot of the current browser window and returns it as a Base64-encoded string.
     *
     * <p>This method uses the {@link TakesScreenshot} interface from Selenium WebDriver to capture
     * the screenshot and then encodes it in Base64 format.</p>
     *
     * @return a Base64-encoded string representing the screenshot of the current browser window.
     */
    public static String getBase64Image() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Array of file paths for storing mobile screenshots.
     * <p>Paths are specified relative to the user directory and include screenshots for various mobile
     * applications.</p>
     */
    public static final String[] Screenshotpath = {
            // Mobile image Paths
            System.getProperty("user.dir") + "\\MobileScreenshots\\RedipaeAndroidMobile.jpg",
            System.getProperty("user.dir") + "\\MobileScreenshots\\BandhuHRMobile.jpg",
            System.getProperty("user.dir") + "\\MobileScreenshots\\BandhuMobileAttendance.jpg",
            System.getProperty("user.dir") + "\\MobileScreenshots\\FrameXFlutterMobile.jpg",
            System.getProperty("user.dir") + "\\MobileScreenshots\\FrameXAndroidMobile.jpg",
            System.getProperty("user.dir") + "\\MobileScreenshots\\VendoMobile.jpg"
    };

    /**
     * Converts an image file to a Base64-encoded string.
     *
     * <p>This method reads the content of the image file from the specified path and encodes it in
     * Base64 format. If an error occurs while reading the file, the method prints the stack trace and
     * returns {@code null}.</p>
     *
     * @param imagePath the path to the image file to be converted.
     * @return a Base64-encoded string representing the image, or {@code null} if an error occurs.
     */
    public static String convertImageToBase64(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            byte[] fileContent = Files.readAllBytes(imageFile.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
