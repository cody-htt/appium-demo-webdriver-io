package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import models.pages.BasePage;

public class LoginFormComponent extends BasePage {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputFieldElem;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordFieldElem;
    @AndroidFindBy(accessibility = "button-LOGIN")
    private MobileElement loginBtnElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-LOGIN']/preceding-sibling::android.widget.TextView[1]")
    private MobileElement invalidEmailMessageElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-LOGIN']/preceding-sibling::android.widget.TextView[2]")
    private MobileElement invalidPasswordMessageElem;

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement loginBtnElem() {
        waitForVisibility(loginBtnElem);
        return loginBtnElem;
    }

    public MobileElement invalidEmailMessageElem() {
        waitForVisibility(invalidEmailMessageElem);
        return invalidEmailMessageElem;
    }

    public MobileElement invalidPasswordMessageElem() {
        waitForVisibility(invalidPasswordMessageElem);
        return invalidPasswordMessageElem;
    }

    @Step("Input email as {email}")
    public LoginFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputFieldElem);
        sendKeysToElement(emailInputFieldElem, email);
        return this;
    }

    @Step("Input password as {password}")
    public LoginFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordFieldElem);
        sendKeysToElement(passwordFieldElem, password);
        return this;
    }

    @Step("Click on Login button")
    public DialogComponent clickOnLoginBtn() {
        clickElement(loginBtnElem);
        return new DialogComponent(this.appiumDriver);
    }

}
