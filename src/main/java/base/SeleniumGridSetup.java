package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class SeleniumGridSetup {
    public static WebDriver getDriver(String browser) {
        WebDriver driver = null;
        try {
            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920x1080");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new RemoteWebDriver(gridUrl, capabilities);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
                driver = new RemoteWebDriver(gridUrl, capabilities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
}
