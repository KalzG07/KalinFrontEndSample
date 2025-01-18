import org.testng.annotations.*;

import java.lang.reflect.Method;

import static ReusableMethods.navigationMethods.navigateToURL;
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
public class swaglabs {

    @BeforeClass
    public static void setupTestClass() {
        setupReporting();
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

    }
}
