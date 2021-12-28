package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.components.global.BottomNavBarComponent;
import org.openqa.selenium.By;
import utils.SwipeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipePage extends BasePage {
    public final List<String> CARD_TEXT = new ArrayList<>(
            Arrays.asList("FULLY OPEN SOURCE", "GREAT COMMUNITY", "JS.FOUNDATION", "SUPPORT VIDEOS", "EXTENDABLE", "COMPATIBLE"));

    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[1]/android.widget.TextView[1]")
    private MobileElement firstCardTitleElem;
    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[1]/android.widget.TextView[2]")
    private MobileElement firstCardTextElem;
    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]")
    private MobileElement centerCardTitleElem;
    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[2]")
    private MobileElement centerCardTextElem;
    @AndroidFindBy(accessibility = "WebdriverIO logo")
    private MobileElement webDriverIOLogo;
    @AndroidFindBy(xpath = "//*[@content-desc='WebdriverIO logo']/following-sibling::android.widget.TextView")
    private MobileElement webDriverIOLogoTextElem;

    private final By webDriverIOLogoLoc = MobileBy.AccessibilityId("WebdriverIO logo");

    private final SwipeUtils swipeUtils;

    public SwipePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        swipeUtils = new SwipeUtils(this.appiumDriver);
    }

    /* Return Mobile Element */
    public MobileElement firstCardTitleElem() {
        waitForVisibility(firstCardTitleElem);
        return firstCardTitleElem;
    }

    public MobileElement firstCardTextElem() {
        waitForVisibility(firstCardTextElem);
        return firstCardTextElem;
    }

    public MobileElement centerCardTitleElem() {
        waitForVisibility(centerCardTitleElem);
        return centerCardTitleElem;
    }

    public MobileElement centerCardTextElem() {
        waitForVisibility(centerCardTextElem);
        return centerCardTextElem;
    }

    public MobileElement webDriverIOLogoTextElem() {
        waitForVisibility(webDriverIOLogoTextElem);
        return webDriverIOLogoTextElem;
    }

    /* Provide main interaction on specific Mobile Element */
    public void swipeToNextCard() {
        swipeUtils.swipeToLeft(0.86);
    }

    public void swipeToWebDriverIOLogo() {
        swipeUtils.swipeUpUntilElementFound(webDriverIOLogoLoc, 0.72);
    }

    public static class Card {
        private final String cardName;
        private final String cardText;

        public Card(String cardName, String cardText) {
            this.cardName = cardName;
            this.cardText = cardText;
        }

        @Override
        public String toString() {
            return "Card name: " + cardName + "\n" + "Card text: " + cardText + "\n------------------------------------------------";
        }
    }
}
