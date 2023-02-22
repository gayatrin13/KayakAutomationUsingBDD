package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.WebDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.SearchResultsPage;

public class SearchFlightPageSteps {

	HomePage hPage;
	WebDriver driver;
	SearchResultsPage searchPage;
	String fromCity;
	String toCity;
	String fromDate;
	String toDate;
	String flightDetails = "";

	@Given("The {string} browser is open")
	public void the_browser_is_open(String browser) {
//		drFactory = new WebDriverFactory();
//		drFactory.setDriver(browser);
//		if (browser.equalsIgnoreCase("firefox"))
//			driver = ThreadGuard.protect(new FirefoxDriver());
//		else if (browser.equalsIgnoreCase("Edge"))
//			driver = ThreadGuard.protect(new EdgeDriver());
//		else
//			driver = ThreadGuard.protect(new ChromeDriver());
//
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		WebDriverFactory.setDriver(browser);
		driver = WebDriverFactory.getDriver();
	}

	@Given("User enters website {string}")
	public void user_enters_website(String string) {

		driver.get(string);

	}

	@Then("the webpage should load")
	public void the_webpage_should_load() {
		// test if the title of the page is "Kayak"
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("KAYAK"));
	}

	@When("User enters {string} and {string} cities")
	public void user_enters_origin_and_destination_cities(String fromCity, String toCity) {
		hPage = new HomePage(driver);
		String fromNearbyAirport = "";
		String toNearbyAirport = "";
		if (fromCity.split("-").length > 1) {
			this.fromCity = fromCity.split("-")[0];
			fromNearbyAirport = fromCity.split("-")[1];
		} else {
			this.fromCity = fromCity;
			System.out.println("From>> Nearby airport not provided");
		}
		hPage.clearForm();
		hPage.enterFromCity(this.fromCity, fromNearbyAirport);
		if (toCity.split("-").length > 1) {
			this.toCity = toCity.split("-")[0];
			toNearbyAirport = toCity.split("-")[1];
		} else {
			this.toCity = toCity;
		}
		hPage.enterToCity(this.toCity, toNearbyAirport);

	}

	@When("User selects departure and return dates")
	public void select_departure_and_return_dates(DataTable table) {

		String fromDate = table.cell(1, 0);
		String toDate = table.cell(1, 1);

		this.fromDate = fromDate;
		this.toDate = toDate;

		hPage.enterFromDate(fromDate, toDate);
	}

	@Then("Click Search")
	public void click_search() {
		hPage.clickSearch();
	}

	@When("User selects a flight from the results")
	public void user_selects_a_flight_from_the_results() {
		searchPage = new SearchResultsPage(driver);
		searchPage.selectFirstFlight();
		System.out.println("Step DONE>> User selects a flight from the results!!");
	}

	@Then("Validate Origin airport is  same as the one entered in the main screen")
	public void validate_origin_airport_is_same_as_the_one_entered_in_the_main_screen() {
		searchPage.validateFlightOriginCity(fromCity);
		System.out.println("Step DONE>> Validate Origin airport is  same as the one entered in the main screen");

	}

	@Then("Validate  Destination airport is same as the one entered in the main screen")
	public void validate_destination_airport_is_same_as_the_one_entered_in_the_main_screen() {
		searchPage.validateFlightDestinationCity(toCity);
		System.out.println("Step DONE>> Validate Destination airport is  same as the one entered in the main screen");

	}

	@Then("Validate departure date is same as the one entered in the main screen")
	public void validate_departure_date_is_same_as_the_one_entered_in_the_main_screen() {
		searchPage.validateDepartureDate(fromDate);
		System.out.println("Step DONE>> Validate departure date is  same as the one entered in the main screen");

	}

	@Then("Validate arrival date is same as the one entered in the main screen")
	public void validate_arrival_date_is_same_as_the_one_entered_in_the_main_screen() {
		searchPage.validateArrivalDate(toDate);
		System.out.println("Step DONE>> Validate arrival date is  same as the one entered in the main screen");

	}

	@Then("log all the flight details to the reports")
	public void log_all_the_flight_details_to_the_reports() {
		flightDetails = searchPage.logFlightInfo();
		System.out.println("Step DONE>> log all the flight details to the reports");

	}

	@AfterStep
	public void addScreenShot(Scenario sc) {
		if (sc.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			sc.attach(screenshot, "image/png", "image");
		}
//		sc.log();
	}

	@After
	public void logInfoAfterScenario(Scenario sc) {
		System.out.println("**************After SCenario ********");
		if (flightDetails != "") {
			String[] str = flightDetails.split("#");
			for (String string : str) {
				String str2[] = string.split("\n");
				for (String string2 : str2) {
					sc.log(string2);
				}
				sc.log("***********");
			}
		}
//		driver.close();
	}
}
