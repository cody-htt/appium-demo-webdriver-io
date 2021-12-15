package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private AppiumDriver<MobileElement> appiumDriver;
    private AppiumDriverLocalService appiumServer;

    public AppiumDriver<MobileElement> getAppiumDriver(String udid, String port, String systemPort) {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver(udid, port, systemPort);
        return appiumDriver;
    }

    private AppiumDriver<MobileElement> initAppiumDriver(String udid, String port, String systemPort) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, udid);
        desiredCapabilities.setCapability("systemPort", Integer.parseInt(systemPort));
//        desiredCapabilities.setCapability("mjpegServerPort", Integer.parseInt(port));
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
        String localAppium = System.getenv("localAppium");
        String hub = System.getProperty("hub");

        String targetServer;
        if (localAppium != null)
            targetServer = localAppium + "/wd/hub";
        else if (hub != null)
            targetServer = hub + ":4444/wd/hub";
        else
            throw new IllegalArgumentException("You need to provide localAppium/hub");

        try {
            URL appiumServerPath = new URL(targetServer);
            appiumDriver = new AndroidDriver<>(appiumServerPath, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver();
        return appiumDriver;
    }

    private AppiumDriver<MobileElement> initAppiumDriver() {
        AppiumServiceBuilder appiumServerBuilder = new AppiumServiceBuilder();
        appiumServerBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
        appiumServerBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServerBuilder);
        appiumServer.start();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "3300d3672cca62b9");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        return appiumDriver;
    }

    public void quitAppiumSession() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }

}