package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.DataObjectBuilder;
import test_data.authentication.LoginCreds;
import test_flows.authentication.LoginFlow;

public class LoginTest extends BaseTest {

    @TmsLink("Login_001")
    @TmsLink("Login_002")
    @TmsLink("Login_003")
    @Description("Test login with data driven...")
    @Test(dataProvider = "invalidLoginCredsData", description = "Login Test", priority = 1)
    public void loginWithIncorrectCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.navigateToLoginPage()
                 .login(loginCreds)
                 .verifyLoginWithIncorrectCreds(loginCreds);
    }

    @TmsLink("Login_004")
    @Description("Test login with correct creds...")
    @Test(dataProvider = "validLoginCredsData",description = "Login Test", priority = 2)
    public void loginWithcorrectCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.navigateToLoginPage()
                 .login(loginCreds)
                 .verifyLoginWithCorrectCreds();
    }

    @DataProvider
    public LoginCreds[] invalidLoginCredsData() {
        String jsonLoc = "src/test/resources/data/authentication/invalidLoginCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] validLoginCredsData() {
        String jsonLoc = "src/test/resources/data/authentication/validLoginCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, LoginCreds[].class);
    }
}
