package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.DataObjectBuilder;
import test_data.authentication.SignUpCreds;
import test_flows.authentication.SignUpFlow;

public class SignUpScreenTest extends BaseTest {

    @TmsLink("SignUp_001")
    @TmsLink("SignUp_002")
    @TmsLink("SignUp_003")
    @TmsLink("SignUp_004")
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

    @TmsLink("SignUp_005")
    @Test(dataProvider = "invalidLoginCreds", description = "Test Sign Up")
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
        String jsonLoc = "/test-data/authentication/invalidSignUpCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, SignUpCreds[].class);
    }

    @DataProvider
    public SignUpCreds[] validLoginCreds() {
        String jsonLoc = "/test-data/authentication/validSignUpCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, SignUpCreds[].class);
    }

}
