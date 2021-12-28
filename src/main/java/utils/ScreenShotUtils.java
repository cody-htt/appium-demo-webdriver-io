package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {

    AppiumDriver<MobileElement> appiumDriver;

    public ScreenShotUtils(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void takeWholeScreenShot() {
        String imagePath = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + "whole_screen.png";
        File image = appiumDriver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(image, new File(imagePath));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void takeElemScreenShot(MobileElement element, String fileName) {
        String csScreenLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + fileName + ".png";
        File elemScreenShot = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(elemScreenShot, new File(csScreenLocation));
        } catch (IOException ex) { ex.printStackTrace(); }
    }

}
