package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Log;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	

	@Test(groups={"regression","master"})
	public void verify_account_registration() {
		try {
		logger.info("******TC001_AccountRegistrationTest is started*******");
//to access the methods from HomePage class we r creating a object. also need to import since it is in diff package. 
//as soon as we create object it will invoke constructor automatically so we need to send driver as constructor is expecting driver argument						
		HomePage hp=new HomePage(driver);  
		hp.clickMyAccount();
		logger.info("My Account is clicked");
		hp.clickRegister();
		logger.info("Register is clicked");
		
		logger.info("Updating customer details");
		AccountRegistrationPage register=new AccountRegistrationPage(driver);
		register.setFirstName(randomeString().toUpperCase());
		register.setLastName(randomeString().toUpperCase());
		register.setEmail(randomeString()+"@gmail.com");
		register.setTelephone(randomeNumber());
		String pswd=randomeAlphaNumeric();
		register.setPassword(pswd);
		register.setConfirmPassword(pswd);
		register.clickAgree();
		register.clickContinue();

		logger.info("Validating confirmation message");
		//validation(u have to use assertion to pass/fail a case)
		if(register.getConfirmationMsg().equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test case has failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}}
		catch(Exception e) //if any step fails then it throws exception so then we need to fail the case.
		{
			Assert.fail();
		}
	}
	
	
}
