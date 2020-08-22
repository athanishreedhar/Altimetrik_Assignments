package com.FlightSearch.testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.FlightSearch.pageObjects.FlightSelection;
import com.FlightSearch.pageObjects.SearchFlight;


public class TC_BookFlight_001 extends BaseClass {
	
	FlightSelection SelectFlight = new FlightSelection(driver);
	SearchFlight flightSearch = new SearchFlight(driver);
	
	public String ExpTripDate = "Sat - Sep 12th, 2020";
	public String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	@Test(dataProvider="flightDetails")
	public void BookFlight_for_OneWayTrip(String FromCity,String ToCity,int day,int month,int year) throws InterruptedException, IOException {
		
		/*
		 * Below code is to Select the flight by providing the details as below
		 * type of trip ,
		 * Where from journey starts
		 * Which Place journey ends
		 * jWhich date the flight is required 
		 * 
		 */
		flightSearch.SelectFlight(FromCity, ToCity, day, month, year);
		SelectFlight.clickContinue();
		waitForElement(SelectFlight.lbel_EconomyFlightFare, driver);
		
		/*
		 * Below code is to get Fares of all Economy class flights displayed on page 
		 * and store in to array with name EconomySort[]
		 * 
		 */
		int allEconomyFlightFare = SelectFlight.btn_EconomyFlightFare.size();
		int EconomySort[] = new int[allEconomyFlightFare-1];
		int tempFare;
		for(int economyFlight =0;economyFlight<allEconomyFlightFare;economyFlight++) {
			int flightFare = Integer.parseInt(SelectFlight.findEconomyClassFlights(economyFlight+1).getText());
			EconomySort[economyFlight] = flightFare ;	
		}
		/*
		 * Below code is to sort the Economy class flight fares in Ascending order
		 * So that in later method we will have to select the first appearing flight
		 * fare as its most cheaper
		 * 
		 */
		for(int economyFlight =0;economyFlight<allEconomyFlightFare;economyFlight++) {
			for(int economyFlightcompare =economyFlight+1;economyFlightcompare<allEconomyFlightFare;economyFlightcompare++) {
				tempFare = EconomySort[economyFlight];
				EconomySort[economyFlight] = EconomySort[economyFlightcompare];
				EconomySort[economyFlightcompare] =tempFare;
			}
		}
		
		/*
		 * Below code is to display the Economy class flight fares sorted in Ascending 
		 * order as its an one of the task in assignment given
		 * 
		 */
		for(int economyFlight =0;economyFlight<allEconomyFlightFare;economyFlight++) {
			System.out.println(EconomySort[economyFlight]);
		}
		
		
		/*
		 * Below code is to Get the first cheapFlight and click select it will 
		 * navigate to next page and control will wait till Continue button is enabled and visible 
		 * making sure next page is loaded completely
		 * 
		 */
		WebElement cheapFlight = SelectFlight.findEconomyClassFlights(0);
		cheapFlight.click();
		waitForElement(SelectFlight.btn_Continue, driver);
		
		/*
		 * Below code is to Get the Trip date from Trip date web page which is checkout page
		 * And verify the actual trip date with expected date
		 */
		WebElement TripdateFromTripDetailsPage = SelectFlight.TripDateFromTripDetailsPAge(ExpTripDate);
		String actualTrpDate = TripdateFromTripDetailsPage.getText();
		Assert.assertEquals(actualTrpDate, ExpTripDate);
		
		/*
		 * Below code is to take a screen shot of the checkout page which is trip details page 
		 * and quit the browser as its one of the requirements of the task to be performed to 
		 * complete the assignment 
		 */
		captureScreen(driver, timeStamp+"TripDetails");
		tearDown();
	}
	
	
	
}


