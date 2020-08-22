package com.FlightSearch.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchFlight {
	
WebDriver ldriver;
	
	/*
	 * Constructor to initialize the page objects 
	 * 
	 */
	
	public SearchFlight(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	/*
	 * WebElements Coming under tests are defined below using @FindBy annotaions
	 * 
	 */
	
	@FindBy(xpath="//h2[contains(@class,'main-content-hdr')]//label[1]")
	WebElement rad_Roundtrip;
	
	@FindBy(xpath="//h2[contains(@class,'main-content-hdr')]//label[2]")
	WebElement rad_OneWaytrip;
	
	@FindBy(xpath="//h2[contains(@class,'main-content-hdr')]//label[3]")
	WebElement rad_Multitrip;
	
	@FindBy(xpath="//input[@name='from1']")
	WebElement txt_FromCity;
	
	@FindBy(xpath="//input[@name='to1']")
	WebElement txt_ToCity;
	
	@FindBy(xpath="//div[@id='dates']")
	WebElement btn_Selectdate;
	
	@FindBy(xpath="//button[text()='Search Flights']")
	WebElement btn_SearchFlight;
	
	@FindBy(xpath="//button[@id='signUpButton']/following::span[1]")
	WebElement btn_NoThanks;
	
	
	/*
	 * WebElement of calendar can be selected using below method (its an Element locator customized function
	 * 
	 */	
	public WebElement Select_Date(int day , int month,int year) {
		WebElement ele =ldriver.findElement(By.xpath("//td[@data-month='"+month+"' and  @data-year='"+year+"']/a[text()='"+day+"']"));
		return ele;
	}
	
	/*
	 * Actions performed on the webElements defined in this page object class as below
	 */	
	
	public void click_RoundTrip() {
		rad_Roundtrip.click();
		
	}
	
	public void click_OneWayTrip() {
		rad_OneWaytrip.click();
	}
	
	public void click_MultiTrip() {
		rad_Multitrip.click();
	}
	public void enter_FromCityName(String FromCity) {
		txt_FromCity.sendKeys(FromCity);
	}
	
	public void enter_ToCityName(String ToCity) {
		txt_ToCity.sendKeys(ToCity);
	}
	
	public void click_SearchFlight() {
		btn_SearchFlight.click();
		
	}
	
	public void click_NoThanks() {
		btn_NoThanks.click();
	}
	
	public void click_Selectdate() {
		btn_Selectdate.click();
	}
	
	
	/*
	 * Method to represent the specific scenario
	 * 
	 */	
	public  void SelectFlight(String FromCity,String ToCity,int day,int month,int year) {
		click_OneWayTrip();
		enter_FromCityName(FromCity);
		enter_ToCityName(ToCity);
		click_Selectdate();
		Select_Date(day, month, year);
		click_SearchFlight();
	}
}
