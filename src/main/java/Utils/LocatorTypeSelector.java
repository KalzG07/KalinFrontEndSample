package Utils;


import org.openqa.selenium.By;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class LocatorTypeSelector {

    public static By getLocatorType(String type, String locator) {
        switch (type) {
            case "id":
                return By.id(locator);

            case "xpath":
                return By.xpath(locator);
            default:
                return null;
        }
    }
}
