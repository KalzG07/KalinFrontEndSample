import org.testng.annotations.*;

import java.lang.reflect.Method;

import static Config.propertiesdReader.loadProperties;
import static Pages.LoginPage.LoginPageMethods.loginIntoSauce;
import static ReusableMethods.NavigationMethods.navigateToURL;
import static TestAndReporting.ExtentReport.*;
import static TestAndReporting.SetupAndTearDown.*;


/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public class testclass {

    @BeforeClass
    public static void setupTestClass() {
        setupReporting();
        loadProperties("TestData.properties");
        initializeTest("test example", "scenario example");
    }

    @BeforeMethod
    private void setupTestMethod() {
        setupBrowser();
    }

    @Test()
    private void trafficTestone(Method method) {
        startTest(method.getName(), "describe test");
        navigateToURL("https://www.saucedemo.com/");
        loginIntoSauce();

    }
}
