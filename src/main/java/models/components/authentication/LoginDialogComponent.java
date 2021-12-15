package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDialogComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By msgTitleSel = MobileBy.id("android:id/alertTitle");

    public LoginDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String msgTitle(){
        WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(msgTitleSel));
        return appiumDriver.findElement(msgTitleSel).getText();
    }

}
