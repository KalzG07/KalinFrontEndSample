package TestAndReporting;


import base.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public class ExtentReport extends Driver {
    public static final Logger LOGGER = Logger.getLogger(ExtentReport.class.getName());
    private static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    public static ExtentTest node;
    public static ExtentTest test;
    private static final String SYSTEM_DELIMITER = File.separator;
    private static ExtentSparkReporter spark;
    public static ExtentReports extent;
    static final String DIRECTORY_PATH = System.getProperty("user.dir") + SYSTEM_DELIMITER + "ExtentReports";
    private static final Logger logger = Logger.getLogger(ExtentReport.class.getName());

    public ExtentReport(String browser) {
        super(browser);
    }


    public static void setupReporting() {
        if (!new File(DIRECTORY_PATH).exists()) {
            boolean status = new File(DIRECTORY_PATH).mkdirs();
            logger.log(Level.CONFIG, "New directory created: {0}", String.valueOf(status));
        }

        if (spark == null) {
            spark = new ExtentSparkReporter(DIRECTORY_PATH + SYSTEM_DELIMITER + "ExtentReportResults.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            logger.log(Level.INFO, "HTML Reporter has been initialized!");
            try {
                extent.setSystemInfo("Host name", InetAddress.getLocalHost().getHostName());
                extent.setSystemInfo("user", "" + System.getProperty("user.name") + "");
            } catch (UnknownHostException e) {
                logger.log(Level.WARNING,"Error from report setup" + e);
            }
        }
    }

    public static void initializeTest(String testName, String scenarioName) {
        test = extent.createTest(testName).assignCategory(scenarioName);
    }

    public static ExtentTest getExtentTest() {
        return node;
    }

    public static void Pass(String message) {
        getExtentTest().log(Status.PASS, message);
    }

    public static void Info(String message) {
        getExtentTest().info(message);
    }

    public static void Fail(String message, String s) {
        try {
            getExtentTest().log(Status.FAIL, message);
            Assert.fail(message);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(message);
        }
    }

    public static void softFail(String message, String path) {
        getExtentTest().log(Status.FAIL, message + getTest().addScreenCaptureFromBase64String(path));
    }

    public static void softFail(String message) {
        getExtentTest().log(Status.FAIL, message);
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        node = test.createNode(testName, desc);
        extentTestMap.put((int) (Thread.currentThread().getId()), node);
        return node;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) (Thread.currentThread().getId()));
    }

    public static String takeScreenshot() {
        String path = "";
        try {
            TakesScreenshot ts = (TakesScreenshot) webDriver;
            path = ts.getScreenshotAs(OutputType.BASE64);
            LOGGER.log(Level.INFO, "A screenshot has been taken");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return path;
    }
}
