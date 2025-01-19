package base;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

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

    public Driver() {
        initializeChromeDriver();
    }

    private void initializeChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920x1080");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);

        webDriver = new ChromeDriver(options);
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        actions = new Actions(webDriver);

        // Initialize FluentWait
        driverFluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(10))  // Maximum wait time
                .pollingEvery(Duration.ofMillis(500)) // Polling interval
                .ignoring(NoSuchElementException.class) // Ignore common exceptions
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);
    }
}
