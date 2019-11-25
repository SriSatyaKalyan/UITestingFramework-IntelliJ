/**
 * Author: Satya 
 * Purpose: This file is the baseclass which decides which browser the tests should run on
*/

package resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class baseClass {
	
	public static WebDriver driver; 
	
	public Properties prop;
	
	//Method to initialize driver based on the browser preference
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		//Loop for Deciding which browser the driver should operate on
		if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
//			ChromeDriverManager.getInstance().setup();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

		}else if(browserName.equals("firefox")){	
//			System.setProperty("webdriver.gecko.driver", "C:\\Users\\satya\\Desktop\\Eclipse Workspace\\Selenium Software\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
		}else if (browserName.equals("ie")){
//			System.setProperty("webdriver.ie.driver", "C:\\Users\\satya\\Desktop\\Eclipse Workspace\\Selenium Software\\IEDriverServer.exe");
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
		}else{
            WebDriverManager.phantomjs().setup();
            driver = new PhantomJSDriver();
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
		FileHandler.copy(screenshotfile, new File(System.getProperty("user.dir") + "\\TestFailureScreenshots\\" + nameoftest + ".png"));
	}

	//Method for accessing data through the Excel sheet
	public ArrayList<String> LoginDetailsfromExcelSheet(String dataset) throws IOException, FileNotFoundException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\datasheets.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		int numberofsheets = workbook.getNumberOfSheets();
		ArrayList<String> credentiallist = new ArrayList<String>();

		for (int i = 0; i < numberofsheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("logindetails")) {
				XSSFSheet logindetailssheet = workbook.getSheetAt(i);

				Iterator<Row> rows = logindetailssheet.iterator(); //A sheet is a collection of rows
				Row firstrow = rows.next();

				Iterator<Cell> cell = firstrow.cellIterator();
				int columnchanger = 0;
				int column = 0;

				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("Credentials")) {
						column = columnchanger;
					} else {
						columnchanger++;
					}
				}

				while (rows.hasNext()) {
					Row row = rows.next();
					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(dataset)) {
						Iterator<Cell> credentialrow = row.cellIterator();
						while (credentialrow.hasNext()) {
//							System.out.println("One of the credentials: " + credentialrow.next().getStringCellValue());
							credentiallist.add(credentialrow.next().getStringCellValue());
						}
					}
				}
			}
		}
		return credentiallist;
	}
}