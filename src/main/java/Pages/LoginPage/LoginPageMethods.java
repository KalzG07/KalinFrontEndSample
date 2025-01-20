package Pages.LoginPage;


import Config.Base;

import static Config.propertiesAndDataReaders.*;
import static Pages.ReusableLocators.elementsById;
import static ReusableMethods.NavigationMethods.navigateToURL;
import static TestAndReporting.ExtentReport.Info;


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

    /**
     * <h1>Login method</h1>
     * This method will navigate to Sauce Labs URL and login with valid credentials
     * <p>
     *
     * Credentials are saved in TestData.properties file, under properties folder.
     * @author Kalin Govender
     * @since 2025/01/20
     */

    public static void loginIntoSauce() {
        Info("navigating and logging into Sauce Labs");
        navigateToURL("https://www.saucedemo.com/");
        username = getProperty("ValidUsername");
        password = getProperty("ValidPassword");
        interactions.enterTextByLocator(locatorType, elementsById("Username"), username);
        interactions.enterTextByLocator(locatorType, elementsById("Password"), password);
        interactions.clickOnElement(locatorType, elementsById("Login"));
        Info("Logged into Sauce Labs.");

    }


}
