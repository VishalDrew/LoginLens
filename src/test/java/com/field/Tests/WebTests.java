package com.field.Tests;

import com.field.Annotations.FrameworkAnnotations;
import com.field.Enums.TestCaseCategory;
import com.field.Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class WebTests extends BaseTest {


    @Test(priority = 1)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void RedipaeWebLoginVerification(Map<String, String> data) {
        Assert.assertTrue(new RedipaePage().loginintoApplication(data.get("Username"),data.get("Password"),data.get("Client")).verifyHomepageisDisplayed(),"Redipae Web Login Failed");
    }

    @Test(priority = 2)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void HrmsWebLoginVerification(Map<String, String> data) {
        Assert.assertTrue(new HRMSPage().loginintoApplication(data.get("Username"),data.get("Password"),data.get("Client")).verifyHomepageisDisplayed(),"HRMS Web Logging Failed");
    }

    @Test(priority = 3)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void FrameXWebLoginVerification(Map<String, String> data) throws InterruptedException {
        Assert.assertTrue(new FrameXPage().loginintoApplication(data.get("Username"),data.get("Password"),data.get("Client")).verifyHomepageisDisplayed(),"FrameX Web Logging Failed");
    }

    @Test(priority = 4)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void PayrollWebLoginVerification(Map<String, String> data) throws InterruptedException {
        Assert.assertTrue(new PayrollPage().loginintoApplication(data.get("Username"),data.get("Password"),data.get("Client")).verifyHomepageisDisplayed(),"FrameX Web Logging Failed");
    }

    @Test(priority = 5)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void VendoWebLoginVerification(Map<String, String> data) {
        Assert.assertTrue(new VendoPage().loginintoApplication(data.get("Username"),data.get("Password")).verifyHomepageisDisplayed(),"Vendo Web Login Failed");
    }


}
