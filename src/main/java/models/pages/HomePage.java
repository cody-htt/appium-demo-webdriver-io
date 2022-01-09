package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage{

    @AndroidFindBy(accessibility = "Home-screen")
    private MobileElement homeScreenView;
    @AndroidFindBy(xpath = "//*[@content-desc='Home-screen']/android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement appDescriptionElem;
    @AndroidFindBy(xpath = "//*[@content-desc='Home-screen']/android.view.ViewGroup/android.widget.TextView[5]")
    private MobileElement supportChannelElem;
//    private MobileElement appDescriptionElem = homeScreenView.findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[2]"));
//    private MobileElement supportChannelElem = homeScreenView.findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[5]"));

    public HomePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    @Step("Get Description Element")
    public MobileElement appDescriptionElem() {
        return appDescriptionElem;
    }

    @Step("Get Support Channel Element")
    public MobileElement supportChannelElem() {
        return supportChannelElem;
    }
}
