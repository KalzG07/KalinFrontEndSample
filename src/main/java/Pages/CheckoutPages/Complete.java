package Pages.CheckoutPages;


import static Config.Base.waits;
import static TestAndReporting.ExtentReport.Pass;
import static TestAndReporting.ExtentReport.softFail;

/**
 * <h1>Checkout :Complete page Elements and Methods </h1>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class Complete {
    public static final String locatorType = "xpath";


    public static String ThankYouHeader = "//h2[@data-test='complete-header'][text()='Thank you for your order!']";
    public static String ThankYouText = "//div[@data-test='complete-text'][text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']";


    /**
     * <h1>Confirm elements on the Checkout Complete page</h1>
     *
     * <p>This method checks the visibility of elements (Thank You header and text) on the Checkout Complete page.</p>
     *
     * <p>If any element is not visible, it logs a failure.</p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static void confirmCompleteOrderElements() {
        String[] elements = {ThankYouHeader, ThankYouText};

        for (String element : elements) {
            if (waits.isElementVisibleByLocator(locatorType, element)) {
                Pass(element + " is visible on the Checkout Complete page.");
            } else softFail(element + " is not visible on the Checkout Complete page.");
        }
    }
}
