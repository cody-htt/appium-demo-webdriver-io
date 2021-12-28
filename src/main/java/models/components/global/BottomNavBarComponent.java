package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class BottomNavBarComponent extends PageModel {

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

    /* Context: return element mobile only */
    public MobileElement bottomNavBarElem() {
        waitForVisibility(bottomNavBarElem);
        return bottomNavBarElem;
    }

    public MobileElement homeLabelELem() {
        waitForVisibility(homeLabelElem);
        return homeLabelElem;
    }

    public MobileElement webviewLabelElem() {
        waitForVisibility(webviewLabelElem);
        return webviewLabelElem;
    }

    public MobileElement loginLabelElem() {
        waitForVisibility(loginLabelElem);
        return loginLabelElem;
    }

    public MobileElement formsLabelElem() {
        waitForVisibility(formsLabelElem);
        return formsLabelElem;
    }

    public MobileElement swipeLabelElem() {
        waitForVisibility(swipeLabelElem);
        return swipeLabelElem;
    }

    public MobileElement dragLabelElem() {
        waitForVisibility(dragLabelElem);
        return dragLabelElem;
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
