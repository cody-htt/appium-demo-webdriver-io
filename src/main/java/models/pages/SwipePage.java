package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import utils.SwipeUtils;

public class SwipePage extends BasePage {

    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[1]")
    private MobileElement firstCardTextContainerElem;
    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[2]")
    private MobileElement centerCardTextContainerElem;
    @AndroidFindBy(xpath = "(//*[@content-desc='card'])[3]")
    private MobileElement nextCardElem;
    @AndroidFindBy(accessibility = "WebdriverIO logo")
    private MobileElement webDriverIOLogo;
    @AndroidFindBy(xpath = "//*[@content-desc='WebdriverIO logo']/following-sibling::android.widget.TextView")
    private MobileElement webDriverIOLogoTextElem;

    private By webDriverIOLogoLoc = MobileBy.AccessibilityId("WebdriverIO logo");

    private final SwipeUtils swipeUtils;

    public SwipePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        swipeUtils = new SwipeUtils(this.appiumDriver);
    }

    /* Return Mobile Element */
    public MobileElement firstCardTextContainer() {
        waitForVisibility(firstCardTextContainerElem);
        return firstCardTextContainerElem;
    }

    public MobileElement firstCardTitleElem() {
        return firstCardTextContainerElem.findElement(By.xpath("//android.widget.TextView[1]"));
    }

    public MobileElement firstCardTextElem() {
        return firstCardTextContainerElem.findElement(By.xpath("//android.widget.TextView[2]"));
    }


    public MobileElement centerCardTitleElem() {
        return centerCardTextContainerElem.findElement(By.xpath("//android.widget.TextView[1]"));
    }

    public MobileElement centerCardTextElem() {
        return centerCardTextContainerElem.findElement(By.xpath("//android.widget.TextView[2]"));
    }

    public MobileElement webDriverIOLogo() {
        waitForVisibility(webDriverIOLogo);
        return webDriverIOLogo;
    }

    public MobileElement webDriverIOLogoTextElem() {
        waitForVisibility(webDriverIOLogoTextElem);
        return webDriverIOLogoTextElem;
    }

    /* Provide main interaction on specific Mobile Element */
    public boolean hasNextCard() {
        try {
            nextCardElem.isDisplayed();
        } catch (NoSuchElementException | TimeoutException ex) {
            return false;
        }
        return true;
    }

    public void swipeToNextCard() {
        swipeUtils.swipeToLeft(0.86);
    }

    public void swipeToWebDriverIOLogo() {
        swipeUtils.swipeUpUntilElementFound(webDriverIOLogoLoc, 0.73);
    }

}
