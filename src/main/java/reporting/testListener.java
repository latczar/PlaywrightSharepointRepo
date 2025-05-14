package reporting;

import com.microsoft.playwright.Page;
import base.commonFunctions;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testListener extends extentManager implements ITestListener {
    private static Page page;
    private static ExtentTest testReport;
    static commonFunctions commonFunctions = new commonFunctions();

    @BeforeMethod
    public void setUpLogger(ITestResult result) {
        testReport = extentManager.initializeReport().createTest(result.getMethod().getMethodName());
        extent.setTest(testReport);
        page = base.commonFunctions.getPage(); // Get Playwright Page instance
    }

    @AfterMethod
    public static void sparkTestResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            testReport.log(Status.FAIL, "Test FAILED - " + result.getName());
            testReport.log(Status.FAIL, "Test FAILED - " + result.getMethod());
            testReport.log(Status.FAIL, "Test FAILED - " + result.getThrowable());

            // Capture Screenshot with Playwright
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String filePath = "./test-output/FailedTestsScreenshots/" + result.getName() + " - " + dateName + ".png";

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));

            // Attach Screenshot to Report
            extent.getTest().fail(MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
        } 
        else if (result.getStatus() == ITestResult.SKIP) {
            testReport.log(Status.SKIP, "Test SKIPPED - " + result.getName());
        } 
        else if (result.getStatus() == ITestResult.SUCCESS) {
            testReport.log(Status.PASS, "Test PASSED - " + result.getName());
        }

		// Close the Playwright browser after each test
        commonFunctions.closeBrowser();
    }

    public void saveData() {
        extentManager.initializeReport().flush();
    }
}
