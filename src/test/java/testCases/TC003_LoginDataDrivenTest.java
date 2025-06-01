package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class, groups="datadriven")
	public void verify_LoginDataDriven(String email, String password, String res) {
		logger.info("*******starting TC003_LoginDataDrivenTest********");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetPage=myacc.myAccountDisplayed();
		
		/* logic
		 * valid creds-->login successful   = pass -->logout
		 *            -->login unsuccessful = fail
		 *       
		 * Invalid creds -->login successful = fail   ->logout
		 *               -->login unsuccessful = pass 
		 */
		
		if(res.equalsIgnoreCase("Valid")) {
			if(targetPage==true)  //true means login is successful
			{
				myacc.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(res.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {
				myacc.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*******finished TC003_LoginDataDrivenTest********");
		}
		
	}


