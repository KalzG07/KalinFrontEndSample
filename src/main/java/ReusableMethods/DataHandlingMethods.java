package ReusableMethods;


import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.ExtentReport.takeScreenshot;
import static Utils.LocatorTypeSelector.getLocatorType;
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
public class DataHandlingMethods {

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
