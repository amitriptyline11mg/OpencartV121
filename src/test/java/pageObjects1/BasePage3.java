package pageObjects1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage3 {
	
	WebDriver driver;
	
	public BasePage3(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This base page is parent of all the page obj classes because the constructor is same everywhere so we will store it here
}
