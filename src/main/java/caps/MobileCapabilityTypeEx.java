package caps;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
    String SYSTEM_PORT = "systemPort";
    String PORT = "port";
    String CHROME_DRIVER_PORT = "chromedriverPort";
}
