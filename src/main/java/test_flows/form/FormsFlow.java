package test_flows.form;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.forms_comp.ActiveBtnDialogComponent;
import models.components.forms_comp.DropdownDialogComponent;
import models.components.global.BottomNavBarComponent;
import models.pages.FormPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FormsFlow {

    private final List<String> dropDownListItem = new ArrayList<>();
    private final AppiumDriver<MobileElement> appiumDriver;
    private final HashMap<String, String> expectedStringMap;
    private FormPage formPage;
    private DropdownDialogComponent dropdownDialogComp;
    private ActiveBtnDialogComponent activeBtnDialogComp;
    private SoftAssert softAssert;
    private TestUtils testUtils;

    public FormsFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.testUtils =  new TestUtils();
        this.expectedStringMap = testUtils.getExpectedStringMap();
        this.softAssert = new SoftAssert();
    }

    public FormsFlow navigateToFormsPage() {
        if (formPage == null) {
            initFormsPage();
        }
        // Init Bottom Nav Comp and Navigate to Forms Page
        BottomNavBarComponent bottomNavBarComp = formPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnFormsLabel();
        return this;
    }

    public FormsFlow initFormsPage() {
        formPage = new FormPage(appiumDriver);
        return this;
    }

    @Step("Enter some text in input field")
    public FormsFlow inputRandomText() {
        formPage.inputField(testUtils.randomStringGenerator());
        return this;
    }

    @Step("Verify Input Text Is Displayed Correctly")
    public FormsFlow verifyInputTextDisplayCorrectly() {
        String actualDisplayedText = formPage.inputTextResultElem().getText();
        String expectedInputText = formPage.inputFieldElem().getText();
        Assert.assertEquals(actualDisplayedText, expectedInputText);
        return this;
    }

    @Step("Switching Button To On/Off")
    public FormsFlow tapOnSwitchButton(String state) {
        if (state.equalsIgnoreCase("ON")) {
            formPage.clickOnSwitchBtn();
        }
        if (state.equalsIgnoreCase("OFF")) {
            formPage.clickOnSwitchBtn();
        }
        return this;
    }

    @Step("Verify Switch Button Is In Correct State")
    public FormsFlow verifySwitchBtnState(String state) {
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
    public FormsFlow verifyTextDisplayedAsState(String state) {
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

    @Step("Tap and Select Value From Dropdown List")
    public FormsFlow tapOnDropdownIcon() {
        dropdownDialogComp = formPage.clickOnDropDownIcon();
        dropdownDialogComp.dialogListItems().forEach(item -> dropDownListItem.add(item.getText()));
        return this;
    }

    @Step("Select Item From Dropdown List")
    public FormsFlow selectItemFromDropdown(int index) {
        dropdownDialogComp.getItemFromList(index).click();
        return this;
    }

    @Step("Verify Select Item From Dropdown Is Displayed")
    public FormsFlow verifySelectedItemIsDisplayed(String expectedValue) {
        String actualItemValue = formPage.dropDownInputFieldElem().getText();
        Assert.assertEquals(actualItemValue, expectedValue);
        return this;
    }

    @Step("Tap On Active Button")
    public FormsFlow tapOnActiveButton() {
        activeBtnDialogComp = formPage.clickOnActiveBtn();
        return this;
    }

    @Step("Verify Active Dialog Display and Texts Are Correct")
    public FormsFlow verifyActiveDialogIsDisplayed() {
        String actualDialogTitle = activeBtnDialogComp.dialogTitleElem().getText();
        String actualDialogMessage = activeBtnDialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("active_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("active_dialog_msg");

        Assert.assertEquals(actualDialogTitle, expectedDialogTitle);
        Assert.assertEquals(actualDialogMessage, expectedDialogMessage);

        activeBtnDialogComp.okBtnElem().click();
        return this;
    }

}
