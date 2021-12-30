package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.SignUpFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test_data.authentication.SignUpCreds;
import utils.TestUtils;

import java.util.HashMap;
import java.util.regex.Pattern;

public class SignUpFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;
    private SignUpFormComponent signUpFormComp;
    private DialogComponent dialogComp;
    private final HashMap<String, String> expectedStringMap;
    private final SoftAssert softAssert;

    public SignUpFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
        softAssert = new SoftAssert();
    }

    public SignUpFlow navigateToLoginPage() {
        if (loginPage == null) {
            initLoginPage();
            signUpFormComp = loginPage.signUpFormComponent();
        }
        // Init Bottom Nav Comp and Navigate to Login Page
        BottomNavBarComponent bottomNavBarComp = loginPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnLoginLabel();
        loginPage.selectSignUpForm();
        return this;
    }

    public SignUpFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    @Step("Input email={signUpCreds.email} | password={signUpCreds.password} | confirm-password={signUpCreds.repeatPassword}")
    public SignUpFlow signUp(SignUpCreds signUpCreds) {
        if (loginPage == null) { throw new RuntimeException("Please use navigateToLoginPage() first!!!"); }
        // Fill in Sign Up form
        dialogComp = signUpFormComp.inputEmailField(signUpCreds.getEmail())
                                   .inputPasswordField(signUpCreds.getPassword())
                                   .inputRepeatPasswordField(signUpCreds.getRepeatPassword())
                                   .clickOnSignUpBtn();
        return this;
    }

    @Step("Verify successfully sign up with valid credentials")
    public SignUpFlow verifyLoginWithCorrectCreds() {
        // Init Dialog Component and verification
        String actualDialogTitle = dialogComp.dialogTitleElem().getText();
        String actualDialogMessage = dialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("success_sign_up_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("success_sign_up_dialog_msg");

        softAssert.assertEquals(actualDialogTitle, expectedDialogTitle);
        softAssert.assertEquals(actualDialogMessage, expectedDialogMessage);
        softAssert.assertAll();

        dialogComp.clickDialogBtn();
        return this;
    }

    @Step("Verify unsuccessfully login with valid credentials")
    public SignUpFlow verifyLoginWithIncorrectCreds(SignUpCreds signUpCreds) {
        String actualErrorText;
        String expectedEmailErrMessage = expectedStringMap.get("error_sign_up_invalid_email_msg");
        String expectedPasswordErrMessage = expectedStringMap.get("error_sign_up_invalid_password_msg");
        String expectedRepeatPasswordErrMessage = expectedStringMap.get("error_sign_up_wrong_repeat_password_msg");

        if (isInvalidEmail(signUpCreds.getEmail())) {
            actualErrorText = signUpFormComp.invalidEmailMessageElem().getText();
            Assert.assertEquals(actualErrorText, expectedEmailErrMessage);
        }
        if (isInvalidPassword(signUpCreds.getPassword())) {
            actualErrorText = signUpFormComp.invalidPasswordMessageElem().getText();
            Assert.assertEquals(actualErrorText, expectedPasswordErrMessage);
        }
        if (isInvalidConfirmPassword(signUpCreds.getRepeatPassword(), signUpCreds.getPassword())) {
            actualErrorText = signUpFormComp.invalidRepeatPwMessageElem().getText();
            Assert.assertEquals(actualErrorText, expectedRepeatPasswordErrMessage);
        }
        return this;
    }

    private boolean isInvalidConfirmPassword(String repeatPassword, String password) {
        return !repeatPassword.equalsIgnoreCase(password);
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
