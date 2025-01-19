package TestAndReporting;


import base.Driver;

import static TestAndReporting.ExtentReport.*;
import static base.Driver.webDriver;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public class SetupAndTearDown {


    public static void setupBrowser() {
        new Driver();
    }

    public static void closeBrowser() {
        try {
            webDriver.manage().deleteAllCookies();
        } catch (Exception e) {
            Fail("Failed to clear browser cookies", takeScreenshot());
        }

        try {
            webDriver.quit();
        } catch (Exception e) {
            Fail("Failed to close the browser instance. Please check the stack trace for more details.", takeScreenshot());
        }
    }
}
