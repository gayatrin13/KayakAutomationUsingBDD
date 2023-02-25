package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import config.Locators;
import util.Util;
import util.WaitHandler;

public class SearchResultsPage {

	WebDriver driver;
	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV)
	WebElement firstFlightFound;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.HIDDEN_FLIGHT_DETAILS_DIV)
	WebElement divClicked;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.VALIDATE_AIRPORT_NAMES)
	List<WebElement> originDestinationAirportNames;

//	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.VALIDATE_ORIGIN_AIRPORT_NAME)
//	WebElement validate_origin_airport;
//
//	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.VALIDATE_DESTINATION_AIRPORT_NAME)
//	WebElement validate_destination_airport;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.VALIDATE_FLIGHT_DEPART_TIME)
	WebElement validate_flightDepartTime;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.VALIDATE_FLIGHT_ARRIVAL_TIME)
	WebElement validate_flightArrivalTime;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.FLIGHTS_LIST + Locators.SECTION)
	List<WebElement> flightDetailsSpans;

	@FindBy(xpath = Locators.FIRST_FLIGHT_DIV + Locators.CONNECTING_FLIGHTS)
	List<WebElement> connectingFlights;

	@FindBy(xpath = Locators.CLOSE_POPUP)
	WebElement closePopup;

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectFirstFlight() {
		List<String> windows = new ArrayList<String>(driver.getWindowHandles());
		windows.forEach(s -> System.out.println("windows: " + s));
		// sometimes the search results are opened in new tab
		if (windows.size() > 1) {
			System.out.println("new window opened :");
			driver.switchTo().window(windows.get(1));
		}

		try {
			WaitHandler.waitUntilElementVisible(driver, closePopup);
			if (closePopup != null) {
				System.out.println("closing the popup");
				closePopup.click();
			}
		} catch (Exception e) {
			System.err.println("No popup showed");
		}
//		WaitHandler.waitUntilRefreshed(driver, firstFlightFound);
		WaitHandler.waitUntilElementClickable(driver, firstFlightFound);
		Actions act = new Actions(driver);
		act.moveToElement(firstFlightFound).click().build().perform();
//		Util.javaScriptExecutor(driver, firstFlightFound);
	}

	public void validateFlightOriginCity(String origin) {
		System.out.println("from city " + origin);

		String originAirport = originDestinationAirportNames.get(0).getAttribute("title") + " - "
				+ originDestinationAirportNames.get(0).getText();
//		String originAirport = validate_origin_airport.getAttribute("title") + " - "
//				+ validate_origin_airport.getText();
		System.out.println("validate origin airport : " + originAirport);
		Assert.assertTrue(originAirport.contains(origin), "The actual and expected Origin city doesn't match ");
	}

	public void validateFlightDestinationCity(String destination) {
		System.out.println(" toCity : " + destination);

		String destinationAirport = originDestinationAirportNames.get(1).getAttribute("title") + " - "
				+ originDestinationAirportNames.get(1).getText();
		System.out.println("validate destination airport : " + destinationAirport);

		Assert.assertTrue(destinationAirport.contains(destination),
				"The actual and expected Destination city doesn't match ");

	}

	public void validateDepartureDate(String fromDate) {
//		WaitHandler.waitUntilElementVisible(driver, validate_flightDepartTime);
		String departDate = validate_flightDepartTime.getText().substring(9).trim();
		System.out.println("Departs: " + departDate);

		System.out.println("Before formatting From  :" + fromDate + " Depart Date : " + departDate);
		fromDate = Util.dateFormat(fromDate) + "";
		departDate = departDate.replace(",", "");
		System.out.println("after formatting From  :" + fromDate + " Depart Date : " + departDate);

		Assert.assertTrue(fromDate.startsWith(departDate), "The actual and expected departure date doesn't match ");
	}

	public void validateArrivalDate(String toDate) {

		String arriveDate = validate_flightArrivalTime.getText().substring(9).trim();

		System.out.println("Arrives :" + arriveDate);

		System.out.println("Before formatting From  :" + toDate + " Depart Date : " + arriveDate);
		toDate = Util.dateFormat(toDate) + "";
		arriveDate = arriveDate.replace(",", "");
		System.out.println("after formatting From  :" + toDate + " Depart Date : " + arriveDate);

		Assert.assertTrue(toDate.startsWith(arriveDate), "The actual and expected Arrival date doesn't match ");
	}

	public String logFlightInfo() {
		String flightDetailsInfo = "";
		System.out.println("Connecting flights :" + connectingFlights.size());

		for (WebElement webElement : connectingFlights) {
			System.out.println(" WebElemtn :" + webElement.getText());
			flightDetailsInfo = flightDetailsInfo + webElement.getText() + "#";
		}

		return flightDetailsInfo;
	}
}
