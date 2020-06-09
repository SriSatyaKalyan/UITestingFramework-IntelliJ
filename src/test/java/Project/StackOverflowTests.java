package Project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.baseClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class StackOverflowTests extends baseClass{

    //Mandatory Step needed to make sure that the logs are shown
    public static Logger log = LogManager.getLogger(baseClass.class.getName());

    @Test
    public void openAll() throws IOException, AWTException {
        WebDriver driver = initializeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        driver.manage().deleteAllCookies();

        Actions action = new Actions(driver);

        List<WebElement> web = driver.findElements(By.tagName("a"));

        for (int i = 0; i < web.size(); i++) {
            if(web.get(i).isDisplayed()){
                action.moveToElement(web.get(i)).keyDown(Keys.CONTROL).click().build().perform();
            }else{
                String url = web.get(i).getAttribute("href");
                ((JavascriptExecutor)driver).executeScript("window.open('" + url + "', '_blank');");
            }
        }

    }

}
