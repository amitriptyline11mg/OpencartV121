package testCases2;
import testBase.BaseClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects1.AccountRegistrationPage;
import pageObjects1.Homepage;


public class TC001_AccountRegistrationTest extends BaseClass{
	

	@Test (groups = {"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("** Starting the Test Case **");
		try {
		// Creating the obj of HomePage POModel class
		Homepage hp = new Homepage(driver);
		
		hp.clickMyAccount();
		logger.info("** Clicked on My Account button **");
		
		hp.clickRegister();
		logger.info("** Clicked on Register button **");
		
		
		// Creating the obj of AccountRegistrationPage POModal class	
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		
		/*
		// This is harcoded data
		regPage.setFirstName("Amit01");
		regPage.setLastName("Rawat");
		regPage.setEmail("amit01rawat@gmail.com");
		regPage.SetTelephone("009220002");
		regPage.SetPassword("pass1234");
		regPage.SetConfirmPassword("pass1234");
		*/
		
		logger.info("** Providing registration detail **");
		regPage.setFirstName(randomString(4).toUpperCase());
		regPage.setLastName(randomString(5).toUpperCase());
		regPage.setEmail(randomString(5) + "@gmail.com");
		regPage.SetTelephone(randomNumber(10));
		
		String RandomPassword = randomAlphanumeric();		
		regPage.SetPassword(RandomPassword);
		regPage.SetConfirmPassword(RandomPassword);

		
		// Click actions
		regPage.clickCheckPolicy();
		regPage.clickContinueBtn();
		
		logger.info("** Validating expected message**");
		String confMsg = regPage.getConfirmationMsg();
		//Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		
		if(confMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e){
			Assert.fail();
		}
		
		logger.info("*** THE TEST TC001_AccountRegistration is PASSED ****");
		
	}
	


}
