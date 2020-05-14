/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the home page
*/

package Project;
import static org.testng.Assert.assertTrue;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.homepageObjects;
import resources.baseClass;

public class HomePageTest extends baseClass{

	public static Logger log = LogManager.getLogger(baseClass.class.getName());

	//Initializing the driver and navigating to the homepage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
        driver.manage().window().maximize();
        log.debug("Window is maximized");
		driver.get(prop.getProperty("homepage"));
		log.info("Navigated to HomePage");
	}
	
	//Checking the presence of popup and removing it from performing further tests
	@Test(groups = {"important"})
	public void PopupPresence() throws InterruptedException {
		homepageObjects homepageobjects = new homepageObjects(driver);
		log.debug("Created instance for the homepageObjects");
		JavascriptExecutor jsExec = (JavascriptExecutor)driver;
		log.info("HomePageTest.PopupPresence");
		
		//Code snippet to get the popup out of the page
		log.info("Waiting for a period of 5 seconds the popup to come alive");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		log.info("Explicit wait for the visibility of the popup");
		wait.until(ExpectedConditions.visibilityOfElementLocated(homepageobjects.findPopup()));
		log.info("Explicit wait for the visibility of 'No Thanks' button on the popup");
		wait.until(ExpectedConditions.elementToBeClickable(homepageobjects.findNoThanks()));

		log.info("Assertion to verify the presence of 'Join Our Newsletter' text");
		log.debug("The text present is: " + homepageobjects.getAlertText().getText());
		Assert.assertEquals(homepageobjects.getAlertText().getText(), "Join Our Newsletter");
		log.debug("Clicking on the 'No Thanks' button to close the popup");
		homepageobjects.getNoThanksbutton().click();
	}
	
	//Validating the title of the page
	@Test
	public void TitleTextValidation() {
		homepageObjects homepageobjects = new homepageObjects(driver);
		log.debug("Created instance for the homepageObjects");
		log.info("HomePageTest.TitleTextValidation");
		String title_text = homepageobjects.getTitle().getText();
		String expected = "FEATURED COURSES";
		
		if(title_text.contains(expected)) {
			log.info("Successfully validated Text Message on Home Page");
			Assert.assertTrue(true);
		}else {
			log.error("Error in validating Text Message on Home Page");
			Assert.assertFalse(true);
		}
	}
	
	//Validating the presence of the navigation bar in the page
	@Test
	public void NavigationBarPresence() {
		homepageObjects homepageobjects = new homepageObjects(driver);
		log.debug("Created instance for the homepageObjects");
		log.info("HomePageTest.NavigationBarPresence");
		log.info("Assertion to verify that the Navigation Bar is displayed");
		Assert.assertTrue(homepageobjects.getNavigationBar().isDisplayed());
	}
	
	//Closing the driver
	@AfterMethod(groups = {"must"})
	public void closeBrowser() {
		log.info("Closing the browser");
		driver.close();
		driver = null;
	}
}