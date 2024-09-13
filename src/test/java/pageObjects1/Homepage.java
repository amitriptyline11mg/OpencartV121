package pageObjects1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage3{
	
//	POM class: Constructor, Locator, Action

	// 1. Invoking the parent's class constructor
	
	public Homepage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	// 2. Locator
	@FindBy (xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;
	
	@FindBy (xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	
	@FindBy (linkText = "Login") WebElement linkLogin;
	
	
	// Action
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	

}
