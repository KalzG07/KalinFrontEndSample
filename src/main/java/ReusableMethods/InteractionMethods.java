package ReusableMethods;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Level;

import static TestAndReporting.ExtentReport.Fail;
import static TestAndReporting.ExtentReport.takeScreenshot;
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

    public void clearTextByLocator(String locator) {
        try {
            WebElement element = driverFluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            Fail("Could not perform clear action on the locator due to: " + e.getMessage(), takeScreenshot());
        }
    }
    public void enterTextByLocator(String locator, String valueToType) {
        try {
            clickOnElement(locator);
            clearTextByLocator(locator);
            webDriver.findElement(By.id(locator)).sendKeys(valueToType);
        } catch (Exception e) {
            Fail("Failed to enter text into element.  Locator : " + locator + " | Text to be entered: " + valueToType , takeScreenshot());
        }
    }

    public void clickOnElement(String locator) {
        try {
            webDriver.findElement(By.id(locator)).click();
        } catch (Exception e) {
            Fail("Failed to click on the element. Locator : " + locator, takeScreenshot());
        }
    }


}
