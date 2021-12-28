package models.pages;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.base.PageModel;
import models.components.global.BottomNavBarComponent;
import models.components.webview_comp.LeftNavBarComponent;
import org.openqa.selenium.support.FindBy;

public class WebviewPage extends PageModel {

    @FindBy(css = ".navbar__toggle > svg")
    private MobileElement leftNavBarToggleBtnElem;
    @FindBy(css = ".hero__title  > svg")
    private MobileElement robotLogoElem;
    @FindBy(css = ".hero__subtitle")
    private MobileElement logoTextFieldElem;

    public WebviewPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        appiumDriver.context(Context.WEBVIEW.getContext());
    }

    public MobileElement robotLogoElem() {
        waitForVisibility(robotLogoElem);
        return robotLogoElem;
    }

    public MobileElement logoTextFieldElem() {
        waitForVisibility(logoTextFieldElem);
        return logoTextFieldElem;
    }

    public LeftNavBarComponent clickOnLeftNavBarToggleBtn() {
        clickElement(leftNavBarToggleBtnElem);
        return new LeftNavBarComponent(appiumDriver);
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(appiumDriver);
    }

    public static class MenuItem {
        private final String label;
        private final String href;

        public MenuItem(String label, String href) {
            this.label = label;
            this.href = href;
        }

        @Override
        public String toString() {
            return "[Label: " + label + " | " + "href: " + href + "]";
        }
    }
}
