package TestAndReporting;


import base.Driver;
import org.openqa.selenium.WebDriver;

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
    public static ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    public static void setupBrowser(String browser) {
        Driver driver = new Driver(browser);
        threadLocalWebDriver.set(webDriver);
    }

    public static WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    public static void closeBrowser() {
        try {
            WebDriver webDriver = getWebDriver();
            if (webDriver != null) {
                webDriver.manage().deleteAllCookies();
                webDriver.quit();
            }
        } catch (Exception e) {
            Fail("Failed to close the browser instance. Please check the stack trace for more details.", takeScreenshot());
        }
        flushReports();
    }
}
