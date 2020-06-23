package Project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.baseClass;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;

public class MiscellaneousPageTest extends baseClass {

    //Mandatory Step needed to make sure that the logs are shown
    public static Logger log = LogManager.getLogger(baseClass.class.getName());

    //Initializing the driver and navigating to the homepage
    @BeforeMethod(groups = {"must"})
    public void openBrowser() throws IOException, InterruptedException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.manage().window().maximize();
        driver.get(prop.getProperty("miscellaneouspage"));
        log.info("Navigated to Miscellaneous Page");
    }

    @Test
    public void dragAndDropFrameElements() throws InterruptedException, AWTException, IOException {
        log.info("dragAndDropFrameElements script is running");
        int X_coordinate1 = 300;
        int Y_coordinate1 = 300;
        int X_coordinate2 = 500;
        int Y_coordinate2 = 300;

        Robot robot = new Robot();
        robot.mouseMove(X_coordinate1, Y_coordinate1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(2000);
        robot.mouseMove(X_coordinate2, Y_coordinate2);

        Thread.sleep(2000);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(5000);

        baseClass base = new baseClass();
        base.getScreenshot("dragAndDropFrameElements");
        log.info("Check the 'dragAndDropFrameElements.png' file in 'TestFailureScreenshots' folder situated in the basepath");
    }

    //Opening all links on the Google home Page
    @Test
    public void openAllGoogleHomePageLinks() throws IOException, AWTException {
        WebDriver driver = initializeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        driver.manage().deleteAllCookies();

        Actions action = new Actions(driver);

        List<WebElement> web = driver.findElements(By.tagName("a"));

        for (int i = 0; i < web.size(); i++) {
            if (web.get(i).isDisplayed()) {
                action.moveToElement(web.get(i)).keyDown(Keys.CONTROL).click().build().perform();
            } else {
                String url = web.get(i).getAttribute("href");
                ((JavascriptExecutor) driver).executeScript("window.open('" + url + "', '_blank');");
            }
        }
    }

    //Closing the driver
    @AfterMethod(groups = {"must"})
    public void closeBrowser() {
        log.info("Closing the driver");
        driver.quit();
        driver = null;
    }
}