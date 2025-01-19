package Pages.CheckoutPages;


import static Config.Base.interactions;
import static Pages.ReusableLocators.elementsById;
import static ReusableMethods.commonMethods.clickOnButton;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class YourInformationPage {
    public static final String locatorType = "id";


    public static void fillInInformation() {
        interactions.enterTextByLocator(locatorType, elementsById("First Name"), "Firstname");
        interactions.enterTextByLocator(locatorType, elementsById("Last Name"), "Lastname");
        interactions.enterTextByLocator(locatorType, elementsById("Postal Code"), "123456");

        clickOnButton("Continue", "Checkout: Overview", " Entered Information and click continue button.");

    }
}

