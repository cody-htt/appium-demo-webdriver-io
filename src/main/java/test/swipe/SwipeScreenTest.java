package test.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.swipe.SwipeFlow;

public class SwipeScreenTest extends BaseTest {

    @TmsLink("Swipe_001")
    @Description("User can swipe and texts are displayed correctly")
    @Test(description = "Verify Card Title and Text are displayed correctly")
    public void verifyCardTitleAndText() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        SwipeFlow swipeFlow = new SwipeFlow(appiumDriver);
        swipeFlow
                .navigateToSwipePage()
                .verifyFirstCard()
                .swipeToNextCardAndVerifyCard();
    }

    @TmsLink("Swipe_001")
    @Description("User can swipe and texts are displayed correctly")
    @Test(description = "Verify Card Title and Text are displayed correctly")
    public void verifyRobotLogoAndTitle() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        SwipeFlow swipeFlow = new SwipeFlow(appiumDriver);
        swipeFlow
                .navigateToSwipePage()
                .swipeDownToLogo()
                .verifyLogoDisplayedAndText();
    }
}
