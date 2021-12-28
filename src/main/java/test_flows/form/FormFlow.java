package test_flows.form;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.pages.FormPage;
import models.pages.HomePage;
import org.testng.Assert;
import test_flows.home.HomeFlow;
import utils.SwipeUtils;
import utils.TestUtils;

import java.util.HashMap;

public class FormFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final HashMap<String, String> expectedStringMap;
    private FormPage formPage;

    public FormFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
    }

    public FormFlow navigateToHomePage() {
        if (formPage == null) {
            initFormPage();
        }
        // Init Bottom Nav Comp and Navigate to Login Page
        BottomNavBarComponent bottomNavBarComp = formPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnFormsLabel();
        return this;
    }

    public FormFlow initFormPage() {
        formPage = new FormPage(appiumDriver);
        return this;
    }

    @Step("Enter some text in input field")
    public FormFlow inputText(String text) {
        formPage.inputField(text);
        return this;
    }

    @Step("Verify Input Text Is Displayed Correctly")
    public FormFlow verifyInputTextDisplayCorrectly() {
        String actualDisplayedText = formPage.inputTextResultElem().getText();
        String expectedInputText = formPage.inputFieldElem().getText();
        Assert.assertEquals(actualDisplayedText, expectedInputText);
        return this;
    }

    @Step("Verify Default Switch Button Is On")
    public FormFlow verifyDefaultSwitchBtnIsOn() {
        return this;
    }


}
