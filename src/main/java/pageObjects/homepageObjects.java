/**
 * Author: Satya 
 * Purpose: This file is the POM for homepage
*/

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class homepageObjects {
	
	public WebDriver driver;
	By loginbutton = By.xpath("//span[contains(text(),'Login')]");
	By title       = By.xpath("//a[@class='main-btn']");
	By navigationbar = By.xpath("//div[@class='navigation']");
	By popup        =  By.xpath("//div[@class='sumome-react-wysiwyg-popup-animation-group']");
	By nothanks = By.xpath("//div[@class='sumome-react-wysiwyg-component sumome-react-wysiwyg-popup-button sumome-react-wysiwyg-button'] //*[contains(text(),'THANKS')]");
	By nothanksbutton    = By.xpath("//button[text()='NO THANKS']");
	By alerttextheading  = By.xpath("//div[@class='sumome-react-wysiwyg-move-handle'] //*[contains(text(),'Newsletter')]");
	
	public homepageObjects(WebDriver driver) {
		this.driver = driver;		
	}
	
	public WebElement getLogin() {
		return driver.findElement(loginbutton);
	}
	
	public WebElement getTitle() {
		return driver.findElement(title);
	}
	
	public WebElement getNavigationBar() {
		return driver.findElement(navigationbar);
	}
	
	public By findPopup() {
		return popup;
	}
	
	public WebElement findNoThanks() {
		return driver.findElement(nothanks);
	}
	
	public WebElement getNoThanksbutton() {
		return driver.findElement(nothanksbutton);
	}

	public WebElement getAlertText() {
		return driver.findElement(alerttextheading);
	}

}