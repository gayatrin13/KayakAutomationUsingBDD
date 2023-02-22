package config;

public class Locators {

	/* Home Page Object Repository */
	public static final String TO_CITY = "//input[@aria-label='Flight destination input']";
	public static final String FROM_CITY = "//input[@aria-label='Flight origin input']";
	public static final String START_DATE = "//span[@aria-label='Start date calendar input']/span[@class='sR_k-value']";
	public static final String END_DATE = "//span[@aria-label='End date calendar input']/span[@class='sR_k-value']";

	public static final String DATE_XPATH_PART1 = "//div[@data-month='";
	public static final String DATE_XPATH_PART2 = "']/descendant::div[@role='button' and text()='";

	public static final String ORIGIN_AIRPORT_LIST = "ul#flight-origin-smarty-input-list li";
	public static final String DESTINATION_AIRPORT_LIST = "ul#flight-destination-smarty-input-list li";

	public static final String COMPARE_CHECKBOX = "//div[contains(text(),'Compare vs.')]/parent::div/following-sibling::div/descendant::input";
	public static final String SEARCH_BTN = "//button[@aria-label='Search']";

	public static final String NEARBY_ORIGIN_AIRPORT_LIST = "//ul[@id='flight-origin-smarty-input-list']";

	public static final String NEARBY_DESTINATION_AIRPORT_LIST = "//ul[@id='flight-destination-smarty-input-list']";
	public static final String CLOSE_SIGN_IN_POPUP = "//div[contains(@aria-label,'Close')]";

	/* Search Results Page Object Repository */
	public static final String FIRST_FLIGHT_DIV = "//div[@id='searchResultsList']/descendant::div[@data-resultid][2]";
	public static final String HIDDEN_FLIGHT_DETAILS_DIV = "/descendant::div[contains(@class,'o-C7') and @aria-hidden]";
	public static final String VALIDATE_FLIGHT_DEPART_TIME = "/descendant::span[contains(text(),'Depart')]";

	public static final String VALIDATE_FLIGHT_ARRIVAL_TIME = "/descendant::span[contains(text(),'Return')]";
	public static final String VALIDATE_ORIGIN_AIRPORT_NAME = "/descendant::div[contains(@id,'leg-0-origin')]"; // "/descendant::span[@dir='auto']";
	public static final String VALIDATE_DESTINATION_AIRPORT_NAME = "/descendant::div[contains(@id,'leg-0-destination')]"; // "/descendant::span[@dir='auto']";

	public static final String CLOSE_POPUP = "//div[@aria-label='Close' and @role='button']/child::span";
	public static final String CLEAR_CITY_FIELDS = "//div[@class='vvTc-item-close']";

	public static final String FLIGHTS_LIST = "/descendant::ol[@class='flights']";
	public static final String SECTION = "/descendant::div[contains(@class,'section times')]";

	public static final String CONNECTING_FLIGHTS = "/descendant::div[@data-flightnumber]";
	public static final String AIRLINE = "/descendant::div[contains(@class,'carrier-text')]";
	public static final String AIRPORTS = "/descendant::span[contains(@class,'station')]";
	public static final String TIMINGS = "/descendant::span[contains(@class,'time')]";

}
