/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the login page
*/

package Project;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.homepageObjects;
import pageObjects.loginpageObjects;
import resources.baseClass;

public class LoginPageTest extends baseClass {
	
	//Creating instances of PageObjects
    //This public instantiation is not working as I am asked to instantiate them individually again in the individual test methods
//	public loginpageObjects loginpageobjects = new loginpageObjects(driver);
//	public homepageObjects homepageobjects = new homepageObjects(driver);
	
	//Mandatory Step needed to make sure that the logs are shown
	public static Logger log = LogManager.getLogger(baseClass.class.getName());
	
	//Initializing the driver and navigating to the loginpage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.get(prop.getProperty("homepage"));
		log.info("Navigated to Home Page");
	}
	
	//Verifying the presence of LoginButton
	@Test
	public void LoginButton() {
		loginpageObjects loginpageobjects = new loginpageObjects(driver);
		homepageObjects homepageobjects = new homepageObjects(driver);

		log.info("LoginPageTest.LoginButton");
		
		//Code snippet to get the popup out of the page
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homepageobjects.findPopup()));
		//System.out.println("Popup visible");
		wait.until(ExpectedConditions.elementToBeClickable(homepageobjects.findNoThanks()));
		homepageobjects.getNoThanksbutton().click();
		
		loginpageobjects.selectLogin().click();
		String title_text = loginpageobjects.getTitleText().getText();
		//System.out.println(title_text);
		String expected = "Log In to QaClickAcademy";
		
		if (title_text.contains(expected)) {
			Assert.assertTrue(true);
		}else {
			Assert.assertFalse(true);
		}
	}

	@Test(dataProvider = "LoginDetails")
	public void LoginDetails(String username, String password, String text) throws IOException {
        loginpageObjects loginpageobjects = new loginpageObjects(driver);

	    log.info("LoginPageTest.LoginDetails");
		driver.navigate().to(prop.getProperty("loginpage"));
		
		log.info("LoginPageTest.LoginDetails");
		log.info(text);
		
		loginpageobjects.emailId().sendKeys(username);
		loginpageobjects.passwordId().sendKeys(password);
		loginpageobjects.clickLogin().click();
		
		if(loginpageobjects.getErrorMessage().size() > 0) {
			log.info("Expected Error Message - Credentials Invalid");
			Assert.assertTrue(true);
		}
	}
	
	//Creating a DataProvider class which provides different parameters to the tests
	@DataProvider
	public Object[][] LoginDetails() {
		Object data[][] = new Object[2][3];
		
		//1st row
		data[0][0]      = "pratyusha321@gmail.com";
		data[0][1]      = "123456";
		data[0][2]      = "Restricted User";
		
		//2nd Row
		data[1][0]      = "batman@gotham.com";
		data[1][1]      = "123456";
		data[1][2]      = "UnRestricted User";
		
		return data;
	}

	@Test(dataProvider = "getDatafromExcelSheet")
	public void LoginDetailswithExcelSheet(String username, String password, String text) throws IOException {
		loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("LoginPageTest.LoginDetails");
		driver.navigate().to(prop.getProperty("loginpage"));

		log.info("LoginPageTest.LoginDetails");
		log.info(text);

		loginpageobjects.emailId().sendKeys(username);
		loginpageobjects.passwordId().sendKeys(password);
		loginpageobjects.clickLogin().click();

		if(loginpageobjects.getErrorMessage().size() > 0) {
			log.info("Expected Error Message - Credentials Invalid");
			Assert.assertTrue(true);
		}
	}

    @DataProvider
	public Object[][] getDatafromExcelSheet() throws IOException {
		baseClass base = new baseClass();

        ArrayList<String> dataset1 = base.LoginDetailsfromExcelSheet("DataSet1");
        ArrayList<String> dataset2 = base.LoginDetailsfromExcelSheet("DataSet2");
        ArrayList<String> dataset3 = base.LoginDetailsfromExcelSheet("DataSet3");
        String exceldata[][] = new String[3][3];
        
        //1st row
        exceldata[0][0]      = dataset1.get(1);
        exceldata[0][1]      = dataset1.get(2);
        exceldata[0][2]      = dataset1.get(3);

        //2nd Row
        exceldata[1][0]      = dataset2.get(1);
        exceldata[1][1]      = dataset2.get(2);
        exceldata[1][2]      = dataset2.get(3);

        //3rd Row
        exceldata[2][0]      = dataset3.get(1);
        exceldata[2][1]      = dataset3.get(2);
        exceldata[2][2]      = dataset3.get(3);

        return exceldata;
    }
	
	//Verifying the presence of "Reset Password" button after clicking on ForgotPassword icon
	@Test
	public void ForgotPassword() {
        loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("LoginPageTest.ForgotPassword");
		driver.navigate().to(prop.getProperty("loginpage"));
		
		loginpageobjects.getForgotPassword().click();
		
		if(loginpageobjects.getResetPassword().size() > 0) {
			log.info("Reset Password button is present");
			Assert.assertTrue(true);
		}else {
			log.error("Reset Password button is not present");
			Assert.assertFalse(true);
		}
	}
	
	//Clicking on the ForgotPassword button and checking if the error message is being produced
	@Test(groups = {"important"})
	public void SendInstructions() throws InterruptedException {
        loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("LoginPageTest.SendInstructions");
		driver.navigate().to(prop.getProperty("loginpage"));
		
		loginpageobjects.getForgotPassword().click();
		
		Actions action = new Actions(driver);
		//If there is any error, it would be in prop.getProperty function
		loginpageobjects.getresetemailId().sendKeys(prop.getProperty("randomemail"));
        action.moveToElement(loginpageobjects.sendInstructions()).click().build().perform();
		//I am struggling because I want to hit "Enter" instead of the "Send Instructions" button
		if (loginpageobjects.getInvalidEmailError().size() > 0) {
			log.info("The invalid error message is present");
			Assert.assertTrue(true);
		}else {
			log.error("The invalid error message is not present");
			Assert.assertFalse(true);
		}
	}
	
	//Closing the driver
	@AfterMethod(groups = {"must"})
	public void closeBrowser() {
		driver.close();
		driver = null;
	}
}