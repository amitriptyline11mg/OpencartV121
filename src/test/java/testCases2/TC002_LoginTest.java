package testCases2;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects1.Homepage;
import pageObjects1.LoginPage;
import pageObjects1.MyAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test (groups = {"Sanity", "Master"})
	public void verify_login() {
		System.out.println("Hello");
		logger.info("*** STARTING THE TC002_LOGINTEST ****");
		
		try
		{
			
		// Homepage
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		
		//MyAccount page validation
		MyAccount myAcc = new MyAccount(driver);
		boolean targetPage = myAcc.ifMyAccPageExist();
		
		Assert.assertTrue(targetPage, "You are seeing this message bcz it failed");
		// We can aslo use
		// Assert.assertEquals(targetPage, true, "Msg if test fails");
		
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*** EXECUTION COMPLETE OF THE TC002_LOGINTEST ****");
		
	}

}
