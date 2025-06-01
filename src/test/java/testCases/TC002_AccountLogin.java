package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.*;
import testBase.BaseClass;

public class TC002_AccountLogin extends BaseClass {
	
	@Test(groups={"sanity","master"})
	public void verify_AccountLogin() {
		
		logger.info("*******starting TC002_AccountLogin********");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean status=myacc.myAccountDisplayed();
		Assert.assertTrue(status);
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*******finished TC002_AccountLogin********");
	}
	

}
