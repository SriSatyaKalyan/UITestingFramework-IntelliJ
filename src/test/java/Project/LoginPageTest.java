/**
 * Author: Satya 
 * Purpose: The purpose of this file is to have tests which run on the login page
*/

package Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
	public loginpageObjects loginpageobjects = new loginpageObjects(driver);
	public homepageObjects homepageobjects = new homepageObjects(driver);
	
	//Mandatory Step needed to make sure that the logs are shown
	public static Logger log = LogManager.getLogger(baseClass.class.getName());
	
	//Initializing the driver and navigating to the loginpage
	@BeforeMethod(groups = {"must"})
	public void openBrowser() throws IOException {
		driver = initializeDriver();
		log.info("Driver is initialized");
		driver.manage().window().maximize();
		driver.get(prop.getProperty("coursepage"));
		log.info("Navigated to Courses Page");
	}
	
	//Verifying that clicking on the LoginButton navigates us to the LoginPage
	@Test (enabled = false, invocationCount = 1)
	public void LoginButton() throws InterruptedException {
		log.info("LoginPageTest.LoginButton");

		log.info("Clicking on the Login button of the page and verifying that it is redirecting us to the Login Page");
		homepageObjects homepageobjects = new homepageObjects(driver);

		//Sleep because the execution is fast
		Thread.sleep(5000);
		log.info("Clicking on the Login button");
		homepageobjects.getLogin().click();
		log.info("Clicked on the Login button");

		Assert.assertTrue(driver.getCurrentUrl().contains("sso.teachable.com/secure/9521/identity/"));
	}

	@Test(enabled = false, dataProvider = "LoginDetails")
	public void LoginDetails(String username, String password, String text) throws IOException, InterruptedException {
		log.info("LoginPageTest.LoginDetails");

		loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("Navigating to the login page");
		driver.navigate().to("https://sso.teachable.com/secure/9521/identity/login");
		Thread.sleep(5000);
		log.info(text);

		loginpageobjects.emailId().sendKeys(username);
		log.debug(username + " entered the emailId field");
		loginpageobjects.passwordId().sendKeys(password);
		log.debug(password + " entered in the password field");
		log.debug("Click on the Login button");
		loginpageobjects.clickLogin().click();

		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='challenge-stage']/div/label/input")).click();

		log.info("Expected Error Message - Credentials Invalid");
//		log.info("The size of the ErrorMessage is " + loginpageobjects.getErrorMessage().size());
		Assert.assertNotEquals(loginpageobjects.getErrorMessage().size(), 0);
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

	@Test(enabled = false, dataProvider = "getDatafromExcelSheet")
	public void LoginDetailswithExcelSheet(String username, String password, String text) throws IOException {
		log.info("LoginPageTest.LoginDetailswithExcelSheet");
		loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("Navigating to the Login Page");
		driver.navigate().to(prop.getProperty("loginpage"));

		log.info(text);

		log.debug(username + " entered the emailId field");
		loginpageobjects.emailId().sendKeys(username);
		log.debug(password + " entered in the password field");
		loginpageobjects.passwordId().sendKeys(password);
		log.debug("Click on the Login button");
		loginpageobjects.clickLogin().click();

		log.info("Expected Error Message - Credentials Invalid");
//		log.info("The size of the ErrorMessage is " + loginpageobjects.getErrorMessage().size());
		Assert.assertNotEquals(loginpageobjects.getErrorMessage().size(), 0);
	}

    @DataProvider
	public Object[][] getDatafromExcelSheet() throws IOException {
		log.info("LoginPageTest.getDatafromExcelSheet");
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
	@Test(enabled = false)
	public void ForgotPassword() {
		log.info("LoginPageTest.ForgotPassword");
        loginpageObjects loginpageobjects = new loginpageObjects(driver);

        log.info("Navigating to the Login Page");
		driver.navigate().to(prop.getProperty("loginpage"));

		log.info("Click on the Forgot Password button");
		loginpageobjects.getForgotPassword().click();

		log.info("Implicit wait of 30 seconds");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		log.info("Asserting that the getResetPassword error message is present");
		Assert.assertNotEquals(loginpageobjects.getResetPassword().size(), 0);
	}
	
	//Clicking on the ForgotPassword button and checking if the error message is being produced
	@Test(groups = {"important"})
	public void SendInstructions() throws InterruptedException {
        loginpageObjects loginpageobjects = new loginpageObjects(driver);

		log.info("LoginPageTest.SendInstructions");
		driver.navigate().to(prop.getProperty("loginpage"));

		Thread.sleep(3000);
		loginpageobjects.getForgotPassword().click();
		
		Actions action = new Actions(driver);
		//If there is any error, it would be in prop.getProperty function
		loginpageobjects.getresetemailId().sendKeys(prop.getProperty("randomemail"));
        action.moveToElement(loginpageobjects.sendInstructions()).click().build().perform();
		//I am struggling because I want to hit "Enter" instead of the "Send Instructions" button

		log.info("Asserting that the getResetPassword error message is present");
		Assert.assertNotEquals(loginpageobjects.getInvalidEmailError().size(), 0);
	}
	
	//Closing the driver
	@AfterMethod(groups = {"must"})
	public void closeBrowser() {
		driver.close();
		driver = null;
	}
}