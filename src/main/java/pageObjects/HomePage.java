package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import config.Locators;
import util.Util;
import util.WaitHandler;

public class HomePage {

	WebDriver driver;
	//// div[@class='JyN0-name-row']

	@FindBy(xpath = Locators.TO_CITY)
	WebElement toCity;

	@FindBy(xpath = Locators.FROM_CITY)
	WebElement fromCity;

	@FindBy(xpath = Locators.START_DATE)
	WebElement startDate;

	@FindBy(xpath = Locators.END_DATE)
	WebElement endDate;

	@FindBy(css = Locators.ORIGIN_AIRPORT_LIST)
	WebElement originAirportItems;

	@FindBy(css = Locators.DESTINATION_AIRPORT_LIST)
	WebElement desinationAirportItems;

	@FindBy(xpath = Locators.COMPARE_CHECKBOX)
	List<WebElement> compareCheckbox;

	@FindBy(xpath = Locators.SEARCH_BTN)
	WebElement searchBtn;

	@FindBy(xpath = Locators.CLOSE_SIGN_IN_POPUP)
	List<WebElement> closeSignInPopup;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFromCity(String city, String fromNearbyAirport) {
		// if the nearby airtport is provided, split the string using -
//		closeSignInPopup();

		fromCity.sendKeys(city);
		WaitHandler.waitUntilElementClickable(driver, originAirportItems);
		originAirportItems.click();
		fromCity.click();

		if (fromNearbyAirport != "" && fromNearbyAirport != null) {
			WebElement nearByAirport = driver.findElement(By.xpath(Locators.NEARBY_ORIGIN_AIRPORT_LIST));
			WaitHandler.waitUntilElementVisible(driver, nearByAirport);
			List<WebElement> airportList = nearByAirport.findElements(By.tagName("li"));
			System.out.println(airportList.size() + " " + fromNearbyAirport);

			if (airportList.size() > 0) {
				for (WebElement li : airportList) {
					System.out.println("*");
					if (li.getText().contains(fromNearbyAirport)) {
						System.out.println("matching airport found: " + fromNearbyAirport);
						Actions action = new Actions(driver);
						action.moveToElement(li);
						li.click();
						break;
					}
				}
			}
		}
	}

	public void enterToCity(String city, String toNearbyAirport) {

		toCity.sendKeys(city);
		WaitHandler.waitUntilElementClickable(driver, desinationAirportItems);
		desinationAirportItems.click();
		toCity.click();

		if (toNearbyAirport != "" && toNearbyAirport != null) {
			WebElement nearByAirport = driver.findElement(By.xpath(Locators.NEARBY_DESTINATION_AIRPORT_LIST));
			WaitHandler.waitUntilElementVisible(driver, nearByAirport);
			List<WebElement> airportList = nearByAirport.findElements(By.tagName("li"));
			System.out.println(airportList.size() + " " + toNearbyAirport);
			if (airportList.size() > 0) {
				for (WebElement li : airportList) {
					System.out.println(" *");
					if (li.getText().contains(toNearbyAirport)) {
						System.out.println("matching airport found: " + toNearbyAirport);
						Actions action = new Actions(driver);
						action.moveToElement(li);
						li.click();
						break;
					}
				}
			}
		}

	}

	public void clearForm() {
		fromCity.clear();
		try {
			driver.findElement(By.xpath(Locators.CLEAR_CITY_FIELDS)).click();
		} catch (Exception e) {
			System.err.println("From city textbox is empty");
		}
		toCity.clear();
	}

	public void enterFromDate(String fromDateStr, String toDateStr) {
		// Selceting from date
		startDate.click();
		String fromDateSpitted[] = fromDateStr.split("-");
		String fromdateYearMonth = fromDateSpitted[0] + "-" + fromDateSpitted[1];

		WebElement fromDate = driver.findElement(By.xpath(
				Locators.DATE_XPATH_PART1 + fromdateYearMonth + Locators.DATE_XPATH_PART2 + fromDateSpitted[2] + "']"));
		WaitHandler.waitUntilElementClickable(driver, fromDate);
		fromDate.click();

		// Selecting To Date
		String toDateSpitted[] = toDateStr.split("-");
		String todateYearMonth = toDateSpitted[0] + "-" + toDateSpitted[1];
		System.out.println(todateYearMonth + " ");
		WebElement toDate = driver.findElement(By.xpath(
				Locators.DATE_XPATH_PART1 + todateYearMonth + Locators.DATE_XPATH_PART2 + toDateSpitted[2] + "']"));
		WaitHandler.waitUntilElementClickable(driver, toDate);
		toDate.click();

	}

	public void closeSignInPopup() {
		WebElement closeSignInPopup = WaitHandler.fluentWait(driver, By.xpath(Locators.CLOSE_SIGN_IN_POPUP));
		closeSignInPopup.click();
	}

	public void clickSearch() {
		Actions action = new Actions(driver);
		// Deselect Compare Checkbox
		System.out.println("compare chcekbox>>>>>> " + compareCheckbox.size());
		if (compareCheckbox.size() > 0) {
			if (compareCheckbox.get(0).isSelected()) {
				Util.javascriptUncheckBox(driver, compareCheckbox.get(0));
			} else {
				System.out.println("Compare checkbox is disabled");
			}
		} else {
			System.out.println("Compare checkbox doesnot exists");
		}

		Util.javaScriptExecutor(driver, searchBtn);

	}

}
