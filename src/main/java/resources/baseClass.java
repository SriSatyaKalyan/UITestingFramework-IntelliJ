/**
 * Author: Satya 
 * Purpose: This file is the baseclass which decides which browser the tests should run on
*/

package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;

public class baseClass {
	
	public static WebDriver driver; 
	
	public Properties prop;
	
	//Method to intialize driver based on the browser preference
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\satya\\Downloads\\UITestingFramework\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		//Loop for Deciding which browser the driver should operate on
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\satya\\Desktop\\Eclipse Workspace\\Selenium Software\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")){	
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\satya\\Desktop\\Eclipse Workspace\\Selenium Software\\geckodriver.exe");
			driver = new FirefoxDriver();		
		}else {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\satya\\Desktop\\Eclipse Workspace\\Selenium Software\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();			
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	
	}
	
	//Method to generate random strings of words for various email names
	public String[] generateRandomWords(int numberOfWords)
	{
	    String[] randomStrings = new String[numberOfWords];
	    Random random = new Random();
	    for(int i = 0; i < numberOfWords; i++)
	    {
	        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
	        for(int j = 0; j < word.length; j++)
	        {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings[i] = new String(word);
	    }
	    return randomStrings;
	}
	
	//Method to get screenshot and provide path as to where the screenshots should be saved
	public void getScreenshot(String nameoftest) throws IOException {
		File screenshotfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshotfile, new File("C:\\Users\\satya\\Downloads\\UITestingFramework\\TestFailureScreenshots\\" + nameoftest + ".png"));
	}
}

