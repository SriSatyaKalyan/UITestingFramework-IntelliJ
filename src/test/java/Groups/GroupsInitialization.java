package Groups;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import resources.baseClass;

import java.io.IOException;

public class GroupsInitialization extends baseClass {

    //Mandatory Step needed to make sure that the logs are shown
    public static Logger log = LogManager.getLogger(baseClass.class.getName());

    @BeforeGroups(groups = {"important"})
    public void openBrowser() throws IOException {
        driver = initializeDriver();
        log.info("Driver is initialized");
        driver.get(prop.getProperty("homepage"));
        log.info("Navigated to HomePage");
    }

    @AfterGroups(groups = {"important"})
    public void closeBrowser() {
        driver.close();
        driver = null;
    }

}
