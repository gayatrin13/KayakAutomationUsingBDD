package util;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import config.Locators;

public class Util {
	public static void javaScriptExecutor(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].scrollIntoView();", element);
		jsExe.executeScript("arguments[0].click();", element);

	}

	public static void javascriptUncheckBox(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
		jsExe.executeScript("arguments[0].scrollIntoView();", element);
		jsExe.executeScript("arguments[0].click();", element);
		jsExe.executeScript("arguments[0].setAttribute('value','false');", element);
		jsExe.executeScript("arguments[0].setAttribute('aria-checked','false');", element);
		System.out.println("javascriptUncheckBox() :" + element.getAttribute("value"));
	}

//	public static void javascriptSetHiddenToFalse(WebDriver driver, WebElement element) {
//		JavascriptExecutor jsExe = (JavascriptExecutor) driver;
//		jsExe.executeScript("arguments[0].scrollIntoView();", element);
//		jsExe.executeScript("arguments[0].setAttribute('aria-hidden','false');", element);
//		System.out.println("set hidden to false thru javascript");
//	}

	public static Date dateFormat(String dateStr) {

//		String dateString = "2023-03-13";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println("dateFormat()" + date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date dateFormat2(String dateStr) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE,MMM dd");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
			System.out.println("date format 2:" + date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(date);
		return date;
	}

	public static void elementScreenshot(WebElement element, String Filename) {
		File srcFile = element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("./target/" + Filename + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static WebElement retryFindElement(WebDriver driver, By locator) {
		WebElement element = null;
		int retry = 0;
		while (retry <= 3) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			retry++;
		}
		return element;
	}

	public static void main(String[] args) {
		String date1 = dateFormat("2023-03-13") + "";
		String date = "Mon, Mar 13";
		System.out.println(date1);
		date = date.replace(",", "");
		System.out.println(date + " " + date1);

		Assert.assertTrue(date1.startsWith(date));
//		dateFormat2(date);

	}

}
