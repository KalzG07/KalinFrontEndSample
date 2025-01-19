package ReusableMethods;


import Utils.LocatorTypeSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;

import static Utils.LocatorTypeSelector.getLocatorType;
import static base.Driver.*;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class SeleniumElementMethods {

    public static final int GLOBAL_TIMEOUT = 15;
    public static final int POLLING_TIME = 1;


    public boolean isElementVisibleByLocator(String type, String locator) {
        boolean isPresent = false;
        try{
            explicitWait.withTimeout(Duration.ofSeconds(GLOBAL_TIMEOUT)).ignoring(Exception.class).pollingEvery(Duration.ofMillis(POLLING_TIME)).
                    until(ExpectedConditions.visibilityOfElementLocated(getLocatorType(type, locator)));
            isPresent = true;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, "Could not find element because of {0}", e.getMessage());
        }
        return isPresent;
    }

    public List<WebElement> findElementsByLocator(String locatorType, String locator) {
        By by = getLocator(locatorType, locator);
        return webDriver.findElements(by);
    }
    private By getLocator(String locatorType, String locator) {
        switch (locatorType.toLowerCase()) {
            case "xpath": return By.xpath(locator);
            case "css": return By.cssSelector(locator);
            default: throw new IllegalArgumentException("Invalid locator type");
        }
    }
}
