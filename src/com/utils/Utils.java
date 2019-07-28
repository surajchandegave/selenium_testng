/**
 * 
 */
package com.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Administrator
 *
 */
public class Utils {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions action;
	int waitTime = 20;
	
	public Utils(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		wait = new WebDriverWait(driver, waitTime);
		action = new Actions(driver);
	}

	public void clickElement(WebElement ele) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		
		ele.click();
	}
	
	public void waitForPageToLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(expectation);
	}
	
	public void moveToElement(WebElement ele) {
		action.moveToElement(ele).build().perform();
	}
	
	public void takeScreenshot(String location) {
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(location));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterText(WebElement element, String text) {
		// TODO Auto-generated method stub
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(text);
	}
}
