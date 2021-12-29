package test_flows.home;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.pages.HomePage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.TestUtils;

import java.util.HashMap;

public class HomeFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final HashMap<String, String> expectedStringMap;
    private HomePage homePage;

    public HomeFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
    }

    public HomeFlow navigateToHomePage() {
        if (homePage == null) {
            initHomePage();
        }
        // Init Bottom Nav Comp and Navigate to Home Page
        BottomNavBarComponent bottomNavBarComp = homePage.bottomNavBarComponent();
        bottomNavBarComp.clickOnHomeLabel();
        return this;
    }

    public HomeFlow initHomePage() {
        homePage = new HomePage(appiumDriver);
        return this;
    }

    @Step("Verify app description is correct")
    public void verifyAppPurposeDescription() {
        String actualPurposeDescriptionText = homePage.appDescriptionElem().getText();
        String expectedPurposeDescriptionText = expectedStringMap.get("app_purpose_description");
        Assert.assertEquals(actualPurposeDescriptionText, expectedPurposeDescriptionText);
    }

    @Step("Verify app description is correct")
    public void verifySupportChannelDisplay() {
        String actualSupportChannelText = homePage.supportChannelElem().getText();
        String expectedSupportChannelText = expectedStringMap.get("support_channel_text");
        Assert.assertEquals(actualSupportChannelText, expectedSupportChannelText);
    }

}
