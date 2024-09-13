package testCases2;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects1.Homepage;
import pageObjects1.LoginPage;
import pageObjects1.MyAccount;
import testBase.BaseClass;
import utilities3.DataProvidersDemo;


public class TC003B_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProvidersDemo.class, groups = "DataDriven")
	public void verify_loginDDT(String email, String passw, String expc) {

		logger.info(" **** STARTING THE DATA DRIVEN LOGIN TEST CASE *****");

		try {
			// Homepage
			Homepage hp = new Homepage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.clearEmail();
			lp.setEmail(email);
			lp.clearPassword();
			lp.setPassword(passw);

			Thread.sleep(3000);
			lp.clickLogin();

			// MyAccount
			MyAccount myAcc = new MyAccount(driver);
			boolean targetPage = myAcc.ifMyAccPageExist();

			// Validation
			// Data is valid: Login successful (Passed) - Logout
			// Data is valid: Login Failed (Failed)

			if (expc.equalsIgnoreCase("Valid")) {

				if (targetPage == true) {

					myAcc.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			// Data is Invalid: Login successful (Failed) - Logout
			// Data is Invalid: Login Failed (Passed)

			if (expc.equalsIgnoreCase("Invalid")) {

				if (targetPage == true) {
					myAcc.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info(" **** ENDING THE DATA DRIVEN LOGIN TEST CASE *****");
	}

}
