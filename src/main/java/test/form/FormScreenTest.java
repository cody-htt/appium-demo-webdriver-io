package test.form;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.form.FormsFlow;

public class FormScreenTest extends BaseTest {

    @TmsLink("Form_001")
    @Description("what user input can be displayed")
    @Test(description = "Verify input text from user can be displayed")
    public void verifyInputTextCanBeDisplayed() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        FormsFlow formsFlow = new FormsFlow(appiumDriver);
        formsFlow
                .navigateToFormsPage()
                .inputRandomText()
                .verifyInputTextDisplayCorrectly();
    }

    @TmsLink("Form_002")
    @TmsLink("Form_003")
    @Description("user can switch on/off and text displayed")
    @Test(dataProvider = "switchBtnState", description = "Verify user can tap on switch on/off button and text is displayed")
    public void verifyClickingOnSwitchButton(String state) {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        FormsFlow formsFlow = new FormsFlow(appiumDriver);
        formsFlow
                .navigateToFormsPage()
                .tapOnSwitchButton(state)
                .verifySwitchBtnState(state)
                .verifyTextDisplayedAsState(state);
    }

    @TmsLink("Form_004")
    @Description("user can select dropdown webdriverio/appium/this app is awesome")
    @Test(dataProvider = "dropdownItems", description = "Verify user is able to select item from dropdown")
    public void verifyItemInDropdownList(int indexOfItem, String itemValue) {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        FormsFlow formsFlow = new FormsFlow(appiumDriver);
        formsFlow
                .navigateToFormsPage()
                .tapOnDropdownIcon()
                .selectItemFromDropdown(indexOfItem)
                .verifySelectedItemIsDisplayed(itemValue);
    }

    @TmsLink("Form_005")
    @Description("Active/Inactive button works properly")
    @Test(description = "Verify Active Button is Clickable and Texts are displayed correctly")
    public void verifyActiveButtonIsWorking() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        FormsFlow formsFlow = new FormsFlow(appiumDriver);
        formsFlow
                .navigateToFormsPage()
                .tapOnActiveButton()
                .verifyActiveDialogIsDisplayed();
    }

    @DataProvider
    public Object[][] switchBtnState() {
        return new Object[][] { { "ON" },
                                { "OFF" } };
    }

    @DataProvider
    public Object[][] dropdownItems() {
        return new Object[][] {
                new Object[] { 1, "webdriver.io is awesome" },
                new Object[] { 2, "Appium is awesome" },
                new Object[] { 3, "This app is awesome" }
        };
    }
}
