package test.webview;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.webview.WebviewFlow;

public class WebviewTest extends BaseTest {

    @TmsLink("Webview_001")
    @Description("Page Header are Displayed")
    @Test(description = "Verify Robot Logo and Page Header are Displayed", priority = 1)
    public void verifyRobotLogoAndPageHeader() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        WebviewFlow webviewFlow = new WebviewFlow(appiumDriver);
        webviewFlow
                .navigateToWebviewPage()
                .verifyWebviewHeader();
    }

    @TmsLink("Webview_002")
    @Description("Make sure the menu text and hyperlink displayed correctly")
    @Test(description = "Verify Menu items and their hyperlink displayed correctly", priority = 2)
    public void verifyMenuItems() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        WebviewFlow webviewFlow = new WebviewFlow(appiumDriver);
        String udid = getUdid();
        webviewFlow
                .navigateToWebviewPage()
                .expandLeftNavBar()
                .verifyMenuItems(udid);
    }

}
