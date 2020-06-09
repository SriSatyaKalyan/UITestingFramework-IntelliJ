/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the practice page
*/

package Project;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import pageObjects.practicepageObjects1;
import resources.baseClass;

public class PracticePageTest1 extends baseClass {
	
	//Creating instances of PageObjects
	//This public instantiation is not working as I am asked to instantiate them individually again in the individual test methods
//	public practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
	
	//Mandatory Step needed to make sure that the logs are shown
	public static Logger log = LogManager.getLogger(baseClass.class.getName());
	
	//Initializing the driver and navigating to the practicepage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException, InterruptedException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.manage().window().maximize();
		driver.get(prop.getProperty("practicepage"));
		log.info("Navigated to Practice Page");
	}
	
    //Validating the title text 
	@Test(groups = {"important"})
	public void TitleTextValidation() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.TitleTextValidation");
		
		if(practicepageobjects.getTitle().getText().contains("Practice Page")) {
			log.info("Successfully validated Text Message on Practice Page");
			Assert.assertTrue(practicepageobjects.getTitle().getText().contains("Practice Page"));
		}else {
			log.error("Error in validating Text Message on Practice Page");
			Assert.assertFalse(true);
		}
	}
	
	//Validating the title on the RadioButtons section
	@Test
	public void RadioButtonTitle() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.RadioButtonExample");

		if (practicepageobjects.getRadioButtonTitle().getText().contains("Radio Button")) {
			log.info("Radio Button Title contains the Radio Button text");
			Assert.assertTrue(true);
		}else {
			log.error("Radio Button Title DOES NOT contain the Radio Button text");
			Assert.assertFalse(true);
		}
	}
	
	//Pressing Radio buttons
	@Test
	public void RadioButtons() throws IOException {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.RadioButtons");

		int radiobuttonnumber = Integer.parseInt(prop.getProperty("radiobutton"));
		
		log.info("The radio button to be selected is " + radiobuttonnumber);
		
		if (radiobuttonnumber == 1) {
			log.debug("Radio Button Number is 1");
			if (!practicepageobjects.getRadioButton1().isSelected()) {
				log.debug("Clicking on the Radio Button Number numbered 1");
				practicepageobjects.getRadioButton1().click();
			}
		}else if (radiobuttonnumber == 2) {
			log.debug("Radio Button Number is 2");
			if (!practicepageobjects.getRadioButton2().isSelected()) {
				log.debug("Clicking on the Radio Button Number numbered 2");
				practicepageobjects.getRadioButton2().click();
			}
		}else {
			log.debug("Radio Button Number is 3");
			if (!practicepageobjects.getRadioButton3().isSelected()) {
				log.debug("Clicking on the Radio Button Number numbered 3");
				practicepageobjects.getRadioButton3().click();
			}
		}
	
		baseClass base = new baseClass();
		base.getScreenshot("Radiobuttons");
		log.info("Check the 'Radiobuttons.png' file in 'TestFailureScreenshots' folder situated in the basepath");
	}
	
	//Validating the title text for Suggestion Class section
	@Test
	public void SuggestionClassTitle() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.SuggestionClassTitle");

		if (practicepageobjects.getSuggestionClass().getText().contains("Suggession Class")) {
			log.info("Suggestion Class contains the text Suggession Class");
			Assert.assertTrue(true);
		}else {
			log.error("Suggestion Class DOES NOT contain the text Suggession Class");
			Assert.assertFalse(true);
		}
	} 

	//Test performed to get the suggested country. Check the test carefully for better understanding
	@Test
	public void getSuggestedCountry() throws InterruptedException {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.getSuggestedCountry");
		log.info("Change the 'countrykeyword' and 'country' property values in the properties file accordingly");
		practicepageobjects.getSuggestionBox().sendKeys(prop.getProperty("countrykeyword"));

		int suggestionlistsize = driver.findElements(By.xpath("//*[@id='ui-id-1']/li")).size();
		System.out.println("The number of suggestions are: " + suggestionlistsize);
		
		int i = 0;
		while(i < suggestionlistsize){
			if (practicepageobjects.getCountrySuggestor().get(i).getText().contains(prop.getProperty("country"))) {
				log.info("The country was found in the dropbox menu with the provided keyword");
				practicepageobjects.getCountrySuggestor().get(i).click();
				Assert.assertEquals(practicepageobjects.getSuggestionBox().getAttribute("value") , prop.getProperty("country"));
				break;
			}else {
				i++;
				if(i == suggestionlistsize){
					log.error("The country provided was not found");
					Assert.assertFalse(true);
				}
			}
		}
	}
		
	//Verifying the title text for the DropDown Example section
	@Test
	public void DropDownExampleTitle() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.DropDownExampleTitle");

		if (practicepageobjects.getDropDownTitle().getText().contains("Dropdown Example")) {
			log.info("DropDown title contains the Dropdown Example ");
			Assert.assertTrue(true);
		}else {
			log.error("DropDown title DOES NOT contain the Dropdown Example ");
			Assert.assertFalse(true);
		}
	}
	
	//Test performing the selection of DropDown options and selecting one of them
	@Test
	public void selectDropDownOptions() throws InterruptedException {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.selectDropDownOptions");

		practicepageobjects.hitDropdownmenu().click();
		
		for (int i=0;i<3; i++) {
			if(practicepageobjects.selectDropdownOptions().get(i).getText().contains(prop.getProperty("optionnumber"))) {
				log.info("Selected the required option");
				practicepageobjects.selectDropdownOptions().get(i).click();
				log.debug("Inserted an explicit wait of 2 seconds");
				Thread.sleep(2000);
				Assert.assertTrue(true);
			}
		}
	}
	
	//Verifying the title text for the Checkbox Example section
	@Test
	public void CheckboxExampleTitle() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.CheckboxExampleTitle");

		if (practicepageobjects.getCheckboxTitle().getText().contains("Checkbox")) {
			log.info("Checkbox Title contains the Checkbox text");
			Assert.assertTrue(true);
		}else {
			log.error("Checkbox Title DOES NOT contain the text");
			Assert.assertFalse(true);
		}
	}
	
	//Selecting a particular checkbox which is random number between 1,2 and 3
	@Test
	public void selectCheckbox() {
		practicepageObjects1 practicepageobjects = new practicepageObjects1(driver);
		log.info("PracticePageTest.selectCheckbox");

		if(prop.getProperty("checkboxnumber").equals("1")) {
			log.debug("Checkbox number is 1");
			practicepageobjects.getCheckbox1().click();
		}else if(prop.getProperty("checkboxnumber").equals("2")) {
			log.debug("Checkbox number is 2");
			practicepageobjects.getCheckbox2().click();
		}else {
			log.debug("Checkbox number is 3");
			practicepageobjects.getCheckbox3().click();
		}
	}
	
	//Closing the driver
	@AfterMethod(groups = {"must"})
	public void closeBrowser() {
		log.info("Closing the driver");
		driver.close();
		driver = null;
	}	
}