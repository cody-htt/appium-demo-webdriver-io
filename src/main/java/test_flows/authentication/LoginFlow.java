package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import test_data.authentication.LoginCreds;

public class LoginFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    public LoginFlow navigateToLoginForm() {
        if (loginPage == null) {
            initLoginPage();
        }
        // Bottom Nav Comp
        BottomNavComponent bottomNavComponent = loginPage.bottomNavComp();
        bottomNavComponent.clickOnLoginLabel();
        return this;
    }

    public LoginFlow login(LoginCreds loginCreds) {
        if (loginPage == null)
            throw new RuntimeException("Please use method navigateToLoginForm first");

        // Fill login Form
        loginPage
                .inputUsername(loginCreds.getUsername())
                .inputPassword(loginCreds.getPassword())
                .clickOnLoginBtn();
        return this;
    }

    public void verifyLoginSuccess() {
        // Verification
        String actualLoginMsg = loginPage.loginDialogComp().msgTitle();
        boolean isTitleCorrect = actualLoginMsg.equals("Success");

        String customErrMsg = "[ERR] Login msg title incorrect!";
        Assert.assertTrue(isTitleCorrect, customErrMsg);
    }

    public void verifyLoginWithIncorrectCreds() {

    }
}
