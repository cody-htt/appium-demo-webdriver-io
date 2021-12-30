package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.DataObjectBuilder;
import test_data.authentication.SignUpCreds;
import test_flows.authentication.SignUpFlow;

public class SignUpTest extends BaseTest {

    @Description("Verify Successfully Sign Up With Valid Credentials")
    @Test(dataProvider = "validLoginCreds", description = "Test Sign Up", priority = 2)
    public void signUpWithValidCreds(SignUpCreds signUpCreds) {
        //Init Appium driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        SignUpFlow signUpFlow = new SignUpFlow(androidDriver, signUpCreds);
        signUpFlow.navigateToLoginPage()
                  .signUp()
                  .verifyLoginWithCorrectCreds();
    }

    @Test(dataProvider = "invalidLoginCreds", description = "Test Sign Up", priority = 1)
    public void signUpWithInvalidCreds(SignUpCreds signUpCreds) {
        //Init Appium driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        SignUpFlow signUpFlow = new SignUpFlow(androidDriver, signUpCreds);
        signUpFlow.navigateToLoginPage()
                  .signUp()
                  .verifyLoginWithIncorrectCreds();
    }


    @DataProvider
    public SignUpCreds[] invalidLoginCreds() {
        String jsonLoc = "src/test/resources/data/authentication/invalidSignUpCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, SignUpCreds[].class);
    }

    @DataProvider
    public SignUpCreds[] validLoginCreds() {
        String jsonLoc = "src/test/resources/data/authentication/validSignUpCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, SignUpCreds[].class);
    }

}
