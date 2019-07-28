package com.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.Utils;

import junit.framework.Assert;

public class Practice1 {
	
	/*
	 * Using JUnit/ WebDriver, open http://www.shoppersstop.com in Firefox: (10 points)
		Click on ‘Brands’ from the menu
		Click on ‘Haute Curry' and capture a screenshot of the resulting page.
		Verify if the page title is ‘Haute Curry|Shoppers Stop’.
		Verify the text “Start Something New”
		In the “FOLLOW US” section, print the URL that the Pinterest logo points to.
	 */

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Automation\\workspace\\Practice\\drivers\\geckodriver.exe");
		
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		WebDriver driver = new FirefoxDriver(options);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\workspace\\Practice\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		
		driver.quit();
	}*/
	WebDriver driver;
	Utils utils;
	@Before
	public void setUp() {
		System.setProperty("webdriver.gecko.driver", "C:\\Automation\\workspace\\Practice\\drivers\\geckodriver.exe");
		
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		utils = new Utils(driver);
		driver.get("http://www.shoppersstop.com");
	}
	
	@Test
	public void verifyHauteCurry() throws InterruptedException {
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'BRANDS')]"));
		utils.clickElement(ele);
		System.out.println("Clicked on Brands");
		
		WebElement el = driver.findElement(By.xpath(".//div[@class='row']"));
		el.click();
		
		WebElement ele1 = driver.findElement(By.xpath(".//span[text()='Haute Curry']/..//div[@class='brand_logos']"));
		utils.moveToElement(ele1);
		System.out.println("Moved to Haute Curry");
		
		WebElement ele2 = driver.findElement(By.xpath(".//span[text()='Haute Curry']/..//a//span[text()='View Now']"));
		utils.clickElement(ele2);
		System.out.println("Clicked Haute Curry");
		
		utils.waitForPageToLoad();
		utils.takeScreenshot("C:\\Automation\\HauteCurry.jpg");
		
		System.out.println(driver.getTitle());
		Assert.assertEquals("Haute Curry Bags | Haute Curry Ladies Footwear | Shoppers Stop | Shoppers Stop", driver.getTitle());
		//assertEquals("Haute Curry|Shoppers Stop", driver.getTitle());
		
		WebElement ele3 = driver.findElement(By.xpath(".//footer/div[@class='add-new']/p"));
		utils.moveToElement(ele3);
		System.out.println(ele3.getText());
		Assert.assertEquals("Should match text", "Start Something New", ele3.getText());
		
		WebElement ele4 = driver.findElement(By.xpath(".//*[contains(text(),'Follow Us')]/..//li[@class='pinterest-icon']/a"));
		System.out.println(ele4.getAttribute("href"));
		
		System.out.println("TEST COMPLETE");
	}
	
	
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
