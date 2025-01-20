package Pages.CheckoutPages;


import com.github.javafaker.Faker;

import java.sql.SQLException;

import static Config.Base.interactions;
import static MySQL.MySQLMethods.*;
import static Pages.ReusableLocators.elementsById;
import static ReusableMethods.commonMethods.clickOnButton;

/**
 * <h1>Checkout :Your Information page Elements and Methods </h1>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class YourInformationPage {
    public static final String locatorType = "id";
    static Faker faker = new Faker();
    static String firstname = faker.name().firstName();
    static String lastname = faker.name().lastName();
    static String postalCode = faker.address().zipCode();


    /**
     * <h1>Fill in user information on the checkout page</h1>
     *
     * <p>This method enters user details (First Name, Last Name, Postal Code) into the respective fields
     * and clicks the 'Continue' button to proceed to the next page.</p>
     *
     * <p>After entering the information, it logs the action of clicking the 'Continue' button.</p>
     *
     * @author Kalin Govender
     * @since 2025/01/20
     */

    public static void fillInInformation() throws SQLException {
        interactions.enterTextByLocator(locatorType, elementsById("First Name"), firstname);
        interactions.enterTextByLocator(locatorType, elementsById("Last Name"), lastname);
        interactions.enterTextByLocator(locatorType, elementsById("Postal Code"), postalCode);

        checkAndCreateDatabase();
        addUserAndConfirmRecord(firstname, lastname, postalCode);

        clickOnButton("Continue", "Checkout: Overview", " Entered Information and click continue button.");

    }
}

