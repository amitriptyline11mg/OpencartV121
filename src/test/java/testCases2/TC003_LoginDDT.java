package testCases2;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects1.Homepage;
import pageObjects1.LoginPage;
import pageObjects1.MyAccount;
import testBase.BaseClass;
import utilities3.ExcelUtility;

/*
  Data is valid: Login successful (Passed) - Logout
  Data is valid: Login Failed (Failed)
  
  Data is Invalid: Login successful (Failed) - Logout
  Data is Invalid: Login Failed  (Passed)

*/
public class TC003_LoginDDT extends BaseClass {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
	    String path = "C:\\Users\\Amit Rawat\\Automation\\Eclipse\\SeleniumWebDriver\\OpencartV121\\testData\\Opencart_LoginpData.xlsx";

	    ExcelUtility xlUtil = new ExcelUtility(path);

	    int totalrows = xlUtil.getRowCount("Sheet1");
	    int totalcols = xlUtil.getcellCount("Sheet1", 0); // Ensure you are using the correct row number for column count

	    String[][] logindata = new String[totalrows - 1][totalcols]; // Assuming first row is headers

	    for (int i = 1; i < totalrows; i++) { // Start from 1 to skip headers
	        for (int j = 0; j < totalcols; j++) {
	            logindata[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);
	        }
	    }

	    return logindata;
	}


	
	@Test (dataProvider = "LoginData") // Getting dataprovider from diff class
	public void verify_loginDDT(String email, String passw, String expc) {

		System.out.println("helllop");
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
			
			Thread.sleep(2000);
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
	
	/*
	 	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class) // Getting dataprovider from diff class
	public void verify_loginDDT(String email, String passw, String expc) {

		logger.info(" **** STARTING THE DATA DRIVEN LOGIN TEST CASE *****");

		
		try {
			// Homepage
			Homepage hp = new Homepage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(passw);
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
	 */

}
