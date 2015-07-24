package com.iheart.selenium.localSanity;


import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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



public class ArticlePage extends Page {

	
	
	//For Article Details
	
	@FindBy(id="ihrnp_info") private WebElement ihrnp_info;
	@FindBy(id="ihrnp_track") private WebElement ihrnp_track;
	@FindBy(id="ihrnp_artist") private WebElement ihrnp_artist;
	
	@FindBy(css="#outbrainRecommendedStories > div.moduleContainer > div > div.moduleSubTitle") 
	 	private WebElement recommended;
	@FindBy(css="#outbrain_widget_0 > div > div.ob_container") private WebElement obContainer;
	
	@FindBy(id="=fbcomments") private WebElement fbcomments;
	@FindBy(css="#u_0_2 > div.textwrapper > textarea") private WebElement commentInput;
	
	//ad
	@FindBy(id="masthead_topad")  private WebElement masthead_topad;
	@FindBy(id="google_image_div") private WebElement google_image_div;
	@FindBy(css="#aw0 > img")  private WebElement google_image_div_image;
	
	@FindBy(id="ad300x250") private WebElement ad300x250;
	@FindBy(css="#DARTad300x250 > a > img") private WebElement ad300x250Image;
	
	@FindBy(id="bottomleaderboard") private WebElement bottomleaderboard;
	@FindBy(css="#bottomleaderboard > a > img") private WebElement bottomleaderboardImage;
	
	//E2E
	@FindBy(css="body > div.pageWrapper > div.pageContainer.subpageContent.subpage-details > div.leftContainer > div.detailContent > div:nth-child(8)")
		private WebElement e2eWidget;
	@FindBy(css="body > div.pageWrapper > div.pageContainer.subpageContent.subpage-details > div.leftContainer > div.detailContent > div:nth-child(8) > figure.position1 > a")
		private WebElement e2ePlayerButton;
	
	public void AL_543_articleDetail()
	{
		clickFirstArticle();
		
		checkRecommendedAndAd();
				
	}
	
	
	public void AL_534_eyesToEars()
	{  
		clickFirstArticle();
		
		int count = 0;
		//Make sure that there is only one widget
		List<WebElement> divs = driver.findElements(By.className("e2eWidget"));
		for (WebElement div: divs)
		{
			if (div.getAttribute("style").contains("visibility: visible;"))
				count++;
		}
		
		if (count > 1)
			errors.append("There is more than 1 eye to ear widgets displayed in this article: " + count);
		//Make sure no overlapping
		String display = driver.findElement(By.className("e2eWidget")).getAttribute("style");
		if (!display.contains("display: block"))
			errors.append("Eye to Ear is not displayed correctly.");
		//String e2eStationName = driver.findElement(By.cssSelector("body > div.pageWrapper > div.pageContainer.subpageContent.subpage-details > div.leftContainer > div.detailContent > div:nth-child(8) > figure.position1 > figcaption > div > h5 > a")).getText();
		String e2eStationName = driver.findElements(By.tagName("figCaption")).get(0).findElement(By.tagName("h5")).findElement(By.tagName("a")).getText();
		System.out.println("e2eStationName:"  + e2eStationName);
		//e2ePlayerButton.click();
		//driver.findElements(By.tagName("figure")).get(0).findElement(By.className("position1")).click();
		driver.findElements(By.tagName("figCaption")).get(0).findElement(By.tagName("h5")).findElement(By.tagName("a")).click();
		String windowPrevious = switchWindow();
		//String stationName = driver.findElement(By.cssSelector("#hero > div.hero-content > div > div.profile-info > div > ul > li.station-name > span")).getText();
		String stationName = driver.findElement(By.className("station-name")).getText();
		System.out.println("stationName:"  + stationName);
		if (!e2eStationName.equalsIgnoreCase(stationName))
			errors.append("Wrong radio station is launched from eye to ear.");
	}

	
	public void AL_544_BlogDetails()
	{  
		clickFirstBlog();
		
		//Check title and body
		String title = driver.findElement(By.className("leftContainer")).findElement(By.className("headline")).getText();
		System.out.println("See blog title:" + title);
		if (title.length() < 1)
			errors.append("Blog title doesn't look right. ");
		
		String body =  driver.findElement(By.className("detailContent")).getText();
		System.out.println("See blog body:" + body);
		if (body.length() < 1)
			errors.append("Blog body doesn't look right. ");
		
		checkRecommendedAndAd();
		checkOminitureCall();		
	}
	
	public void AL_547_URLredirect()
	{
		clickOnPhotoGallery();
		
		driver.findElement(By.className("nowPlaying")).findElements(By.tagName("a")).get(0).click();
				
		
        String winHandleBefore = switchWindow();
		
		String url = driver.getCurrentUrl();
		
		if (!parseURL(url))
			Page.getErrors().append("Parameter values are not passed.");
		
	}
	
	
	
	private void checkRecommendedAndAd()
	{
		//Check Recommended
		
		if (!driver.findElement(By.id("outbrainRecommendedStories")).findElement(By.className("moduleSubTitle")).getText().contains("Recommended Stories"))
			errors.append("Recommended module is not shown.");
		
		
		if (recommendedStoryCount() <1 )
			errors.append("No recommended stories.");
		
		if (recommendedStoryCount() >10)
			errors.append("Too many recommended stories in one page.");
		
		//Check comment area
	//	if (fbcomments.getText().contains("Comments"))  //moduleContainer
		try{
		     System.out.println("SEE moduleContainer:" + driver.findElement(By.id("fbcomments")).getText());
		}catch(Exception e)
		{
			//errors.append("Comment section is missing.");
			handleError("Comment section is missing.", "checkRecommendedAndAd");;
			return;
		}
		if (!driver.findElement(By.id("fbcomments")).getText().contains("Comments"))
			errors.append("Comment title is missing.");
		//if(!commentInput.isDisplayed())
		System.out.println("facebook-comments-container:" + driver.findElement(By.id("facebook-comments-container")).getText());
		if(!driver.findElement(By.id("facebook-comments-container")).isDisplayed())
			errors.append("No way to input comment.");
		
		//Check Ad
		
		/*  This is for home page
		try{
			if (!masthead_topad.findElement(By.tagName("div")).isDisplayed())
				errors.append("No ad is displayed in Top LetterHead.");
		}catch(Exception e)
		{
			if (!masthead_topad.findElement(By.tagName("a")).isDisplayed())
				errors.append("No ad is displayed in Top LetterHead.");
		}
		*/
		
		
		try{
			
			if (!driver.findElement(By.className("GoogleActiveViewClass")).isDisplayed())
				errors.append("No ad is displayed in Top LetterHead.");
		}catch(Exception e)
		{
		}
		
		
		//sOMETIMES THIS AD IS EMBEDDED IN AN IFRAME?!
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(2);
			if (ad300x250Image.getAttribute("src").length() < 4)
				errors.append("No ad is displayed in right 300x250.");
		}catch(Exception e)
		{
			driver.switchTo().defaultContent();
			if (!driver.findElement(By.id("ad300x250")).isDisplayed())
				errors.append("No ad is displayed in right 300x250.");
		}
		
		
		checkBottomAdD();
		
	}
	
	
	
	
	
	private void checkOminitureCall()
	{
		//to be implemented
	}
	
	
	public  void clickFirstArticle()
	{
		driver.findElement(By.xpath("//*[@id='homepageFeed']/div[3]/div[3]/div[1]/a[3]")).click();
		
		WaitUtility.sleep(1500);
		//firstArticle.click();
		List<WebElement> feedItems = driver.findElement(By.id("feedItems")).findElements(By.tagName("a"));
		
		for (WebElement item: feedItems)
		{
			item.click();
			break;
		}
	}
	
	private void clickFirstBlog()
	{
		driver.findElement(By.xpath("//*[@id='homepageFeed']/div[3]/div[3]/div[1]/a[2]")).click();
		WaitUtility.sleep(1500);
		List<WebElement> feedItems = driver.findElement(By.id("feedItems")).findElements(By.tagName("a"));
		
		for (WebElement item: feedItems)
		{
			item.click();
			break;
		}
	}
	
	private void clickOnPhotoGallery()
	{
		driver.findElement(By.id("photoGallery")).findElements(By.tagName("a")).get(0).click();
	}
	
	
	
	//Make sure that campid, cid, pname values are passed
	private boolean parseURL(String url)
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
	
	//to be implemented
	private int recommendedStoryCount()
	{  int count = 0;
	   count =  driver.findElement(By.className("OUTBRAIN")).findElement(By.className("ob_container_recs")).findElements(By.tagName("a")).size();
	   System.out.println("Recommended story count: " + count);
	   return count;
	}
		
	
    public  boolean isPlaying()
    {
       try{
    	ihrnp_track.getText();
       }catch(Exception e)
       {
    	   return false;
       }
       
       return true;
       
    }
    
    public  String getPlayingTrack()
    {
    	if (isPlaying())
    	   return ihrnp_track.getText();
    	else 
    		return "";
    }
    
    public  String getPlayingArtist()
    {
    	if (isPlaying())
     	   return ihrnp_artist.getText();
     	else 
     		return "";
    }
    
    
    public void checkBottomAdD()
    {
    	//Seems that sometimes the ad is embedded in iframe, sometime not!
			String adURL ="";
			
			try{
				//first, check it in iframe 3
				adURL = driver.findElement(By.id("bottomleaderboard")).findElement(By.tagName("a")).getAttribute("href");
			}catch(Exception e)
			{
				
			}	
				
			if (adURL.length() < 1)
			{	
				try{
					//first, check it in iframe 3
					adURL = driver.findElement(By.id("bottomleaderboard")).findElement(By.tagName("iframe")).getAttribute("name");
				}catch(Exception e)
				{
					
				}	
			
			}
			
			if (adURL.length() < 1)
			{
				driver.switchTo().defaultContent();
				try{
				  adURL = driver.findElement(By.id("bottomleaderboard")).findElement(By.tagName("a")).getAttribute("href");
				}catch(Exception e)
				{
						
				}
				
				if (adURL.length() < 1)
				{
					driver.switchTo().defaultContent();
					try{
						adURL = driver.findElement(By.id("bottomleaderboard")).findElement(By.tagName("iframe")).getAttribute("name");
					}catch(Exception e)
					{
							
					}
				}
			}
			

			System.out.println("adURL: " + adURL);
    		
			if (adURL.length() < 4)
			{	errors.append("No ad is displayed in the bottom Leader Board.");
				
			}
    }
}
