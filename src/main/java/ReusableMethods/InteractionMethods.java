package ReusableMethods;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.ExtentReport.takeScreenshot;
import static Utils.LocatorTypeSelector.getLocatorType;
import static base.Driver.driverFluentWait;
import static base.Driver.webDriver;

/**
 * <h1>Interaction methods </h1>
 * Methods that are used to interact with the front end, such as clicks clear text
 * and enter text which can be used throughout the project.
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class InteractionMethods {

    /**
     * <h1>Clear text in an element</h1>
     *
     * <p>This method clears the text in an input field identified by the given locator type and locator.</p>
     *
     * <p>If an error occurs during the clearing action, it logs a failure.</p>
     *
     * @param type The type of the locator (e.g., "id", "xpath").
     * @param locator The locator for the element.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public void clearTextByLocator(String type, String locator) {
        try {
            WebElement element = driverFluentWait.until(ExpectedConditions.presenceOfElementLocated(getLocatorType(type, locator)));
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            Fail("Could not perform clear action on the locator due to: " + e.getMessage(), takeScreenshot());
        }
    }
    /**
     * <h1>Enter text into an element</h1>
     *
     * <p>This method clicks on an element, clears any existing text, and enters the specified text.</p>
     *
     * <p>If an error occurs during the action, it logs a failure.</p>
     *
     * @param type The type of the locator (e.g., "id", "xpath").
     * @param locator The locator for the element.
     * @param valueToType The text value to enter into the element.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public void enterTextByLocator(String type, String locator, String valueToType) {
        try {
            clickOnElement(type, locator);
            clearTextByLocator(type, locator);
            webDriver.findElement((Objects.requireNonNull(getLocatorType(type, locator)))).sendKeys(valueToType);
        } catch (Exception e) {
            Fail("Failed to enter text into element.  Locator : " + locator + " | Text to be entered: " + valueToType, takeScreenshot());
        }
    }
    /**
     * <h1>Click on an element</h1>
     *
     * <p>This method clicks on an element identified by the given locator type and locator.</p>
     *
     * <p>If an error occurs during the click action, it logs a failure.</p>
     *
     * @param type The type of the locator (e.g., "id", "xpath").
     * @param locator The locator for the element.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public void clickOnElement(String type, String locator) {
        try {
            webDriver.findElement(Objects.requireNonNull(getLocatorType(type, locator))).click();
        } catch (Exception e) {
            Fail("Failed to click on the element. Locator : " + locator, takeScreenshot());
        }
    }


}
