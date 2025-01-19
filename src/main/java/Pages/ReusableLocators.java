package Pages;


import static TestAndReporting.ExtentReport.softFail;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class ReusableLocators {

    public static String titleText(String text) {
        return "//span[@data-test='title'][text()='" + text + "']";
    }


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
