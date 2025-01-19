package Pages.CartPage;


import static Config.Base.interactions;
import static Pages.ReusableLocators.elementsById;
import static Pages.ReusableLocators.titleText;
import static ReusableMethods.commonMethods.clickOnButton;
import static TestAndReporting.ExtentReport.*;

import static Config.Base.waits;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class CartPageMethods {
    public static String locatorType = null;


    public static String cartColumns(String text) {
        String type = null;
        if (text.equals("Quantity")) {
            type = "quantity";
        } else if (text.equals("Description")) {
            type = "desc";
        }
        return "//div[@data-test='cart-" + type + "-label']";
    }

    public static void confirmCartElements() {
        String[] columns = {"Quantity", "Description"};
        String[] actions = {"Continue Shopping", "Checkout"};

        for (String column : columns) {
            locatorType = "xpath";

            if (waits.isElementVisibleByLocator(locatorType, cartColumns(column))) {
                Pass(column + " is present on the cart screen.");
            } else softFail(column + " is not present on cart screen.", takeScreenshot());
        }

        for (String action : actions) {
            locatorType = "id";

            if (waits.isElementVisibleByLocator(locatorType, elementsById(action))) {
                Pass(action + " is present on the cart screen.");
            } else softFail(action + " is not present on cart screen.", takeScreenshot());
        }
    }

    public static void clickOnCheckOut() {
        clickOnButton("Checkout", "Checkout: Your Information", "Clicked on Checkout button and navigated to Checkout : Your Information screen.");
    }
}

