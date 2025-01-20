package base;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * <h1>Driver Class</h1>
 *
 * <p>This class handles the initialization of the Selenium WebDriver and configuration for interacting with the browser,
 * either Chrome or Firefox, through a Selenium Grid.</p>
 *
 * <p>The class provides methods for initializing the remote WebDriver, setting up the necessary capabilities,
 * and ensuring proper setup for web interactions using the FluentWait and WebDriverWait classes.</p>
 *
 * <h2>Methods:</h2>
 * <ul>
 *     <li><b>Driver(String browser):</b> Constructor that initializes the WebDriver based on the specified browser (Chrome or Firefox).</li>
 *     <li><b>initializeRemoteDriver(String browser):</b> Initializes the WebDriver remotely using Selenium Grid and
 *     sets the necessary capabilities for Chrome or Firefox.</li>
 * </ul>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public class Driver {
    public static WebDriver webDriver;
    public static Actions actions;
    public static FluentWait<WebDriver> driverFluentWait;
    public static WebDriverWait explicitWait;
    public static final Logger LOGGER = Logger.getLogger(Driver.class.getName());

    private static final String GRID_URL = "http://localhost:4444/wd/hub";

    public Driver(String browser) {
        initializeRemoteDriver(browser);
        explicitWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    private void initializeRemoteDriver(String browser) {
        try {
            DesiredCapabilities capabilities;

            if (browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--window-size=1920x1080");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--remote-allow-origins=*");
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--window-size=1920x1080");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
            } else {
                throw new IllegalArgumentException("Browser not supported: " + browser);
            }
            webDriver = new RemoteWebDriver(new URL(GRID_URL), capabilities);

            webDriver.manage().deleteAllCookies();
            webDriver.manage().window().maximize();
            actions = new Actions(webDriver);

            driverFluentWait = new FluentWait<>(webDriver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(StaleElementReferenceException.class);

        } catch (Exception e) {
            LOGGER.severe("Error initializing the Selenium Grid driver: " + e.getMessage());
        }
    }
}
