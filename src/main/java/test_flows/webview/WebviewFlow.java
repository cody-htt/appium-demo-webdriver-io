package test_flows.webview;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavBarComponent;
import models.components.webview_comp.LeftNavBarComponent;
import models.pages.WebviewPage;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import test_data.DataObjectBuilder;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WebviewFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private WebviewPage webviewPage;
    private LeftNavBarComponent leftNavBarComp;
    private SoftAssert softAssert;
    private HashMap<String, String> expectedStringMap;

    public WebviewFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.expectedStringMap = new TestUtils().getExpectedStringMap();
        this.softAssert = new SoftAssert();
    }

    public WebviewFlow navigateToWebviewPage() {
//        changeDriverToNative();
        BottomNavBarComponent bottomNavBarComp = new BottomNavBarComponent(appiumDriver);
        bottomNavBarComp.clickOnWebviewLabel();
        if (webviewPage == null) {
            appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
            initWebviewPage();
            changeDriverToWebview();
        }
        return this;
    }

    public WebviewFlow initWebviewPage() {
        webviewPage = new WebviewPage(appiumDriver);
        return this;
    }

    @Step("Verify Robot Logo and Page Header is Displayed")
    public WebviewFlow verifyWebviewHeader() {
        String actualHeaderText = webviewPage.logoTextFieldElem().getText();
        String expectedHeaderText = expectedStringMap.get("webview_page_header");
        softAssert.assertTrue(webviewPage.robotLogoElem().isDisplayed());
        softAssert.assertEquals(actualHeaderText, expectedHeaderText);
        softAssert.assertAll();
        return this;
    }

    @Step("Expand Left NavBar")
    public WebviewFlow expandLeftNavBar() {
        leftNavBarComp = webviewPage.clickOnLeftNavBarToggleBtn();
        return this;
    }

    @Step("Verify All Menu Items and Their Hyperlink are displayed")
    public WebviewFlow verifyMenuItems(String udid) {
        List<WebviewPage.MenuItem> actualMenuItems = fetchMenuItemsToList();
        List<WebviewPage.MenuItem> expectedMenuItems = convertMenuItemJsonToList(udid);
        Assert.assertEquals(actualMenuItems.size(), expectedMenuItems.size());
        AtomicInteger index = new AtomicInteger(0);
        actualMenuItems.forEach(menuItem -> {
            WebviewPage.MenuItem expectedMenuItem = expectedMenuItems.get(index.get());
            softAssert.assertEquals(menuItem.getLabel(), expectedMenuItem.getLabel());
            softAssert.assertEquals(menuItem.getHref(), expectedMenuItem.getHref());
            index.incrementAndGet();
        });
        softAssert.assertAll();
        return this;
    }

    private List<WebviewPage.MenuItem> fetchMenuItemsToList() {
        List<WebviewPage.MenuItem> menuItemsList = new ArrayList<>();
        leftNavBarComp.menuItemList().forEach(item -> {
            if (StringUtils.isEmpty(item.getText())) {
                menuItemsList.add(new WebviewPage.MenuItem("Github Logo", item.getAttribute("href")));
            } else {
                menuItemsList.add(new WebviewPage.MenuItem(item.getText(), item.getAttribute("href")));
            }
        });
        return menuItemsList;
    }

    private List<WebviewPage.MenuItem> convertMenuItemJsonToList(String udid) {
        List<WebviewPage.MenuItem> menuItemsList = new ArrayList<>();
        DataObjectBuilder dataObjectBuilder = new DataObjectBuilder();
        //String jsonLoc = "/src/main/resources/test-data/webview_menu_item/MenuItem.json";
        String jsonLoc = "test-data/webview_menu_item/MenuItem.json";
        if (udid.equalsIgnoreCase("CB5A2BZKHF")) {
            jsonLoc = jsonLoc.replace("MenuItem.json", "MenuItem_Only_CB5A2BZKHF.json");
        }
        Arrays
                .stream(dataObjectBuilder.buildDataObject(jsonLoc, WebviewPage.MenuItem[].class))
                .iterator()
                .forEachRemaining(menuItemsList :: add);
        return menuItemsList;
    }

    @Step("Change Appium Driver Context To Web View")
    public void changeDriverToWebview() {
        appiumDriver.context(Context.WEBVIEW.getContext());
    }
}
