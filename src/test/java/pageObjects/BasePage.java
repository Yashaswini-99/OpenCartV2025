package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage   //BasePage class consists of the constructor code which has to be included in all Page object class to achieve resusability.                       //So all the Page object classes shld extend BasePage
{
	WebDriver driver;
	
	public BasePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		}

}
