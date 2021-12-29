package models.pages;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.webview_comp.LeftNavBarComponent;
import org.openqa.selenium.support.FindBy;

public class WebviewPage extends BasePage {

    @FindBy(css = ".navbar__toggle > svg")
    private MobileElement leftNavBarToggleBtnElem;
    @FindBy(css = ".hero__title  > svg")
    private MobileElement robotLogoElem;
    @FindBy(css = ".hero__subtitle")
    private MobileElement logoTextFieldElem;

    public WebviewPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver.context(Context.WEBVIEW.getContext());
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

    public static class MenuItem {
        private final String label;
        private final String href;

        public MenuItem(String label, String href) {
            this.label = label;
            this.href = href;
        }

        public String getLabel() {
            return label;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "[Label: " + label + " | " + "href: " + href + "]";
        }
    }
}
