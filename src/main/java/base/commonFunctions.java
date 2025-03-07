package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.testng.Assert;
import org.testng.Reporter;
import java.nio.file.Paths;
import java.util.*;

public class commonFunctions {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public commonFunctions() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        if (browser == null) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false)); // Change to false for debugging
                    //.setArgs(Arrays.asList("--start-maximized")));
        }
        if (context == null) {
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        }
        if (page == null) {
            page = context.newPage();
        }
    }

    public static Page getPage() {
        return page;
    }

    /**Navigate to URL */
    public void navigateTo(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    /**Click an element */
    public void click(String selector) {
        page.locator(selector).click();
    }

    /**Enter text */
    public void enterText(String selector, String text) {
        page.locator(selector).fill(text);
    }

    /**Select dropdown by visible text */
    public void selectFromDropdown(String selector, String option) {
        page.locator(selector).selectOption(option);
    }

    /**Wait for an element to be visible */
    public void waitForVisibility(String selector, int timeout) {
        page.locator(selector).waitFor(new Locator.WaitForOptions().setTimeout(timeout * 1000));
    }

    /**Wait for an element to be hidden */
    public void waitForInvisibility(String selector, int timeout) {
        page.waitForCondition(() -> !page.locator(selector).isVisible(), new Page.WaitForConditionOptions().setTimeout(timeout * 1000));
    }

    /**Scroll into view */
    public void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    /**Get text from an element */
    public String getText(String selector) {
        return page.locator(selector).textContent();
    }

    /**Take a screenshot */
    public void takeScreenshot(String filePath) {
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(filePath)).setFullPage(true));
    }

    /**Close the browser */
    public void closeBrowser() {
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    /**Assert if an element is displayed */
    public void assertElementVisible(String selector) {
        boolean isVisible = page.locator(selector).isVisible();
        Assert.assertTrue(isVisible, "Element not visible: " + selector);
    }

    /**Assert if an element contains specific text */
    public void assertElementText(String selector, String expectedText) {
        String actualText = page.locator(selector).textContent();
        Assert.assertEquals(actualText, expectedText, "Text mismatch for element: " + selector);
    }

    /**Generate a random integer */
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
