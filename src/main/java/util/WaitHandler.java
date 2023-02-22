package util;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHandler {
	static long seconds = 5;
	static long interval = 2;

	public static WebElement waitUntilElementClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilRefreshed(WebDriver driver, WebElement elmt) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(elmt)));
	}


	public static WebElement waitUntilElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement fluentWait(WebDriver driver, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		WebElement element = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {

				WebElement linkelement = driver.findElement(locator);
				System.out.println("Element found After FluentWait");
				return linkelement;
			}
		});

		return element;
	}
}
