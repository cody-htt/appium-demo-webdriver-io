package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.authentication.CredsFormComponent;
import models.components.authentication.LoginDialogComponent;
import models.components.authentication.SignUpFormComponent;
import org.openqa.selenium.By;

public class LoginPage extends Page {

    private final AppiumDriver<MobileElement> appiumDriver;
    private CredsFormComponent credsFormComponent;
    private SignUpFormComponent signUpFormComponent;
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By signUpLabelSel = MobileBy.AccessibilityId("button-sign-up-container");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        credsFormComponent = new CredsFormComponent(appiumDriver);
    }

    public LoginPage inputUsername(String username){
        credsFormComponent.inputUsername(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        credsFormComponent.inputPassword(password);
        return this;
    }

    @Step("Click on Sign up label")
    public LoginPage clickOnSignUpLabel(){
        appiumDriver.findElement(signUpLabelSel).click();
        signUpFormComponent = new SignUpFormComponent(appiumDriver);
        return this;
    }

    public LoginPage signUpInputUsername(String username){
        signUpFormComponent.inputUsername(username);
        return this;
    }

    public LoginPage signUpInputPassword(String password){
        signUpFormComponent.inputPassword(password);
        return this;
    }

    public LoginPage signUpRetypePassword(String password){
        signUpFormComponent.retypePassword(password);
        return this;
    }

    public void clickOnOnSignUpBtn(){
        signUpFormComponent.clickOnSignUpBtn();
    }

    @Step("Click on login btn")
    public LoginPage clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    public LoginDialogComponent loginDialogComp() {
        return new LoginDialogComponent(appiumDriver);
    }
}
