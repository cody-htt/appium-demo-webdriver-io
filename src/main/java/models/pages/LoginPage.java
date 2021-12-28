package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import models.base.PageModel;
import models.components.global.BottomNavBarComponent;
import models.components.authentication.LoginFormComponent;
import models.components.authentication.SignUpFormComponent;

public class LoginPage extends PageModel {

    @AndroidFindBy(accessibility = "button-login-container")
    private MobileElement loginFormLabelElem;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private MobileElement signUpFormLabelElem;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    @Step("Select Login Form")
    public boolean selectLoginForm() {
        loginFormLabelElem.click();
        return loginFormComponent().loginBtnElem().isDisplayed();
    }

    @Step("Select Sign Up Form")
    public boolean selectSignUpForm() {
        signUpFormLabelElem.click();
        return signUpFormComponent().signUpBtnElem().isDisplayed();
    }

    public LoginFormComponent loginFormComponent() {
        return new LoginFormComponent(this.appiumDriver);
    }

    public SignUpFormComponent signUpFormComponent() {
        return new SignUpFormComponent(this.appiumDriver);
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(this.appiumDriver);
    }

}
