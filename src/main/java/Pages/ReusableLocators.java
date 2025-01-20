package Pages;


import static TestAndReporting.ExtentReport.softFail;

/**
 * <h1>Reusable Locators </h1>
 *
 * This centralizes a handler for locators where, they can be referenced from different points in the project
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class ReusableLocators {

    public static String titleText(String text) {
        return "//span[@data-test='title'][text()='" + text + "']";
    }

    /**
     * <h1>Get element ID by name</h1>
     *
     * <p>This method returns the corresponding element ID for a given name. It maps user-friendly names
     * like "Username", "Password", etc., to their respective IDs used in the UI.</p>
     *
     * <p>If the name is not recognized, it logs a failure.</p>
     *
     * @param id The name of the element to look up.
     * @return The corresponding element ID.
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static String elementsById(String id) {
        switch (id) {
            case "Username":
                id = "user-name";
                break;
            case "Password":
                id = "password";
                break;
            case "Login":
                id = "login-button";
                break;
            case "Continue Shopping":
                id = "continue-shopping";
                break;
            case "Checkout":
                id = "checkout";
                break;
            case "First Name":
                id = "first-name";
                break;
            case "Last Name":
                id = "last-name";
                break;
            case "Postal Code":
                id = "postal-code";
                break;
            case "Cancel":
                id = "cancel";
                break;
            case "Continue":
                id = "continue";
                break;
            case "Finish":
                id = "finish";
                break;
            case "Back to products":
                id = "back-to-products";
                break;

            default:
                softFail(id + " is not defined correctly.");
        }
        return id;
    }
}
