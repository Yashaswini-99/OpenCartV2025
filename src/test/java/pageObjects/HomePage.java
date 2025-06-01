package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);          //super keyword is used to invoke immediate parent constructor here, ie constructor from BasePage class	
	}
	
	
	//locators
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;
	
    @FindBy(xpath="//a[normalize-space()='Login']")
    WebElement lnkLogin;
	
    //Action methods
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		lnkLogin.click();	}


}
