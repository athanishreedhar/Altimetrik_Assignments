package com.FlightSearch.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.FlightSearch.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public static WebDriver driver;
	
	public static Logger logger;
	
	/*
	 * Below code is to initialize the driver and launch the url in maximized window mode
	 * 
	 */
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{			
		logger = Logger.getLogger("FlightSearch");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	/*
	 * Below code is to switch to frame using frame element
	 * 
	 */
	public static void switchToFrame(WebElement ele) {
		System.out.println("==============================I AM There================================");
		driver.switchTo().frame(ele);
		System.out.println("==============================I AM switched================================");
	}
	/*
	 * Below code is to Switch back to main window
	 * 
	 */
	public static void swithToMainWin() {
		driver.switchTo().defaultContent();
		System.out.println("==============================I AM switched to main win================================");
	}
	
	/*
	 * Below code is to close the browser sessions opened after test method execution
	 * 
	 */
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	/*
	 * Below code is to Capture the screen 
	 * 
	 */
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	/*
	 * Below code is to return the random String
	 * 
	 */
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	/*
	 * Below code is to return the random number
	 * 
	 */
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	/*
	 * Below code is to Explicit wait (WebDriver wait ), basically wait for
	 * element on the page to be visible and enabled
	 * 
	 */
	public WebElement waitForElement(WebElement btn_Continue,WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		return wait.until(ExpectedConditions.presenceOfElementLocated((By) btn_Continue));
	}
}
