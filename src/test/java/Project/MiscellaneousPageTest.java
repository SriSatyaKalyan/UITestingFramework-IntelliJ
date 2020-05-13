package Project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.baseClass;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.IOException;

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

    //Closing the driver
    @AfterMethod(groups = {"must"})
    public void closeBrowser() {
        log.info("Closing the driver");
        driver.quit();
        driver = null;
    }
}