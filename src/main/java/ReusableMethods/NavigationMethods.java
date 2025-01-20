package ReusableMethods;

import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.SetupAndTearDown.getWebDriver;

/**
 * <h1>Navigation methods </h1>

 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public class NavigationMethods {
    public static void navigateToURL(String url) {
        try {
            getWebDriver().get(url);
        } catch (Exception e) {
            Info("Failed to navigate to webpage. URL used : " + url + ". Error message: " + e.getMessage());
        }
    }
}
