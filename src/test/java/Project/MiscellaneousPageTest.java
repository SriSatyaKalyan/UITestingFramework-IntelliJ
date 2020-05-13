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
        log.info("Navigated to SignUp Page");
    }

    @Test
    public void mouseHoverSelectReload() throws InterruptedException, AWTException {

        int Xcoordinate1 = 300, Ycoordinate1 = 300;
        int Xcoordinate2 = 500, Ycoordinate2 = 300;

        Robot robot = new Robot();
        robot.mouseMove(Xcoordinate1, Ycoordinate1);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(2000);
        robot.mouseMove(Xcoordinate2, Ycoordinate2);

        Thread.sleep(2000);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);
    }

    //Closing the driver
    @AfterMethod(groups = {"must"})
    public void closeBrowser() {
        log.info("Closing the driver");
        driver.quit();
        driver = null;
    }
}