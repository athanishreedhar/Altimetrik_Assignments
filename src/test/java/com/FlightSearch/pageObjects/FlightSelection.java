package com.FlightSearch.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.FlightSearch.testCases.BaseClass;

public class FlightSelection {

	WebDriver ldriver;

	/*
	 * Constructor to initialize the page objects 
	 * 
	 */
	
	public FlightSelection(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	

	/*
	 * WebElements Coming under tests are defined below using @FindBy annotaions
	 * 
	 */
	
	@FindBy(xpath="//ol/li[1]/div[1]/span")
	public List<WebElement> btn_EconomyFlightFare;
	
	
	@FindBy(xpath="//ol/li[2]/div[1]/span")
	public List<WebElement> btn_PremiumFlightFare;
	
	@FindBy(xpath="//ol/li[3]/div[1]/span")
	public List<WebElement> btn_BusinessFlightFare;
	
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	public WebElement btn_Continue;
	
	
	
	@FindBy(xpath="//span[@class = 'fltrt-opt-fares-price-brand-name' and text()='Economy']")
	public WebElement lbel_EconomyFlightFare;
	
	/*
	 * WebElements Coming under tests are defined below using Dynamic function
	 * 
	 */
	public WebElement findEconomyClassFlights(int i) {
		int x=i;
		WebElement ele =ldriver.findElement(By.xpath("//*[contains(@id,'"+x+"')]//ol/li[1]/div[1]/span"));
		return ele;
	}
	
	public WebElement TripDateFromTripDetailsPAge(String ExpectedDate) {
		WebElement ele = ldriver.findElement(By.xpath("//div[@class='src__Box-sc-1sbtrzs-0 fsZetV' and text()='"+ExpectedDate+"']"));
		return ele;
	}
	public void clickContinue() {
		btn_Continue.click();
	}
	
	
	
	
	
	
}
