package Pages.LoginPage;


import Config.Base;

import static Config.propertiesAndDataReaders.*;
import static Pages.ReusableLocators.elementsById;


/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public class LoginPageMethods implements Base {

    static String username;
    static String password;
    public static final String locatorType = "id";


    public static void loginIntoSauce() {
        username = getProperty("ValidUsername");
        password = getProperty("ValidPassword");
        interactions.enterTextByLocator(locatorType, elementsById("Username"), username);
        interactions.enterTextByLocator(locatorType, elementsById("Password"), password);
        interactions.clickOnElement(locatorType, elementsById("Login"));
    }


}
