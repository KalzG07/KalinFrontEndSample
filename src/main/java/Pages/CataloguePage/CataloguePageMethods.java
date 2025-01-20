package Pages.CataloguePage;


import static Config.Base.*;
import static Config.propertiesAndDataReaders.getJSONPath;
import static Config.propertiesAndDataReaders.getJsonObjectData;
import static Pages.ReusableLocators.titleText;
import static TestAndReporting.ExtentReport.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;


/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class CataloguePageMethods {
    public static final String locatorType = "xpath";
    private static final String PATH = getJSONPath("ProductData.json");
    private static JSONObject selectedProductData;


    public static String inventoryItemElements(String element, String text) {

        switch (element) {
            case "item name":
                element = "inventory-item-name";
                break;
            case "item description":
                element = "inventory-item-desc";
                break;
            case "item price":
                element = "inventory_item_price";
                break;
        }

        if (element.equals("inventory_item_price")) {
            return "//div[@class='" + element + "' and string()='" + text + "']";
        } else {
            return "//div[@data-test='" + element + "' and normalize-space(text())=\"" + text + "\"]";
        }
    }

    public static String addToCartButton(String buttonData) {
        return "//button[@data-test='" + buttonData + "']";
    }

    public static String removeFromCartButton = "//button[starts-with(@data-test, 'remove-')]";

    public static String cartBadge = "//span[@data-test='shopping-cart-badge']";


    public static String getButtonDataTest(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        return "add-to-cart-" + formattedName;
    }



    /**
     * <h1>Catalogue page methods</h1>
     * Methods used to interact and test elements on the catalogue page
     * <p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */


    /**
     * <h1>Confirm items are present on the catalogue page</h1>
     *
     * <p>This method checks if each product's details (name, description, price) and the 'Add to Cart' button
     * are visible on the catalogue page by iterating through the product list from the JSON file (ProductsData.json).</p>
     *
     * <p>If any element is missing, it logs a failure and captures a screenshot. If the product data cannot be fetched,
     * a failure is logged.</p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static void confirmIfItemsArePresent() {
        Info("Checking is all items and details are present on the screen.");
        String[] inventoryElements = {"item name", "item description", "item price"};
        JSONArray products = getJsonObjectData(PATH, "Products");


        if (products != null) {
            for (Object o : products) {
                JSONObject product = (JSONObject) o;
                for (String element : inventoryElements) {
                    String text = "";
                    switch (element) {
                        case "item name":
                            text = product.get("name").toString();
                            break;
                        case "item description":
                            text = product.get("description").toString();
                            break;
                        case "item price":
                            text = product.get("price").toString();
                            break;
                    }

                    if (waits.isElementVisibleByLocator(locatorType, inventoryItemElements(element, text))) {
                        Info(element + " is visible on screen for: " + text);
                    } else {
                        softFail(element + " is not present on screen for: " + text, takeScreenshot());
                    }
                }

                String productName = product.get("name").toString();
                String buttonDataTest = getButtonDataTest(productName);

                if (waits.isElementVisibleByLocator(locatorType, addToCartButton(buttonDataTest))) {
                    Info("Add to Cart button is visible for: " + productName);
                } else {
                    softFail("Add to Cart button is not present for: " + productName, takeScreenshot());
                }
            }
        } else {
            Fail("Unable to fetch product data from JSON.", takeScreenshot());
        }
    }

    /**
     * <h1>Click and validate random item on the catalogue page</h1>
     *
     * <p>This method randomly selects a product from the JSON file (ProductsData.json), clicks the 'Add to Cart' button,
     * and verifies if the button changes to 'Remove'. It also checks if the cart badge reflects the correct number of items
     * in the cart.</p>
     *
     * <p>If any issue is found, such as the button not being visible or the cart badge not matching, it logs a failure
     * and captures a screenshot. If an error occurs during the process, it logs the error.</p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static void clickAndValidateRandomItem() {
        try {
            JSONArray products = getJsonObjectData(PATH, "Products");
            if (products != null && !products.isEmpty()) {
                Random random = new Random();
                int randomIndex = random.nextInt(products.size());
                selectedProductData = (JSONObject) products.get(randomIndex);

                String productName = selectedProductData.get("name").toString();
                String buttonDataTest = getButtonDataTest(productName);

                if (waits.isElementVisibleByLocator(locatorType, addToCartButton(buttonDataTest))) {
                    interactions.clickOnElement(locatorType, addToCartButton(buttonDataTest));
                    Info("Clicked 'Add to Cart' button for: " + productName);
                } else {
                    softFail("'Add to Cart' button not visible for: " + productName, takeScreenshot());
                    return;
                }

                String removeDataTest = "remove-" + productName.toLowerCase().replace(" ", "-");

                if (waits.isElementVisibleByLocator(locatorType, addToCartButton(removeDataTest))) {
                    Info("Button changed to 'Remove' for: " + productName);
                } else {
                    softFail("Button did not change to 'Remove' for: " + productName, takeScreenshot());
                    return;
                }

                List<WebElement> removeButtons = waits.findElementsByLocator(locatorType, removeFromCartButton);
                int removeButtonCount = removeButtons.size();
                Info("Number of 'Remove' buttons: " + removeButtonCount);

                if (waits.isElementVisibleByLocator(locatorType, cartBadge)) {
                    String cartBadgeValue = data.extractTextValueByLocator(locatorType, cartBadge);
                    if (Integer.parseInt(cartBadgeValue) == removeButtonCount) {
                        Info("Cart badge value (" + cartBadgeValue + ") matches the 'Remove' button count (" + removeButtonCount + ").");
                    } else {
                        softFail("Cart badge value (" + cartBadgeValue + ") does not match 'Remove' button count (" + removeButtonCount + ").", takeScreenshot());
                    }
                } else {
                    softFail("Cart badge not visible after adding the item.", takeScreenshot());
                }
            } else {
                Fail("Unable to fetch product data from JSON.", takeScreenshot());
            }
        } catch (Exception e) {
            softFail("An error occurred while validating 'Add to Cart' functionality: " + e.getMessage(), takeScreenshot());
        }
    }

    public static void navigateToCart() {
        interactions.clickOnElement(locatorType, cartBadge);
        if (waits.isElementVisibleByLocator(locatorType, titleText("Your Cart"))) {
            Pass("Successfully clicked on the cart button.");
        } else Fail("Failed to navigate to cart page.", takeScreenshot());
    }

}

