package Pages.CheckoutPages;


import static Config.Base.waits;
import static TestAndReporting.ExtentReport.Pass;
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
public class Complete {
    public static final String locatorType = "xpath";


    public static String ThankYouHeader = "//h2[@data-test='complete-header'][text()='Thank you for your order!']";
    public static String ThankYouText = "//div[@data-test='complete-text'][text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']";


    public static void confirmCompleteOrderElements() {
        String[] elements = {ThankYouHeader, ThankYouText};

        for (String element : elements) {
            if (waits.isElementVisibleByLocator(locatorType, element)) {
                Pass(element + " is visible on the Checkout Complete page.");
            } else softFail(element + " is not visible on the Checkout Complete page.");
        }
    }
}
