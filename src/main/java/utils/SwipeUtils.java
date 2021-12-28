package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SwipeUtils {

    private final double SCREEN_SIZE_PERCENTAGE = 1.0D;
    private final int MID_POINT_FACTOR = 2;

    private AppiumDriver<MobileElement> appiumDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;

    public SwipeUtils(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.mobileScreenSize = appiumDriver.manage().window().getSize();
        this.touchAction = new TouchAction(appiumDriver);
    }

    public void horizontallySwipeToElem(By locator, String direction, int scrollTimes) {
        for (int scroll = 0 ; scroll < scrollTimes ; scroll++) {
            if (isDisplayed(locator)) { break; } else {
                if (direction.equalsIgnoreCase("up")) {
                    swipeToElem("up");
                } else {
                    swipeToElem("down");
                }
            }
        }
    }

    /* Swipe left basing on swipeTimes */
    public void verticallySwipeToElem(By locator, String direction, int swipeTimes) {
        for (int scroll = 0 ; scroll < swipeTimes ; scroll++) {
            if (isDisplayed(locator)) { break; } else {
                if (direction.equalsIgnoreCase("left")) {
                    swipeToElem("left");
                } else {
                    swipeToElem("right");
                }
            }
        }
    }

    /* Swipe left one time */
    public void verticallySwipeToElem(String direction) {
        if (direction.equalsIgnoreCase("left")) {
            swipeToElem("left");
        } else {
            swipeToElem("right");
        }
    }


    private void swipeToElem(String direction) {
        int xStartPoint = mobileScreenSize.getWidth() / MID_POINT_FACTOR;
        int xEndpointPoint = mobileScreenSize.getWidth() / MID_POINT_FACTOR;
        int yStartPoint = mobileScreenSize.getHeight() / MID_POINT_FACTOR;
        int yEndpoint = mobileScreenSize.getHeight() / MID_POINT_FACTOR;

        switch (direction) {
            case "up":
                yStartPoint = ( int ) (mobileScreenSize.getHeight() * 0.8);
                yEndpoint = ( int ) (mobileScreenSize.getHeight() * 0.2);
                break;
            case "down":
                yStartPoint = ( int ) (mobileScreenSize.getHeight() * 0.2);
                yEndpoint = ( int ) (mobileScreenSize.getHeight() * 0.8);
                break;
            case "left":
                xStartPoint = ( int ) (mobileScreenSize.getWidth() * 0.8);
                xEndpointPoint = ( int ) (mobileScreenSize.getWidth() * 0.2);
                break;
            case "right":
                xStartPoint = ( int ) (mobileScreenSize.getWidth() * 0.2);
                xEndpointPoint = ( int ) (mobileScreenSize.getWidth() * 0.8);
                break;
        }
        PointOption startPoint = PointOption.point(xStartPoint, yStartPoint);
        PointOption endPoint = PointOption.point(xEndpointPoint, yEndpoint);
        touchAction.press(startPoint)
                   .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                   .moveTo(endPoint)
                   .release().perform();
    }

    private boolean isDisplayed(final By locator) {
        appiumDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 2);
            return wait.until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver appiumDriver) {
                    return appiumDriver.findElement(locator).isDisplayed();
                }
            });
        } catch (Exception ex) {
            return false;
        }
    }

    /* The following methods perform swipe from a visible element to another visible element */
    public void swipeToElement(By fromLocator, By toLocator) {
        ElementOption fromElement = new ElementOption().withElement(appiumDriver.findElement(fromLocator));
        ElementOption toElement = new ElementOption().withElement(appiumDriver.findElement(toLocator));
        performSwipe(toElement, fromElement, 500);
    }

    /* The following methods perform swipe up action */
    public void swipeUpUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeUpStartPoint(percentage);
        PointOption endPoint = getSwipeUpEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeUpUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeUpStartPoint(percentage);
        PointOption endPoint = getSwipeUpEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    public void swipeUp(double percentage) {
        PointOption startPoint = getSwipeUpStartPoint(percentage);
        PointOption endPoint = getSwipeUpEndPoint(percentage);
        performSwipe(startPoint, endPoint, 800);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeUpStartPoint(double percentage) {
        int xStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.height * percentage);
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeUpEndPoint(double percentage) {
        int xEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe down action */
    public void swipeDownUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeDownStartPoint(percentage);
        PointOption endPoint = getSwipeDownEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeDownUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeDownStartPoint(percentage);
        PointOption endPoint = getSwipeDownEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    public void swipeDown(double percentage) {
        PointOption startPoint = getSwipeDownStartPoint(percentage);
        PointOption endPoint = getSwipeDownEndPoint(percentage);
        performSwipe(startPoint, endPoint, 1000);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeDownStartPoint(double percentage) {
        int xStartPoint = mobileScreenSize.getWidth() / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.getHeight() * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeDownEndPoint(double percentage) {
        int xEndPoint = mobileScreenSize.getWidth() / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.getHeight() * percentage);
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to left action */
    public void swipeToLeft(double percentage) {
        PointOption startPoint = getSwipeToLeftStartPoint(percentage);
        PointOption endPoint = getSwipeToLeftEndPoint(percentage);
        performSwipe(startPoint, endPoint, 800);
    }

    public void swipeLeftUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeToLeftStartPoint(percentage);
        PointOption endPoint = getSwipeToLeftEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeLeftUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeToLeftStartPoint(percentage);
        PointOption endPoint = getSwipeToLeftEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeToLeftStartPoint(double percentage) {
        int xStartPoint = ( int ) (mobileScreenSize.width * percentage);
        int yStartPoint = mobileScreenSize.height / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToLeftEndPoint(double percentage) {
        int xEndPoint = ( int ) (mobileScreenSize.width * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yEndPoint = mobileScreenSize.height / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to right action */
    public void swipeRightUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeToRightStartPoint(percentage);
        PointOption endPoint = getSwipeToRightEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeRightUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeToRightStartPoint(percentage);
        PointOption endPoint = getSwipeToRightEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    /* The following methods will return the vertical points for swipe to right action */
    private PointOption getSwipeToRightStartPoint(double percentage) {
        int xStartPoint = ( int ) (mobileScreenSize.width * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yStartPoint = mobileScreenSize.height / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToRightEndPoint(double percentage) {
        int xEndPoint = ( int ) (mobileScreenSize.width * percentage);
        int yEndPoint = mobileScreenSize.height / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* This method will simply perform a swipe action */
    private void performSwipe(PointOption startPoint, PointOption endPoint, int waitTime) {
        touchAction.press(startPoint)
                   .waitAction(new WaitOptions().withDuration(Duration.ofMillis(waitTime)))
                   .moveTo(endPoint)
                   .release()
                   .perform();
    }

    /* Common method */
    private void swipeUntilElementFound(By locator, PointOption startPoint, PointOption endPoint) {
        int shortWaitTime = 800;
        boolean continueToSwipe = true;
        while (continueToSwipe) {
            performSwipe(startPoint, endPoint, shortWaitTime);
            continueToSwipe = isElementFound(locator);
        }
    }

    private void swipeUntilElementFound(By locator, int maxSwipeTimes, PointOption startPoint, PointOption endPoint) {
        int longWaitTime = 1200;
        int swipeTime = 0;
        boolean continueToSwipe = true;
        while ((swipeTime < maxSwipeTimes) && continueToSwipe) {
            performSwipe(startPoint, endPoint, longWaitTime);
            continueToSwipe = isElementFound(locator);
            swipeTime++;
        }
    }

    private boolean isElementFound(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements.isEmpty();
    }
}
