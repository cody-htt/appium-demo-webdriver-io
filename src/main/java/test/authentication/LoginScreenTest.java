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

public class LoginScreenTest extends BaseTest {

    @TmsLink("Login_001")
    @TmsLink("Login_002")
    @TmsLink("Login_003")
    @Description("Test login with data driven...")
    @Test(dataProvider = "invalidLoginCredsData", description = "Login Test")
    public void loginWithIncorrectCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver, loginCreds);
        loginFlow.navigateToLoginPage()
                 .login()
                 .verifyLoginFail();
    }

    @TmsLink("Login_004")
    @Description("Test login with correct creds...")
    @Test(dataProvider = "validLoginCredsData", description = "Login Test")
    public void loginWithCorrectCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver, loginCreds);
        loginFlow.navigateToLoginPage()
                 .login()
                 .verifyLoginSuccessful();
    }

    @DataProvider
    public LoginCreds[] invalidLoginCredsData() {
        /* Use this path to run directly in IntelliJ
         * String jsonLoc = "/src/main/resources/test-data/authentication/invalidLoginCreds.json";
         * The following path is used when executing test with Jar file */
        String jsonLoc = "test-data/authentication/invalidLoginCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] validLoginCredsData() {
        /* Use this path to run directly in IntelliJ
         * String jsonLoc = "/src/main/resources/test-data/authentication/validLoginCreds.json";
         * The following path is used when executing test with Jar file */
        String jsonLoc = "test-data/authentication/validLoginCreds.json";
        return new DataObjectBuilder().buildDataObject(jsonLoc, LoginCreds[].class);
    }
}
