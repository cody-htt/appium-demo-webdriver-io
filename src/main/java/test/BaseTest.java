package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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

    @BeforeTest(alwaysRun = true)
    @Parameters({"udid", "port", "systemPort"})
    public void beforeTest(String udid, String port, String systemPort) {
        System.out.println(udid + "|" + port + "|" + systemPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
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
        return driverThread.get().getAppiumDriver(udid, port, systemPort);
    }

    // TODO: this can be enum type
//    public static AppiumDriver<MobileElement> getDriver(String mobileDriverName){
//        return driverThread.get().getDriver(browserName);
//    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test Method name
            String testMethodName = result.getName();

            // 2. Declare the file location
//            Calendar calendar = new GregorianCalendar();
//            int y = calendar.get(Calendar.YEAR);
//            int m = calendar.get(Calendar.MONTH);
//            int d = calendar.get(Calendar.DATE);
//            int hr = calendar.get(Calendar.HOUR_OF_DAY);
//            int min = calendar.get(Calendar.MINUTE);
//            int sec = calendar.get(Calendar.SECOND);
//            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + sec;
//            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + testMethodName + "_"+ dateTaken + ".png";
            TestUtils testUtils = new TestUtils();
            String dateTaken = testUtils.getDateTime();
            String fileLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + testMethodName + "_" + dateTaken + ".png";

            // 3. Declare the file name

            // 4. Save the screenshot to the system
            File screenShot = driverThread.get().getAppiumDriver().getScreenshotAs(OutputType.FILE);

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
