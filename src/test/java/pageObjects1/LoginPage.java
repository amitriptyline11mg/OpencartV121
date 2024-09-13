package pageObjects1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage3
{ // We extend basepage for every page obj class because it contains webdriver constructor
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy (xpath = "//input[@id='input-email']") WebElement txtEmail;
	@FindBy (xpath = "//input[@id='input-password']") WebElement txtPassword;
	@FindBy (xpath = "//input[@value='Login']") WebElement btnLogin;
	
	public void setEmail(String email){
		txtEmail.sendKeys(email);
	}
	
	public void clearEmail(){
		txtEmail.clear();
	}
	

	
	public void setPassword(String Password){
		txtPassword.sendKeys(Password);
	}
	
	public void clearPassword(){
		txtPassword.clear();
	}
	
	public void clickLogin(){
		btnLogin.click();
	}
}
