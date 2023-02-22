package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public static void setDriver(String browser) {
		if (browser.equalsIgnoreCase("firefox"))
			driver.set(new FirefoxDriver());
		else if (browser.equalsIgnoreCase("Edge"))
			driver.set(new EdgeDriver());
		else
			driver.set(new ChromeDriver());

		driver.get().manage().window().maximize();
		driver.get().manage().deleteAllCookies();
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void closeBrowser() {
		driver.get().close();
		driver.remove();
	}
}
