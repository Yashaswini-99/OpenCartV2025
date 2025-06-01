package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement txtMyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean myAccountDisplayed() {
		try {
		return txtMyAccount.isDisplayed();  //if My account text displayed then login successful so it'll return true
		}
		catch(Exception e) {
			return false;                   //if My account text not displayed then login unsuccessful so it'll return false
		}
	}
	
		public void clickLogout() {
			lnkLogout.click();
		}
	

}
