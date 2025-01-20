package Pages.CheckoutPages;


import static Config.Base.interactions;
import static Config.Base.waits;
import static ReusableMethods.commonMethods.clickOnButton;
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
public class OverviewPage {
    public static final String locatorType = "xpath";


    public static String overviewSummaryElements(String element) {

        switch (element) {
            case "Payment Information":
                element = "payment-info-label";
                break;
            case "Payment Information value":
                element = "payment-info-value";
                break;
            case "Shipping Information":
                element = "shipping-info-label";
                break;
            case "Shipping Information value":
                element = "shipping-info-value";
                break;
            case "Price Total":
                element = "total-info-label";
                break;
            case "Sub Total Value":
                element = "subtotal-label";
                break;
            case "Tax Value":
                element = "tax-label";
                break;
        }
        return "//div[@data-test='" + element + "']";
    }

    /**
     * <h1>Confirm elements on the Overview page</h1>
     *
     * <p>This method checks the visibility of elements (Payment Information, Shipping Information, Price Total, etc.)
     * on the Overview page. It also clicks the 'Finish' button after confirming the elements.</p>
     *
     * <p>If any element is not visible, it logs a failure. The method logs the action of clicking the 'Finish' button.</p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */
    public static void confirmOverviewElements() {
        String[] elements = {
                "Payment Information",
                "Payment Information value",
                "Shipping Information",
                "Shipping Information value",
                "Price Total",
                "Sub Total Value",
                "Tax Value"
        };

        for (String element : elements) {
            if (waits.isElementVisibleByLocator(locatorType, overviewSummaryElements(element))) {
                Pass(element + " is visible on the Overview page.");
            } else softFail(element + " is not visible on the over page.");
        }

        clickOnButton("Finish", "Checkout: Complete!", " Confirmed overview elements and clicked finish button.");
    }
}

