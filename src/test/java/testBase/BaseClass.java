package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {           //BaseClass contains resuable methods
	
	public static WebDriver driver;  //make it public otherwise u cant access in other packages
	public Logger logger;
	public Properties p;
	
	@Parameters({"os","browser"})
	@BeforeClass(groups= {"sanity","regression","master"})
	public void setup(String os, String br) throws IOException {
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties"); //to open file in reading mode  and ./ indicates current project location
		p=new Properties();  //creating properties object
		p.load(file);       //loading the file
		
		logger=LogManager.getLogger(this.getClass());   //this will load the logj42 xml file. LogManager is predefined class , getLogger is the method. this.getClass() makes the logs applicable by gettingS the class
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//platform
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")){
			capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")){
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("Platform not matching");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox":capabilities.setBrowserName("firefox"); break;
			default: System.out.println("Browser name not matching"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		
	
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {	
		switch(br.toLowerCase()) 
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge"  : driver=new EdgeDriver();  break;
		case "firefox":driver=new FirefoxDriver(); break;
		default :System.out.println("Invalid browser"); return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(p.getProperty("appURL"));  //captures the appURL from config.properties file
		driver.manage().window().maximize();		
	}
	
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		driver.quit();
	}
	

	public String randomeString() {
		return (RandomStringUtils.randomAlphabetic(5));  //commons-lang3 library provides a predefined class called RandomStringUtils which has method called randomAlphabetic to produce random string. Based on num provided so many random characters will be returned
		
	}
	
	public String randomeNumber() {
		return (RandomStringUtils.randomNumeric(10));
	}
	
	public String randomeAlphaNumeric() {
	 String randomstring=RandomStringUtils.randomAlphabetic(3);	
	 String randomnum=RandomStringUtils.randomNumeric(3);
	 return (randomstring+"$"+randomnum) ; //concatenating (string + special char + num) as we dont have direct method for alphanumeric
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;  //we r returning the targetfilepath so the screenshot gets attached to report , otherwise ss will only be under screenshots folder

	}
}
