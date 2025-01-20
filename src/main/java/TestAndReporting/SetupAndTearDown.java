package TestAndReporting;


import base.Driver;
import org.openqa.selenium.WebDriver;

import static TestAndReporting.ExtentReport.*;
import static base.Driver.webDriver;
/**
 * <h1>SetupAndTearDown Class</h1>
 *
 * <p>This class is responsible for managing the setup and teardown of the WebDriver instance used in automated tests.</p>
 * <p>It handles browser initialization, provides access to the WebDriver instance, and ensures proper cleanup by closing the browser and clearing any cookies.</p>
 *
 * <p>The class uses ThreadLocal to store the WebDriver instance for use in multi-threaded environments, ensuring that each thread has its own instance of WebDriver.</p>
 *
 * <h2>Methods:</h2>
 * <ul>
 *     <li><b>setupBrowser(String browser):</b> Initializes the WebDriver instance based on the specified browser.</li>
 *     <li><b>getWebDriver():</b> Returns the current WebDriver instance for the current thread.</li>
 *     <li><b>closeBrowser():</b> Closes the WebDriver instance, deletes cookies, and ensures proper cleanup.</li>
 * </ul>
 *
 * @author Kalin Govender
 * @since 2025/01/20
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
            Fail("Failed to close the browser instance. Please check the stack trace for more details.", String.valueOf(e));
        }
        flushReports();
    }
}
