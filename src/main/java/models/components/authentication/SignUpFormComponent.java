package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import models.pages.BasePage;

public class SignUpFormComponent extends BasePage {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputFieldElem;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordFieldElem;
    @AndroidFindBy(accessibility = "input-repeat-password")
    private MobileElement repeatPasswordFieldElem;
    @AndroidFindBy(accessibility = "button-SIGN UP")
    private MobileElement signUpBtnElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-SIGN UP']/preceding-sibling::android.widget.TextView[1]")
    private MobileElement invalidEmailMessageElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-SIGN UP']/preceding-sibling::android.widget.TextView[2]")
    private MobileElement invalidPasswordMessageElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-SIGN UP']/preceding-sibling::android.widget.TextView[3]")
    private MobileElement invalidRepeatPwMessageElem;

    public SignUpFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement signUpBtnElem() {
        waitForVisibility(signUpBtnElem);
        return signUpBtnElem;
    }

    public MobileElement invalidEmailMessageElem() {
        waitForVisibility(invalidEmailMessageElem);
        return invalidEmailMessageElem;
    }

    public MobileElement invalidPasswordMessageElem() {
        waitForVisibility(invalidPasswordMessageElem);
        return invalidPasswordMessageElem;
    }

    public MobileElement invalidRepeatPwMessageElem() {
        waitForVisibility(invalidRepeatPwMessageElem);
        return invalidRepeatPwMessageElem;
    }

    @Step("Input email as {email}")
    public SignUpFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputFieldElem);
        sendKeysToElement(emailInputFieldElem, email);
        return this;
    }

    @Step("Input password as {password}")
    public SignUpFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordFieldElem);
        sendKeysToElement(passwordFieldElem, password);
        return this;
    }

    @Step("Input repeat password as {password}")
    public SignUpFormComponent inputRepeatPasswordField(String password) {
        clearElementInputField(repeatPasswordFieldElem);
        sendKeysToElement(repeatPasswordFieldElem, password);
        return this;
    }

    @Step("Click on Sign Up button")
    public DialogComponent clickOnSignUpBtn() {
        clickElement(signUpBtnElem);
        return new DialogComponent(this.appiumDriver);
    }

}
