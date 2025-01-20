package ReusableMethods;


import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.ExtentReport.takeScreenshot;
import static Utils.LocatorTypeSelector.getLocatorType;
import static base.Driver.webDriver;

/**
 * <h1>Data Handling methods </h1>
 * Methods used to extract, manipulate or confirm text values from locators
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class DataHandlingMethods {

    /**
     * <h1>Extract text value from an element</h1>
     *
     * <p>This method retrieves the text value of an element identified by the given locator type and locator.</p>
     *
     * <p>If an error occurs during the extraction, it logs a failure and returns an empty string.</p>
     *
     * @param type The type of the locator (e.g., "id", "xpath").
     * @param locator The locator for the element.
     * @return The text value of the element.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public String extractTextValueByLocator(String type, String locator) {
        String text = "";
        try {
            return webDriver.findElement(getLocatorType(type,locator)).getText();
        } catch (Exception e) {
            Fail("Failed to extract text from frontend", takeScreenshot());
            return text;
        }
    }
}
