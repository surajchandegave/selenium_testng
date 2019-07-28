/**
 * 
 */
package com.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utils.Utils;

import junit.framework.Assert;

/**
 * @author Administrator
 *
 */
public class Practice2 {
	WebDriver driver;
	Utils utils;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\workspace\\Practice\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		utils = new Utils(driver);
		driver.get("http://www.imdb.com/");
		driver.manage().window().maximize();
	}
	
	@Test
	public void verifyIMDBMovie() {
		WebElement searchBox = driver.findElement(By.id("navbar-query"));
		utils.enterText(searchBox, "Gangs of New York");
		
		WebElement searchButton = driver.findElement(By.id("navbar-submit-button"));
		utils.clickElement(searchButton);
		
		WebElement movieLink = driver.findElement(By.xpath(".//td[contains(.,'Gangs of New York')][contains(.,'2002')]/a"));
		utils.clickElement(movieLink);
		
		WebElement movieInfo = driver.findElement(By.xpath(".//div[@class='subtext']"));
		String movieInfoString = movieInfo.getText();
		System.out.println(movieInfoString);
		String mpaaRating = movieInfoString.split("|")[0].trim();
		System.out.println(mpaaRating);
		
		Assert.assertEquals("R", mpaaRating);
		System.out.println(movieInfoString.split("|")[1]);
		String movieRuntime = movieInfoString.split("|")[1].trim();
		System.out.println(movieRuntime);
		//System.out.println(convertToMinutes(movieRuntime));
		
		System.out.println("TEST COMPLETE");
	}
	
	public int convertToMinutes(String time) {
		String hr = time.split(" ")[0];
		String min = time.split(" ")[1];
		System.out.println(hr + " " + min);
		
		int hrint = Integer.parseInt(hr.replaceAll("hr", ""));
		int minint = Integer.parseInt(min.replaceAll("min", ""));
		return hrint*60+minint;
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
