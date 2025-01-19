package Pages.LoginPage;


import Config.Base;

import static Config.propertiesdReader.*;


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


    public static String loginInputFunctions(String id) {
        switch (id) {
            case "Username":
                id = "user-name";
                break;
            case "Password":
                id = "password";
                break;
            case "Login":
                id = "login-button";
            default:
                System.out.println("Option not available");

        }

        return id;
    }


    public static void loginIntoSauce() {
        username = getProperty("ValidUsername");
        password = getProperty("ValidPassword");
        interactions.enterTextByLocator(loginInputFunctions("Username"), username);
        interactions.enterTextByLocator(loginInputFunctions("Password"), password);
        interactions.clickOnElement(loginInputFunctions("Login"));
    }


}
