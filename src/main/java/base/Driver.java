package base;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.logging.Logger;

/**
 * <h1>Header </h1>
 * description
 * <p>
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

    public Driver() {
        initializeRemoteDriver();
        explicitWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    private void initializeRemoteDriver() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920x1080");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

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
