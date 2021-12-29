package test.home;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.home.HomeFlow;

public class HomeScreenTest extends BaseTest {

    @TmsLink("HomePage_001")
    @Description("Make sure \"App purpose\" displayed")
    @Test(description = "Verify app purpose description is displayed")
    public void checkAppPurposeDescription() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        HomeFlow homeFlow = new HomeFlow(appiumDriver);

        homeFlow
                .initHomePage()
                .navigateToHomePage()
                .verifyAppPurposeDescription();
    }

    @TmsLink("HomePage_002")
    @Description("Make sure \"Support\" channel displayed")
    @Test(description = "Verify app purpose description is displayed")
    public void checkSupportChannelDisplayed() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        HomeFlow homeFlow = new HomeFlow(appiumDriver);

        homeFlow
                .initHomePage()
                .navigateToHomePage()
                .verifySupportChannelDisplay();
    }
}
