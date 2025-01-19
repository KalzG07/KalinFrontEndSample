package ReusableMethods;


import static Config.Base.interactions;
import static Config.Base.waits;
import static Pages.ReusableLocators.elementsById;
import static Pages.ReusableLocators.titleText;
import static TestAndReporting.ExtentReport.*;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class commonMethods {

    public static void clickOnButton(String action, String expectedText, String message) {

        interactions.clickOnElement("id", elementsById(action));
        if (waits.isElementVisibleByLocator("xpath", titleText(expectedText))) {
            Pass("Successfully " + message);
        } else Fail("failed to " + message, takeScreenshot());
    }
}
