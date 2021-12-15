package test.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import test.BaseTest;

import java.time.Duration;

public class SwipeToOpenNotification extends BaseTest {

    @Test
    public void swipe() {
        AppiumDriver<MobileElement> androidDriver = getDriver();


        // Get Mobile window size
        Dimension windowSize = androidDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Calculate touch point
        int xStartPoint = 50 * screenWidth / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = 0;
        int yEndPoint = 50 * screenHeight / 100;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Perform Touch actions
        TouchAction touchAction = new TouchAction(androidDriver);

        // Swipe down to open notification
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform(); // without this, will be no action at all


        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(startPoint)
                .release()
                .perform(); // without this, will be no action at all

    }
}
