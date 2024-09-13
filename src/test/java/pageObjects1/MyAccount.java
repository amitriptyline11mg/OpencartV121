package pageObjects1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage3 {

	public MyAccount(WebDriver driver) {
		super(driver);
	}

	// 2. locators
	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement msgHeading;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linkLogout;
	// a[@class='list-group-item'][normalize-space()='Logout']
	// 3. Remember not to do validation here

	public boolean ifMyAccPageExist() {
		try {
			return (msgHeading.isDisplayed()); // This will return true or false
		} catch (Exception e) {
			// Incase of any indefinite exception
			return false;
		}
	}

	public void clickLogout() {
		linkLogout.click();
	}

}
