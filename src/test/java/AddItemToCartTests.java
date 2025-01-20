import com.github.javafaker.Faker;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.sql.SQLException;

import static Config.propertiesAndDataReaders.loadProperties;
import static Pages.CartPage.CartPageMethods.clickOnCheckOut;
import static Pages.CartPage.CartPageMethods.confirmCartElements;
import static Pages.CataloguePage.CataloguePageMethods.*;
import static Pages.CheckoutPages.Complete.confirmCompleteOrderElements;
import static Pages.CheckoutPages.OverviewPage.confirmOverviewElements;
import static Pages.CheckoutPages.YourInformationPage.fillInInformation;
import static Pages.LoginPage.LoginPageMethods.loginIntoSauce;
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
public class AddItemToCartTests {


    @BeforeClass
    public static void setupTestClass() {
        setupReporting();
        loadProperties("TestData.properties");
        initializeTest("Test add to cart functionality", "Add item to cart and complete checkout process.");
    }

    @BeforeMethod
    @Parameters("browser")
    private void setupTestMethod(String browser) {
        setupBrowser(browser);
    }

    @Test()
    private void AddItemToCartAndCompleteCheckout(Method method) throws SQLException {
        startTest(method.getName(), "Test to see if an item can be added to cart and successfully complete checkout process.");

        loginIntoSauce();
        confirmIfItemsArePresent();
        clickAndValidateRandomItem();
        navigateToCart();
        confirmCartElements();
        clickOnCheckOut();
        fillInInformation();
        confirmOverviewElements();
        confirmCompleteOrderElements();

    }

    @AfterMethod
    private void endDriver() {
        closeBrowser();
        Info("Cookies cleared & Browser instance closed.");
    }
}
