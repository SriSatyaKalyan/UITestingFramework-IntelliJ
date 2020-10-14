package Project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.baseClass;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MiscellaneousPageTest extends baseClass {

    //Mandatory Step needed to make sure that the logs are shown
    public static Logger log = LogManager.getLogger(baseClass.class.getName());

    //Initializing the driver and navigating to the homepage
    @BeforeMethod(groups = {"must"})
    public void openBrowser() throws IOException, InterruptedException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.manage().window().maximize();
    }

    @Test
    public void dragAndDropFrameElements() throws InterruptedException, AWTException, IOException {
        driver.get(prop.getProperty("miscellaneouspage"));
        log.info("Navigated to Miscellaneous Page");

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

    @Test
    public void openAMEXGoBlock() throws IOException, InterruptedException {
        driver.get("https://www.americanexpress.com/en-us/business/payment-solutions/employee-spending/");

        //Because I receive a Stale Element Reference Exception here, I am using a Thread.sleep() which makes sure that I am waiting for
        //the element to completely load in the page
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[2]//div[1]//div[2]//div[1]//div[1]//div[1]")).click();

        //Instead of directly grabbing the text from the page using a specific XPath like 'contains(text(),...)', I verified the 'American Express Go' text in the page and then
        //traversed through the HTML to get the text
        String amexGotext = driver.findElement(By.xpath("//h2[contains(text(),'American Express Go')]/parent::div/parent::div/following-sibling::div/div[1]")).getText();
        Assert.assertEquals(amexGotext, "This fast, flexible virtual payment method allows employees to make purchases. Virtual Cards are sent to users via a mobile app, and can be used online immediately.");

        //The hardcoded paths used here are generally kept in POM pages and then sent over to here

        //In the interview, I was handling the exception using Explicit waits and hence the error. I could have also used try-catch block but
        //I wanted to avoid it as the time was limited
    }

//    	Closing the driver
    @AfterMethod(groups = {"must"})
    public void closeBrowser() {
        log.info("Closing the driver");
        driver.quit();
        driver = null;
    }

}