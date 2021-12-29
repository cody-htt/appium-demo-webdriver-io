package models.components.webview_comp;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeftNavBarComponent extends BasePage {

    @FindBy(css = ".navbarSidebarToggle_wkoY")
    private MobileElement viewModeToggleBtnElem;
    @FindBy(css = ".menu__list")
    private MobileElement navBarMenuListElem;
    @FindBy(css = ".menu__list-item > a")
    private MobileElement menuItemElem;

    private final static By menuItemLoc = MobileBy.cssSelector(".menu__list-item > a");

    public LeftNavBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement viewModeToggleBtnElem() {
        waitForVisibility(viewModeToggleBtnElem);
        return viewModeToggleBtnElem;
    }

    public MobileElement navBarMenuListElem() {
        waitForVisibility(navBarMenuListElem);
        return navBarMenuListElem;
    }

    public List<MobileElement> menuItemList() {
        waitForVisibility(menuItemElem);
        return this.appiumDriver.findElements(menuItemLoc);
    }

}
