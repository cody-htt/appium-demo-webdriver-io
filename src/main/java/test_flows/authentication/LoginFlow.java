package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.components.authentication.DialogComponent;
import models.components.authentication.LoginFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test_data.authentication.LoginCreds;
import utils.TestUtils;

import java.util.HashMap;
import java.util.regex.Pattern;

public class LoginFlow {

    private final AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;
    private LoginFormComponent loginFormComp;
    private DialogComponent dialogComp;
    private final HashMap<String, String> expectedStringMap;
    private final SoftAssert softAssert;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
        this.softAssert = new SoftAssert();
    }

    public LoginFlow navigateToLoginPage() {
        if (loginPage == null) {
            initLoginPage();
            loginFormComp = loginPage.loginFormComponent();
        }
        // Init Bottom Nav Comp and Navigate to Login Page
        BottomNavBarComponent bottomNavBarComp = loginPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnLoginLabel();
        return this;
    }

    public LoginFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    @Step("Input email as {loginCreds.email} and password as {loginCreds.password}")
    public LoginFlow login(LoginCreds loginCreds) {
        if (loginPage == null) { throw new RuntimeException("Please use navigateToLoginPage() first!!!"); }
        // Fill Login form
        dialogComp = loginFormComp.inputEmailField(loginCreds.getEmail())
                                  .inputPasswordField(loginCreds.getPassword())
                                  .clickOnLoginBtn();
        return this;
    }

    @Step("Verify successfully login with valid credentials")
    public LoginFlow verifyLoginWithCorrectCreds() {
        // Verification
        String actualDialogTitle = dialogComp.dialogTitleElem().getText();
        String actualDialogMessage = dialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("success_login_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("success_login_dialog_msg");

        softAssert.assertEquals(actualDialogTitle, expectedDialogTitle);
        softAssert.assertEquals(actualDialogMessage, expectedDialogMessage);
        softAssert.assertAll();

        dialogComp.clickDialogBtn();
        return this;
    }

    @Step("Verify unsuccessfully login with valid credentials")
    public LoginFlow verifyLoginWithIncorrectCreds(LoginCreds loginCreds) {
        //Verification
        String actualErrorText;
        String expectedEmailErrMessage = expectedStringMap.get("error_login_invalid_email_msg");
        String expectedPasswordErrMessage = expectedStringMap.get("error_login_invalid_password_msg");
        if (isInvalidEmail(loginCreds.getEmail())) {
            actualErrorText = loginFormComp.invalidEmailMessageElem().getText();
            Assert.assertEquals(actualErrorText, expectedEmailErrMessage);
        }
        if (isInvalidPassword(loginCreds.getPassword())) {
            actualErrorText = loginFormComp.invalidPasswordMessageElem().getText();
            Assert.assertEquals(actualErrorText, expectedPasswordErrMessage);
        }
        return this;
    }

    private boolean isInvalidPassword(String password) {
        int minPasswordLength = 8;
        return password.length() < minPasswordLength;
    }

    private boolean isInvalidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(email).matches();
    }
}
