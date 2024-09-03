package com.field.Tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.field.Annotations.FrameworkAnnotations;
import com.field.Constants.Constants;
import com.field.Enums.TestCaseCategory;
import com.field.Pages.RedipaePage;
import com.field.Reports.ExtentLogger;
import com.field.Utils.ApiUtils;
import com.field.Utils.ScreenshotUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.field.Constants.Constants.*;
import static com.field.Utils.ScreenshotUtils.Screenshotpath;

public class MobileTests  {

    @Test(priority = 6)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void RedipaeMobileLoginVerification(Map<String, String> data) {
        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(false).setQueryParams(null).setAppParams(Constants.redipae_params).setContentType("json").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[0]);
            if (base64Image != null) {
                ExtentLogger.pass("Redipae Mobile Logged in Successfully.", base64Image);
            }
            Assert.assertTrue(true, "Redipae Mobile login succeeded.");
        }else{
            ExtentLogger.fail("Redipae Mobile Login Failed.");
            Assert.fail("Redipae Mobile Login Failed");
        }
    }

    @Test(priority = 7)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void BandhuHRMobileLoginVerification(Map<String, String> data) {

        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(false).setQueryParams(null).setAppParams(Constants.bandhuhr_params).setContentType("json").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[1]);
            if (base64Image != null) {
                ExtentLogger.pass("BandhuHR Mobile Logged in Successfully", base64Image);
            }
            Assert.assertTrue(true, "BandhuHR Mobile login succeeded.");
        }else{
            ExtentLogger.fail("BandhuHR Mobile Login Failed.");
            Assert.fail("BandhuHR Mobile Login Failed");
        }
    }

    @Test(priority = 8)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void BandhuHRAttendanceVerification(Map<String, String> data) {
        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(false).setQueryParams(null).setAppParams(Constants.bandhuhr_Attendanceparams).setContentType("json").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[2]);
            if (base64Image != null) {
                ExtentLogger.pass("Attendance Record has been added successfully..", base64Image);
            }
            Assert.assertTrue(true, "Attendance Record has been added successfully...");
        }else{
            ExtentLogger.fail("Your Attendance Record Not added successfully...");
            Assert.fail("Your Attendance Record Not added successfully...");
        }

    }

    @Test(priority = 9)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void FrameXFlutterLoginVerification(Map<String, String> data) throws UnsupportedEncodingException {

        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(true).setQueryParams(frameXflutter_queryParams).setAppParams(Constants.createFrameXMobileParams()).setContentType("x-www-form-urlencoded").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[3]);
            if (base64Image != null) {
                ExtentLogger.pass("FrameX flutter Mobile Logged in Successfully",base64Image);
            }
            Assert.assertTrue(true, "FrameX flutter Mobile Logged in Successfully");
        }else{
            ExtentLogger.fail("FrameX flutter Mobile Login Failed..");
            Assert.fail("FrameX flutter Mobile Login Failed.");
        }

    }

    @Test(priority = 10)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void FrameXAndroidLoginVerification(Map<String, String> data) throws UnsupportedEncodingException {

        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(false).setQueryParams(null).setAppParams(Constants.createFrameXMobileParams()).setContentType("x-www-form-urlencoded").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[4]);
            if (base64Image != null) {
                ExtentLogger.pass("FrameX Android Mobile Logged in Successfully", base64Image);
            }
            Assert.assertTrue(true, "FrameX Android Mobile Logged in Successfully");
        }else{
            ExtentLogger.fail("FrameX Android Mobile Login Failed..");
            Assert.fail("FrameX Android Mobile Login Failed.");
        }

    }

    @Test(priority = 11)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.MOBILE})
    public void VendoMobileLoginVerification(Map<String, String> data) throws UnsupportedEncodingException {
        String vendo_params = createVendoMobileParams();
        boolean loginSuccess = ApiUtils.getInstance().setAppUrl(data.get("URL")).setQueryParamsStatus(false).setQueryParams(null).setAppParams(vendo_params).setContentType("x-www-form-urlencoded").apiLogin();
        if(loginSuccess){
            String base64Image = ScreenshotUtils.convertImageToBase64(Screenshotpath[5]);
            if (base64Image != null) {
                ExtentLogger.pass("Vendo Mobile Logged in Successfully",base64Image);
            }
            Assert.assertTrue(true, "Vendo Mobile Logged in Successfully");
        }else{
            ExtentLogger.fail(" Vendo Mobile Login Failed..");
            Assert.fail("Vendo Mobile Login Failed.");
        }
    }

}
