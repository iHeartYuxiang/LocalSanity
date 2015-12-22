package com.iheart.selenium.localSanity;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Z100HomePage extends Page {
	
	//@FindBy(css="body > div.pageWrapper > div.nowPlayingWrapper.full > div > a > span.cta > span:nth-child(1)")
	@FindBy(css="div.small-8 > span:nth-child(1)")
		private WebElement listenLive;
	
	 public Z100HomePage() {
			
	  }
			
	  public Z100HomePage(WebDriver driver) {
				super(driver);
		}
		
	
	public void listOutLinks()
	{
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Link Count:" + links.size());
		for (WebElement link: links)
			System.out.println(link.getText());
	}
	
	public void testAL_485_listenLive()
	{
		listenLive.click();
		
		String winHandleBefore = driver.getWindowHandle();
		
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		String url = driver.getCurrentUrl();
		
		if (!parseFine(url))
			Page.getErrors().append("Parameter values are not passed.");
		
	}

	//Make sure that campid, cid, pname values are passed
	private boolean parseFine(String url)
	{   //http://www.iheart.com/live/z100-1469/?autoplay=true&pname=1793&campid=play_bar&cid=main.html
		String campid, cid, pname; 
		String[] params = url.split("&");
		
		pname = params[1].split("=")[1];
		campid = params[2].split("=")[1];
		cid = params[3].split("=")[1];
		 
		System.out.println("pname/campid/cid:" + pname + "/" + campid +"/" +  cid);
		
		if (pname != null && campid != null & cid != null)
			return true;
		else 
			return false;
	}
	
	
	
}
