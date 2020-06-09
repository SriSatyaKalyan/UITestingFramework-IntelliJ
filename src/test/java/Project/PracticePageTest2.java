/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the practice page
*/

package Project;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.practicepageObjects2;
import resources.baseClass;

public class PracticePageTest2 extends baseClass{
	
	//Creating instances of PageObjects
	public practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
	
	//Mandatory Step needed to make sure that the logs are shown
	public static Logger log = LogManager.getLogger(baseClass.class.getName());
	
	//Initializing the driver and navigating to the practicepage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException, InterruptedException {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.manage().window().maximize();
		driver.get(prop.getProperty("practicepage"));
		log.info("Navigated to Practice Page2");
	}
	
	//Verifying the title text for the SwitchWindow section
	@Test
	public void getSwitchWindowTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getSwitchWindowTitle");
		
		if(practicepageobjects.getSwitchWindowTitle().getText().contains("Switch Window Example")) {
			log.info("Switch Window Title contains Switch Window Example text");
			Assert.assertTrue(true);
		}else {
			log.error("Switch Window Title DOES NOT contain Switch Window Example text");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if a new window will be opened if the necessary actions are performed
	@Test
	public void OpenNewWindow() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.OpenNewWindow");
		
		practicepageobjects.getWindowButton().click();
		
		//Loop performing the iteration between various window handles and getting the expected title
		for (String handle:driver.getWindowHandles()) {
			log.info("Switching to another window handle");
			
			if(driver.switchTo().window(handle).getTitle().contains(prop.getProperty("expectedtitletext"))) {
				log.info("The title of the page contains the expected text");
				Assert.assertTrue(true);
			}else {
				log.info("The title of the page does not contain the expected text");
				driver.close();
			}	
		}
	}
	
	//Verifying the title text for the SwitchTab section
	@Test
	public void getSwitchTabTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getSwitchTabTitle");
		
		if(practicepageobjects.getSwitchTabTitle().getText().contains("Switch Tab Example")) {
			log.info("Switch Tab Title contains Switch Tab Example text");
			Assert.assertTrue(true);
		}else {
			log.error("Switch Tab Title DOES NOT contain Switch Tab Example text");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if a new tab will be opened when the necessary actions are performed
	@Test
	public void OpenNewTab() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.OpenNewTab");

		log.info("Clicking on the Tab Button");
		practicepageobjects.getTabButton().click();
		
		//Handling the tabs
		Set<String> windowids = driver.getWindowHandles();
		Iterator<String> iterator = windowids.iterator();
		
		String oldwindowid = iterator.next();
		String newwindowid = iterator.next();
		if(driver.switchTo().window(newwindowid).getTitle().contains(prop.getProperty("expectedtabtitle"))) {
			log.info("The title of the new tab contains the expected text");
			Assert.assertTrue(true);
		}else {
			log.error("The title of the new tab does not contain the expected text");
			Assert.assertFalse(true);
		}	
	}
	
	//Verifying the title text for the SwitchAlert section
	@Test
	public void getSwitchAlertTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getSwitchAlertTitle");
		
		if(practicepageobjects.getSwitchAlertTitle().getText().contains("Switch To Alert")) {
			log.info("Switch Alert Title contains Switch To Alert text");
			Assert.assertTrue(true);
		}else {
			log.error("Switch Alert Title DOES NOT contain Switch To Alert text");
			Assert.assertFalse(true);
		}
	}
	
	//Test where we check if the alert box is opening as intended and if it is being dismissed
	@Test
	public void getAlert() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getAlert");

		log.info("Get the alertname property from the Alert Box");
		practicepageobjects.getAlertNameBox().sendKeys(prop.getProperty("alertname1"));

		log.info("Close the Alert Box");
		practicepageobjects.clickAlertBox().click();
		
		if (driver.switchTo().alert().getText().contains(prop.getProperty("alertname1"))) {
			log.info("Dismissing the alert because the alertname property is present");
			driver.switchTo().alert().dismiss();
			Assert.assertTrue(true);
		}else {
			log.error("The alertname property is not present");
			Assert.assertFalse(true);
		}
	}
	
	//Test where we check if the alert box is opening as intended and if it is being confirmed
	@Test
	public void getConfirm() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getConfirm");

		log.info("Get the alertname property from the Alert Box");
		practicepageobjects.getAlertNameBox().sendKeys(prop.getProperty("alertname2"));

		log.info("Close the Alert Box");
		practicepageobjects.clickConfirmBox().click();
		
		if (driver.switchTo().alert().getText().contains(prop.getProperty("alertname2"))) {
			log.info("Dismissing the alert because the alertname property is present");
			driver.switchTo().alert().dismiss();
			Assert.assertTrue(true);
		}else {
			log.error("The alertname property is not present");
			Assert.assertFalse(true);
		}
	}
	
	////Verifying the title text for the Table section
	@Test
	public void getTableTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getTableTitle");
		
		if(practicepageobjects.getWebTableTitle().getText().contains("Web Table")) {
			log.info("The Web Table Title contains the Web Table text");
			Assert.assertTrue(true);
		}else {
			log.error("The Web Table Title contains the Web Table text");
			Assert.assertFalse(true);
		}
	}
	
	//Test validating the total price produced if all the courses are taken
	@Test
	public void getTable() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getTable");
		
		int count = practicepageobjects.getWebTable().findElements(By.cssSelector("td:nth-child(3)")).size();
		int totalprice = 0;
		for (int i=2; i <= count; i++) {
			totalprice += Integer.parseInt(practicepageobjects.getWebTable().findElement(By.cssSelector("tr:nth-child("+i+") td:nth-child(3)")).getText());
		}
		log.info("The total price of all the courses combined is " + totalprice);
		log.info("The expected total price is 235");
	}
	
	//Verifying the title text for the ElementDisplayed section
	@Test
	public void getElementDisplayedTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.getElementDisplayedTitle");
		
		if(practicepageobjects.getElementExampleTitle().getText().contains("Element Displayed")) {
			log.info("The Element contains the Element Displayed text");
			Assert.assertTrue(true);
		}else {
			log.error("The Element DOES NOT contain the Element Displayed text");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if the required element can be displayed
	@Test
	public void elementShow() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.elementShow");

		log.info("Click on the Show Button of the Element");
		practicepageobjects.getShowButton().click();
		if (practicepageobjects.getElementBox().isDisplayed()) {
			log.info("The Element Box is displayed");
			Assert.assertTrue(true);
		}else {
			log.error("The Element Box IS NOT displayed");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if the required element can be hidden
	@Test
	public void elementHide() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.elementHide");

		log.info("Click on the Hide Button of the Element");
		practicepageobjects.getHideButton().click();
		if(practicepageobjects.getElementBox().isDisplayed()) {
			log.info("The Element Box is displayed");
			Assert.assertFalse(true);
		}else {
			log.error("The Element Box IS NOT displayed");
			Assert.assertTrue(true);
		}
	}
	
	//Verifying the title text for the MouseHover section
	@Test
	public void mouseHoverTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.mouseHoverTitle");
		
		if (practicepageobjects.getMouseHoverTitle().getText().contains("Mouse Hover")) {
			log.info("The Mouse Hover Title contains the Mouse Hover text");
			Assert.assertTrue(true);
		}else {
			log.error("The Mouse Hover Title DOES NOT contain the Mouse Hover text");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if the mouse hovering actioncan be performed and a required selection can be made	
	@Test
	public void mouseHoverSelectTop() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.mouseHoverselectTop");
		
		Actions action = new Actions(driver);
		action.moveToElement(practicepageobjects.getMouseButton()).build().perform();	
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.presenceOfElementLocated(practicepageobjects.mousehovertopbutton));
		
		practicepageobjects.getMouseHoverTopButton().click();
        log.info("Selected the Top button and the page has been redirected to the top");
	}
	
	//Test checking if the mouse hovering actioncan be performed and a required selection can be made
	@Test
	public void mouseHoverSelectReload() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.mouseHoverselectReload");
		
		Actions action = new Actions(driver);
		log.info("Using Actions class to click on the Mouse Button");
		action.moveToElement(practicepageobjects.getMouseButton()).build().perform();	
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		log.info("Implicit wait for the presence of the Mouse Hover Load Button");
		wait.until(ExpectedConditions.presenceOfElementLocated(practicepageobjects.mousehoverreloadbutton));

		practicepageobjects.getMouseHoverReloadButton().click();
        log.info("Selected the Reload button and the page has reloaded");
	}
	
	//Verifying the title text for the iFrame section
	@Test
	public void iFrameTitle() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.iFrameTitle");
		
		if (practicepageobjects.getiFrameTitle().getText().contains("Rahul Shetty Academy")) {
			log.info("The i-frame Title contains the Rahul Shetty Academy text");
			Assert.assertTrue(true);
		}else {
			log.error("The i-frame Title DOES NOT contain the Rahul Shetty Academy text");
			Assert.assertFalse(true);
		}
	}
	
	//Test checking if an operation can be performed on the frame
	@Test
	public void iFrameOperation() {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.iFrameOperation");

		log.info("Switch to the i-frame");
		driver.switchTo().frame(practicepageobjects.getiFrame());
//		HomePageTest homepage = new HomePageTest();
//		homepage.TitleTextValidation();

		log.info("Switch to the default content");
		driver.switchTo().defaultContent();

		PracticePageTest2 page = new PracticePageTest2();

		log.info("Using the iFrameTitle function to run the test assertion");
		page.iFrameTitle();
	}
	
	//Test checking if all the links in the page can be opened at a time
	@Test(groups = {"important"})
	public void accessAllLinks() throws InterruptedException {
		practicepageObjects2 practicepageobjects = new practicepageObjects2(driver);
		log.info("PracticePageTest2.accessAllLinks");

		log.info("Getting the number of links on the Practice Page");
		WebElement linksset = practicepageobjects.getLinksBox(); //footer_driver
		int linkscount = practicepageobjects.getNumberofLinks();
		
		for (int i=0; i < linkscount; i++) {
			String openlinkkeys = Keys.chord(Keys.CONTROL, Keys.ENTER);
			log.info("Opening the links using a loop");
			linksset.findElements(By.tagName("a")).get(i).sendKeys(openlinkkeys);
			Thread.sleep(2000);
		}
		
		Set<String> tabset = driver.getWindowHandles();
		Iterator<String> iterator = tabset.iterator();
		
		log.info("The pages will be Practice Page, ApacheJMeter, Appium, SOAPUI and REST API");
		log.info("The iteration has begun - Titles of the links will be printed");
		while(iterator.hasNext()) {
			driver.switchTo().window(iterator.next());
			log.info(driver.getTitle());
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