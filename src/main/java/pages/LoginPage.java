package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import base.commonFunctions;
import reporting.extent;
import utils.mailosaurAPI;

public class LoginPage extends commonFunctions {
    private final Page page;
    private final mailosaurAPI emailCode;

    // **Locators**
    private final Locator loginButton;
    private final Locator enterEmail;
    private final Locator proceedButton;
    private final Locator enterPassword;
    private final Locator signInButton;
    private final Locator sendVerificationCodeButton;
    private final Locator verificationCodeInput;
    private final Locator verifyCodeButton;
    private final Locator continueButton;
    private final Locator dashboardLabel;

    // **Constructor**
    public LoginPage(Page page) {
        super();
        this.page = page;
        this.emailCode = new mailosaurAPI();

        this.loginButton = page.locator("(//button[contains(@class,'button_bre-button')])[1]");
        this.enterEmail = page.locator("//input[@id='user_email']");
        this.proceedButton = page.locator("//button[normalize-space()='Proceed']");
        this.enterPassword = page.locator("//input[@id='password']");
        this.signInButton = page.locator("//button[@id='next']");
        this.sendVerificationCodeButton = page.locator("//button[@id='readOnlyEmail_ver_but_send']");
        this.verificationCodeInput = page.locator("//input[@id='readOnlyEmail_ver_input']");
        this.verifyCodeButton = page.locator("(//button[normalize-space()='Verify code'])[1]");
        this.continueButton = page.locator("//button[@id='continue']");
        this.dashboardLabel = page.locator("//label[text()='Dashboard']");
    }

    /**Standard Login Flow */
    public void login(String username, String password) {
        extent.getTest().info("Logging in to the test portal...");
        loginButton.click();
        enterEmail.fill(username);
        proceedButton.click();
        enterPassword.fill(password);
        signInButton.click();
    }

    /**Initiate OTP Verification */
    public void generateOTPLoginCode() {
        sendVerificationCodeButton.click();
        extent.getTest().info("Clicked on the send verification code button");
    }

    /**Retrieve OTP from Mailosaur and enter it */
    public void inputOTPLoginCode() throws Exception {
        String verificationCode = emailCode.getCode();
        OutlookInputVerificationCode(verificationCode);
        OutlookVerifyCodeButtonClick();
        OutlookContinueButtonClick();
        waitForDashboard();
    }

    /**Enter Verification Code */
    public void OutlookInputVerificationCode(String verificationCode) {
        verificationCodeInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        verificationCodeInput.fill(verificationCode);
        extent.getTest().info("Entered verification code: " + verificationCode);
    }

    /**Click the Verify Code Button */
    public void OutlookVerifyCodeButtonClick() {
        page.waitForSelector("#overlay", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        verifyCodeButton.click();
        extent.getTest().info("Clicked the Verify Code button.");
    }

    /**Click the Continue Button */
    public void OutlookContinueButtonClick() {
        page.waitForSelector("#overlay-content", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        continueButton.click();
        extent.getTest().info("Clicked the Continue button.");
    }

    /**Wait for Dashboard */
    public void waitForDashboard() {
        dashboardLabel.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        extent.getTest().info("Dashboard is now displayed.");
    }

    /**Full Login Flow with OTP (Mailosaur Integrated) */
    public void loginWithOTP(String username, String password) throws Exception {
        login(username, password);
    }
}
