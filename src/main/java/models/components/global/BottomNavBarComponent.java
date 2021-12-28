package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.pages.BasePage;

public class BottomNavBarComponent extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Home']/parent::android.view.ViewGroup")
    private MobileElement bottomNavBarElem;
    @AndroidFindBy(accessibility = "Home")
    private MobileElement homeLabelElem;
    @AndroidFindBy(accessibility = "Webview")
    private MobileElement webviewLabelElem;
    @AndroidFindBy(accessibility = "Login")
    private MobileElement loginLabelElem;
    @AndroidFindBy(accessibility = "Forms")
    private MobileElement formsLabelElem;
    @AndroidFindBy(accessibility = "Swipe")
    private MobileElement swipeLabelElem;
    @AndroidFindBy(accessibility = "Drag")
    private MobileElement dragLabelElem;

    public BottomNavBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    /* Context: provide main interaction method with Mobile Element of this page */

    public void clickOnHomeLabel() {
        clickElement(homeLabelElem);
    }

    public void clickOnWebviewLabel() {
        clickElement(webviewLabelElem);
    }

    public void clickOnLoginLabel() {
        clickElement(loginLabelElem);
    }

    public void clickOnFormsLabel() {
        clickElement(formsLabelElem);
    }

    public void clickOnSwipeLabel() {
        clickElement(swipeLabelElem);
    }

    public void clickOnDragLabel() {
        clickElement(dragLabelElem);
    }

}
