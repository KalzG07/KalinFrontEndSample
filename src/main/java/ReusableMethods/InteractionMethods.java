package ReusableMethods;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.ExtentReport.takeScreenshot;
import static Utils.LocatorTypeSelector.getLocatorType;
import static base.Driver.driverFluentWait;
import static base.Driver.webDriver;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class InteractionMethods {

    public void clearTextByLocator(String type, String locator) {
        try {
            WebElement element = driverFluentWait.until(ExpectedConditions.presenceOfElementLocated(getLocatorType(type, locator)));
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            Fail("Could not perform clear action on the locator due to: " + e.getMessage(), takeScreenshot());
        }
    }

    public void enterTextByLocator(String type, String locator, String valueToType) {
        try {
            clickOnElement(type, locator);
            clearTextByLocator(type, locator);
            webDriver.findElement((getLocatorType(type, locator))).sendKeys(valueToType);
        } catch (Exception e) {
            Fail("Failed to enter text into element.  Locator : " + locator + " | Text to be entered: " + valueToType, takeScreenshot());
        }
    }

    public void clickOnElement(String type, String locator) {
        try {
            webDriver.findElement(getLocatorType(type, locator)).click();
        } catch (Exception e) {
            Fail("Failed to click on the element. Locator : " + locator, takeScreenshot());
        }
    }


}
