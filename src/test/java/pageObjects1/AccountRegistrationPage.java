package pageObjects1;

//import java.time.Duration;

//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage3{
	
	// 1. Constructor
	public AccountRegistrationPage (WebDriver driver) {
		super(driver);
	}

	
	// 2. Locating elements
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement checkPolicy;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	
	// Once we click continue we will recieve the confirmation message
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	

	// 3. Action methods
	
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void SetTelephone (String tele){
		txtTelephone.sendKeys(tele);
	}
	public void SetPassword(String password){
		txtPassword.sendKeys(password);
	}
	public void SetConfirmPassword(String password){
		txtConfirmPassword.sendKeys(password);
	}
	
	public void clickCheckPolicy() {
		checkPolicy.click();
	}
	
	public void clickContinueBtn() {
		btnContinue.click();
		
		// Sol2
//		btnContinue.submit();
		
		// Sol3
//		Actions act = new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();
		
		// Sol4
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", btnContinue);
		
		// Sol5
//		btnContinue.sendKeys(Keys.RETURN);
		
		
		// Sol6
//		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	
	
	// We will only return the Confirmation msg
	// We do not validate anything in POM class, that's the job for test class
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e){
			return (e.getMessage());
		}
	}

	

}
