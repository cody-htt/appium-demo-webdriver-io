package test;

import driver.DriverFactory;
import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {

    private final static List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String udid;
    private String port;
    private String systemPort;
    private String chromedriverPort;

    @BeforeTest(alwaysRun = true)
    @Parameters({ "udid", "port", "systemPort", "chromedriverPort" })
    public void beforeTest(String udid, String port, String systemPort, String chromedriverPort) {
        System.out.println(udid + " | " + port + " | " + systemPort + " | " + chromedriverPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
        this.chromedriverPort = chromedriverPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitAppiumSession();
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver(udid, port, systemPort, chromedriverPort);
    }

    public String getUdid() {
        return driverThread.get().getUdid();
    }

    // TODO: this can be enum type

//    @BeforeMethod(alwaysRun = true)
//    public void beforeMethod() {
//        System.out.print(this.getClass().getSimpleName() + " - ");
//        System.out.println(udid + "|" + port + "|" + systemPort + " | " + chromedriverPort);
//        driverThread.get().getAppiumDriver(udid, port, systemPort, chromedriverPort).context(Context.NATIVE.getContext());
//    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test Method name
            String testMethodName = result.getName();

            // 2. Declare the file location
            TestUtils testUtils = new TestUtils();
            String dateTaken = testUtils.getDateTime();
            String fileLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + testMethodName + "_" + dateTaken + ".png";

            // 3. Declare the file name

            // 4. Save the screenshot to the system
            File screenShot = getDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation.trim()));
                Path content = Paths.get(fileLocation);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testMethodName, is);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
