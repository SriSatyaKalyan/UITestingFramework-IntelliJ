/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the home page
*/

package Project;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.homepageObjects;
import resources.baseClass;

public class HomePageTest extends baseClass{

	//This public instantiation is not working as I am asked to instantiate them individually again in the individual test methods
//	public homepageObjects homepageobjects = new homepageObjects(driver);
	
	//Mandatory Step needed to make sure that the logs are shown
	public static Logger log = LogManager.getLogger(baseClass.class.getName());
		
	//Initializing the driver and navigating to the homepage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get(prop.getProperty("homepage"));
		log.info("Navigated to HomePage");
	}
	
	//Checking the presence of popup and removing it from performing further tests
	@Test(groups = {"important"})
	public void PopupPresence() throws InterruptedException {
		homepageObjects homepageobjects = new homepageObjects(driver);
		JavascriptExecutor jsExec = (JavascriptExecutor)driver;
		log.info("HomePageTest.PopupPresence");
		
		//Code snippet to get the popup out of the page
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homepageobjects.findPopup()));
		wait.until(ExpectedConditions.elementToBeClickable(homepageobjects.findNoThanks()));

		Assert.assertEquals(homepageobjects.getAlertText().getText(), "Join Our Newsletter");
		homepageobjects.getNoThanksbutton().click();
//		log.info(driver.switchTo().alert().getText());
	}
	
	//Validating the title of the page
	@Test
	public void TitleTextValidation() {

		homepageObjects homepageobjects = new homepageObjects(driver);
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
		log.info("HomePageTest.NavigationBarPresence");
		Assert.assertTrue(homepageobjects.getNavigationBar().isDisplayed());
		log.info("Validated presence of Navigation Bar");
	}
	
	//Closing the driver
	@AfterMethod(groups = {"must"})
	public void closeBrowser() {
		driver.close();
		driver = null;
	}
}