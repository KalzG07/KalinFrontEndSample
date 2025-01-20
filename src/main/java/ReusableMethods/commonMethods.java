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
    /**
     * <h1>Click on a button and validate the result</h1>
     *
     * <p>This method clicks on a button specified by the action name and verifies if the expected text appears
     * on the next page. It logs a success or failure based on the visibility of the expected text.</p>
     *
     * @param action The action name corresponding to the button.
     * @param expectedText The text expected to appear after the button is clicked.
     * @param message The message to log on success or failure.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static void clickOnButton(String action, String expectedText, String message) {

        interactions.clickOnElement("id", elementsById(action));
        if (waits.isElementVisibleByLocator("xpath", titleText(expectedText))) {
            Pass("Successfully " + message);
        } else Fail("failed to " + message, takeScreenshot());
    }
}
