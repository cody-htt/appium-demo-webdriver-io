package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.DataObjectBuilder;
import test_data.authentication.LoginCreds;

public class SignUpTest extends BaseTest {

    @Test
    public void signUp() throws InterruptedException {
        String jsonLoc = "/src/main/resources/test-data/authentication/ValidLoginCreds.json";
        LoginCreds loginCreds = DataObjectBuilder.buildDataObject(jsonLoc, LoginCreds[].class)[0];

        // Init driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginPage loginPage = new LoginPage(androidDriver);

        // 1st service method approach | Provide component level
        loginPage.bottomNavComp().clickOnLoginLabel();


        // Second service method approach | Hide Component Level
        loginPage.clickOnSignUpLabel();
        loginPage.signUpInputUsername(loginCreds.getUsername());
        loginPage.signUpInputPassword(loginCreds.getPassword());
        loginPage.signUpRetypePassword(loginCreds.getPassword());
        loginPage.clickOnOnSignUpBtn();

        Thread.sleep(3000);
    }

}
