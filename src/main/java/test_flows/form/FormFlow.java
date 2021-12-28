package test_flows.form;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.pages.FormPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.TestUtils;

import java.util.HashMap;

public class FormFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final HashMap<String, String> expectedStringMap;
    private FormPage formPage;
    private SoftAssert softAssert;
    private TestUtils testUtils;

    public FormFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
        this.softAssert = new SoftAssert();
        this.testUtils = new TestUtils();
    }

    public FormFlow navigateToFormsPage() {
        if (formPage == null) {
            initFormsPage();
        }
        // Init Bottom Nav Comp and Navigate to Login Page
        BottomNavBarComponent bottomNavBarComp = formPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnFormsLabel();
        return this;
    }

    public FormFlow initFormsPage() {
        formPage = new FormPage(appiumDriver);
        return this;
    }

    @Step("Enter some text in input field")
    public FormFlow inputRandomText() {
        formPage.inputField(testUtils.randomStringGenerator());
        return this;
    }

    @Step("Verify Input Text Is Displayed Correctly")
    public FormFlow verifyInputTextDisplayCorrectly() {
        String actualDisplayedText = formPage.inputTextResultElem().getText();
        String expectedInputText = formPage.inputFieldElem().getText();
        Assert.assertEquals(actualDisplayedText, expectedInputText);
        return this;
    }

    @Step("Switching Button To On/Off")
    public FormFlow tapOnSwitchButton(String state) {
        if (state.equalsIgnoreCase("ON")) {
            formPage.clickOnSwitchBtn();
        }
        if (state.equalsIgnoreCase("OFF")) {
            formPage.clickOnSwitchBtn();
        }
        return this;
    }

    @Step("Verify Switch Button Is In Correct State")
    public FormFlow verifySwitchBtnState(String state) {
        String actualSwitchMsg = formPage.switchTextElem().getText();
        if (state.equalsIgnoreCase("ON")) {
            boolean isOn = formPage.switchBtnElem().getText().equalsIgnoreCase("ON");
            Assert.assertTrue(isOn);
        } else if (state.equalsIgnoreCase("OFF")) {
            boolean isOff = formPage.switchBtnElem().getText().equalsIgnoreCase("OFF");
            Assert.assertTrue(isOff);
        }
        return this;
    }

    @Step("Verify Text Is Displayed Properly With Switch Button State ON/OFF")
    public FormFlow verifyTextDisplayedAsState(String state) {
        String actualSwitchMsg = formPage.switchTextElem().getText();
        String expectedSwitchMsg;
        if (state.equalsIgnoreCase("ON")) {
            expectedSwitchMsg = expectedStringMap.get("switch_off_msg");
            Assert.assertEquals(actualSwitchMsg, expectedSwitchMsg);
        } else if (state.equalsIgnoreCase("OFF")) {
            expectedSwitchMsg = expectedStringMap.get("switch_on_msg");
            Assert.assertEquals(actualSwitchMsg, expectedSwitchMsg);
        }
        return this;
    }
}
