package test_flows.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.pages.SwipePage;
import org.testng.asserts.SoftAssert;
import test_data.DataObjectBuilder;
import test_data.swipe_card.Card;
import utils.TestUtils;

import java.util.Arrays;
import java.util.HashMap;

public class SwipeFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private SwipePage swipePage;
    private final HashMap<String, String> expectedStringMap;
    private final SoftAssert softAssert;
    private final HashMap<String, String> cardContentMap;

    public SwipeFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
        this.softAssert = new SoftAssert();
        this.cardContentMap = convertCardJsonToMap();
    }

    public SwipeFlow navigateToSwipePage() {
        if (swipePage == null) {
            initSwipePage();
        }
        // Init Bottom Nav Comp and Navigate to Swipe Page
        BottomNavBarComponent bottomNavBarComp = swipePage.bottomNavBarComponent();
        bottomNavBarComp.clickOnSwipeLabel();
        return this;
    }

    public SwipeFlow initSwipePage() {
        swipePage = new SwipePage(appiumDriver);
        return this;
    }

    @Step("Verify First Card Title and Text")
    public SwipeFlow verifyFirstCard() {
        String actualCardTitle = swipePage.firstCardTitleElem().getText();
        String actualCardText = swipePage.firstCardTextElem().getText();
        String expectedCardTitle = expectedStringMap.get("open_source_card_title");
        String expectedCardText = expectedStringMap.get("open_source_card_text");

        softAssert.assertEquals(actualCardText, expectedCardText);
        softAssert.assertEquals(actualCardTitle, expectedCardTitle);
        return this;
    }

    @Step("Swipe To Next Card")
    public SwipeFlow swipeToNextCardAndVerifyCard() {
        do {
            swipePage.swipeToNextCard();
            String actualCardTitle = swipePage.centerCardTitleElem().getText();
            String actualCardText = swipePage.centerCardTextElem().getText();
            String expectedCardText = cardContentMap.get(actualCardTitle);
            softAssert.assertTrue(cardContentMap.containsKey(actualCardTitle));
            softAssert.assertEquals(actualCardText, expectedCardText);
        } while (swipePage.hasNextCard());
        return this;
    }

    private HashMap<String, String> convertCardJsonToMap() {
        HashMap<String, String> cardTextMap = new HashMap<>();
        DataObjectBuilder dataObjectBuilder = new DataObjectBuilder();
        //String jsonLoc = "/src/main/resources/test-data/swipe_page_card_text/CardText.json";
        String jsonLoc = "test-data/swipe_page_card_text/CardText.json";
        Arrays
                .stream(dataObjectBuilder.buildDataObject(jsonLoc,
                        Card[].class))
                .iterator()
                .forEachRemaining(card -> cardTextMap.put(card.getCardTitle(), card.getCardText()));
        return cardTextMap;
    }

    @Step("Swipe down to find WebDriver IO Logo")
    public SwipeFlow swipeDownToLogo() {
        swipePage.swipeToWebDriverIOLogo();
        return this;
    }

    @Step("Verify Logo Is Displayed and Text is correct")
    public SwipeFlow verifyLogoDisplayedAndText() {
        String actualLogoTitle = swipePage.webDriverIOLogoTextElem().getText();
        String expectedLogoTitle = expectedStringMap.get("logo_title");
        softAssert.assertTrue(swipePage.webDriverIOLogo().isDisplayed());
        softAssert.assertEquals(actualLogoTitle, expectedLogoTitle);
        softAssert.assertAll();
        return this;
    }
}
