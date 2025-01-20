package ReusableMethods;


import Utils.LocatorTypeSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import static Utils.LocatorTypeSelector.getLocatorType;
import static base.Driver.*;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class SeleniumElementMethods {

    public static final int GLOBAL_TIMEOUT = 15;
    public static final int POLLING_TIME = 1;


    /**
     * <h1>Check if an element is visible by its locator</h1>
     *
     * <p>This method waits for the visibility of an element within a specified timeout and polling interval.</p>
     *
     * <p>If the element is found and visible, it returns true; otherwise, false.</p>
     *
     * @param type    The type of the locator (e.g., "xpath", "css").
     * @param locator The locator for the element.
     * @return true if the element is visible; false otherwise.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public boolean isElementVisibleByLocator(String type, String locator) {
        boolean isPresent = false;
        try {
            explicitWait.withTimeout(Duration.ofSeconds(GLOBAL_TIMEOUT)).ignoring(Exception.class).pollingEvery(Duration.ofMillis(POLLING_TIME)).
                    until(ExpectedConditions.visibilityOfElementLocated(getLocatorType(type, locator)));
            isPresent = true;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Could not find element because of {0}", e.getMessage());
        }
        return isPresent;
    }

    /**
     * <h1>Find elements by locator</h1>
     *
     * <p>This method returns a list of elements located by the specified locator.</p>
     *
     * @param locatorType The type of the locator (e.g., "xpath", "css").
     * @param locator     The locator for the elements.
     * @return List of WebElements matching the locator.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public List<WebElement> findElementsByLocator(String locatorType, String locator) {
        By by = getLocator(locatorType, locator);
        return webDriver.findElements(by);
    }

    /**
     * <h1>Get locator by type</h1>
     *
     * <p>This method returns the appropriate locator based on the provided locator type.</p>
     *
     * @param locatorType The type of the locator (e.g., "xpath", "css").
     * @param locator     The locator value.
     * @return By locator.
     * @throws IllegalArgumentException if an invalid locator type is provided.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    private By getLocator(String locatorType, String locator) {
        switch (locatorType.toLowerCase()) {
            case "xpath":
                return By.xpath(locator);
            case "css":
                return By.cssSelector(locator);
            default:
                throw new IllegalArgumentException("Invalid locator type");
        }
    }
}
