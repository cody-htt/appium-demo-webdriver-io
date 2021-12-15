package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpFormComponent extends CredsFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final By confirmPasswordSel = MobileBy.AccessibilityId("input-repeat-password");
    private final By signUpBtnSel = MobileBy.AccessibilityId("button-SIGN UP");

    public SignUpFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    @Step("Retype password {password}")
    public SignUpFormComponent retypePassword(String password){
        appiumDriver.findElement(confirmPasswordSel).sendKeys(password);
        return this;
    }

    @Step("Click on Sign up button")
    public SignUpFormComponent clickOnSignUpBtn(){
        appiumDriver.findElement(signUpBtnSel).click();
        return this;
    }
}
