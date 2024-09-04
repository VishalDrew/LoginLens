package com.field.Tests;

import com.field.Annotations.FrameworkAnnotations;
import com.field.Constants.Constants;
import com.field.Enums.TestCaseCategory;
import com.field.Pages.WhatsappPage;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Map;

public class WhatsappTest extends BaseTest {

    @Test(priority = 12)
    @FrameworkAnnotations(author = {"Ezhil"}, category = {TestCaseCategory.WEB})
    public void SendFile(Map<String, String> data) throws InterruptedException, AWTException {
        new WhatsappPage().clickchat().clickattach().clickdocuments().setPath(Constants.getExtentReportfilePath()).sendmessage().clickSend();
    }
}
