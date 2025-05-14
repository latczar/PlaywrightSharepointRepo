package unitTests;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import utils.completeLoginWithCode;
import utils.userCredentials;
import pages.LoginPage;
import base.commonFunctions;
import reporting.testListener;

public class UserLoginTest extends testListener {
    
    /*
     * Variables declaration 
     */
    private static LoginPage loginPage;
    private static commonFunctions cf;
    private static completeLoginWithCode loginWithCode;
    private static testListener testListen;
    
    // Constructor
    public UserLoginTest() throws IOException {
        super();
    }
    
    /*
     * Set up test environment
     * Invocation/Initialization of objects
     */
    @BeforeMethod
    public void before() throws IOException, Exception {
        // Initialize Playwright Browser
        cf = new commonFunctions();
    	testListen = new testListener();
        loginPage = new LoginPage(commonFunctions.getPage());
        loginWithCode = new completeLoginWithCode(commonFunctions.getPage());

        // Navigate to login page
        cf.navigateTo("https://portal-preprod.breeam.com");
    }
    
    /*
     * Start Test case execution
     */
    @Test
    public void userLoginTest() throws Exception {
        System.out.println("Starting test case execution..");

        // Playwright handles tabs differently, switch to the first tab
        commonFunctions.getPage().bringToFront();

        // Capture screenshot
        commonFunctions.getPage().screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("./screenshots/tab_0.png")));

        // Perform login
        loginPage.loginWithOTP(userCredentials.BREusername, userCredentials.BREAppPassword);
        loginPage.generateOTPLoginCode();
        loginWithCode.process();

        // Validate if the dashboard loaded successfully
        //Validate if the dashboard loaded successfully by checking for a unique element
        Locator dashboardHeader = commonFunctions.getPage().locator("(//a[contains(text(), 'Dashboard')])[1]");
        dashboardHeader.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        
     //Capture a screenshot after login
        commonFunctions.getPage().screenshot(new Page.ScreenshotOptions().setPath(java.nio.file.Paths.get("./screenshots/after_login.png")));

    }
    
    /*
     * Stop execution for test case 
     * Browser teardown
     */
    @AfterSuite
    public void closeBrowser() {
        System.out.println("Saving test execution report...");
    	testListen.saveData();
        System.out.println("Test execution finished, closing browser...");
        cf.closeBrowser();
    }
}
