package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import models.components.global.BottomNavBarComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

import java.util.List;

public class BasePage {

    //    protected ThreadLocal<AppiumDriver<MobileElement>> appiumDriver;
    protected AppiumDriver<MobileElement> appiumDriver;

    protected BasePage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver), this);
    }

    protected void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisibility(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
    }

    /* Get element's attribute */
    protected String getElementAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    /* Get element's attribute by locator */
    protected String getElementAttribute(By locator, String attribute) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementAttribute(element, attribute);
    }

    /* Perform click action on an element */
    protected void clickElement(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    /* Perform click action on an element by its locator */
    protected void clickElement(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        clickElement(element);
    }

    /* Send keys to an input-field element */
    protected void sendKeysToElement(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    /* Send keys to an input-field element by its locator */
    protected void sendKeysToElement(By locator, String input) {
        MobileElement element = appiumDriver.findElement(locator);
        sendKeysToElement(element, input);
    }

    /* Clear text in an input-field element */
    protected void clearElementInputField(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    /* Clear text in an input-field element by its locator */
    protected void clearElementInputField(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        clearElementInputField(element);
    }

    /* Directly get inner-text from an element */
    protected String getElementText(MobileElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    /* Directly get inner-text from an element by its locator */
    protected String getElementText(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementText(element);
    }

    /* Check if an element is existed by its locator */
    protected boolean isElementPresent(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements.size() > 0;
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(this.appiumDriver);
    }

}
