package com.iheart.selenium.localSanity;


import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
	//@FindBy(id="ihrnp_track") private WebElement ihrnp_track;
	@FindBy(className="np-track") private WebElement ihrnp_track;
	//@FindBy(id="ihrnp_artist") private WebElement ihrnp_artist;
	@FindBy(className="np-artist") private WebElement ihrnp_artist;
	
	@FindBy(css="a.filter-option:nth-child(2)") private WebElement blogLink;
	
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
	
	//public void AL_543_articleDetail()
	public void AL_495_articleDetail()
	{
		clickFirstArticle();
		
		checkRecommendedAndAd();
				
	}
	
	
	public void AL_189_eyesToEars()
	{  
		
		
		//clickFirstArticle();
		
		//first, let's get a page that contains ETE
		driver.get("http://radio1057.iheart.com/articles/rock-news-104648/ian-anderson-visiting-real-life-jethro-13907211");
		
		int count = 0;
		//Make sure that there is only one widget
		
		List<WebElement> figures = driver.findElement(By.className("e2eWidget")).findElements(By.tagName("figure"));
		
		if (figures.size() != 2)
			errors.append("Eye To Ear is not working: " + count);
		
		String e2eStationName = driver.findElement(By.cssSelector(".position1 > figcaption:nth-child(2) > div:nth-child(1) > h5:nth-child(1) > a:nth-child(1)")).getText();
		System.out.println("e2eStationName:"  + e2eStationName);
		
		driver.findElement(By.cssSelector(".position1 > a:nth-child(1)")).click();
		
		
		String windowPrevious = switchWindow();
		//String stationName = driver.findElement(By.cssSelector("#hero > div.hero-content > div > div.profile-info > div > ul > li.station-name > span")).getText();
		String stationName = driver.findElement(By.cssSelector(".profile-name > span:nth-child(1) > span:nth-child(1)")).getText();
		System.out.println("stationName:"  + stationName);
		if (!e2eStationName.equalsIgnoreCase(stationName))
			errors.append("Wrong radio station is launched from eye to ear.");
			
		
		
		/*
		
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
			
		*/	
	}

	
	//public void AL_544_BlogDetails()
	public void AL_501_BlogDetails()
	{  
		clickFirstBlog();
		
		//Check title and body
		String title = driver.findElement(By.className("left-container")).findElement(By.className("headline")).getText();
		System.out.println("See blog title:" + title);
		if (title.length() < 1)
			errors.append("Blog title doesn't look right. ");
		
		//String body =  driver.findElement(By.className("detailContent")).getText();
	//	String body = 
	    //List<WebElement> paras = driver.findElement(By.className("left-container")).findElements(By.tagName("p"));
		//System.out.println("See blog body:" + body);
		//if (paras.size() < 1)
		System.out.println("see body:" + driver.findElement(By.className("left-container")).getText());
		
	   if (driver.findElement(By.className("left-container")).getText().length() < 1)
			errors.append("Blog body doesn't look right. ");
		
		checkRecommendedAndAd();
		checkOminitureCall();		
	}
	
	//public void AL_547_URLredirect()
	public void AL_2049_URLredirect()
	{
		clickOnPhotoGallery();
		
		//driver.findElement(By.className("nowPlaying")).findElements(By.tagName("a")).get(0).click();
		driver.findElement(By.cssSelector(".np-container")).click();		
		
        String winHandleBefore = switchWindow();
		
		String url = driver.getCurrentUrl();
		
		if (!parseURL(url))
			Page.getErrors().append("Parameter values are not passed.");
		
	}
	
	
	
	private void checkRecommendedAndAd()
	{
		//Check Recommended
		
		//if (!driver.findElement(By.id("outbrainRecommendedStories")).findElement(By.className("moduleSubTitle")).getText().contains("Recommended Stories"))
		if (!driver.findElement(By.cssSelector("[class='module outbrain']")).getText().contains("Recommended Stories"))
		    errors.append("Recommended module is not shown.");
		
		
		if (recommendedStoryCount() <1 )
			errors.append("No recommended stories.");
		
		if (recommendedStoryCount() >10)
			errors.append("Too many recommended stories in one page.");
		
		//Check comment area
	//	if (fbcomments.getText().contains("Comments"))  //moduleContainer
		try{
		    // System.out.println("SEE moduleContainer:" + driver.findElement(By.id("fbcomments")).getText());
			driver.findElement(By.className("facebook-comments"));
		}catch(Exception e)
		{
			errors.append("Comment section is missing.");
			return;
		}
		if (!driver.findElement(By.className("facebook-comments")).getText().contains("Comments"))
			errors.append("Comment title is missing.");
		
		
		
		//Check Ad
		
	//	#google_ads_iframe_\/6663\/ccr\.miami\.fl\/whyi-fm_1	
		try{
			 Set<String> framesForAd = new HashSet<String>();
			   
			
			//Let's see how many iframes in the page
			List<WebElement> frames = driver.findElements(By.tagName("iframe"));
			System.out.println("Frame count:" + frames.size());
		    for (WebElement frame: frames)
		    {	String frameID = frame.getAttribute("id");
		    	System.out.println("frame id: " + frameID);
		         
			     if (!frameID.contains("_hidden_") && frameID.contains("google_ads_iframe_"))
			    	 framesForAd.add(frame.getAttribute("id"));
		    
		    }
		    
		    System.out.println("See ad frame count:" + framesForAd.size());
		    
		    for (String frame: framesForAd )
		       System.out.println("ad frame:" + frame);
			
		    if (framesForAd.size() <3)
		    	errors.append("Ad display is missing.");
		    
		}catch(Exception e)
		{
			errors.append("Something is wrong with ad display.");
		}
		
		
		//sOMETIMES THIS AD IS EMBEDDED IN AN IFRAME?!
		
		/*
		try{
			driver.switchTo().defaultContent();
			driver.switchTo().frame(2);
			if (ad300x250Image.getAttribute("src").length() < 4)
				errors.append("No ad is displayed in right 300x250.");
		}catch(Exception e)
		{
			driver.switchTo().defaultContent();
			//if (!driver.findElement(By.id("ad300x250")).isDisplayed())
			
			if (!driver.findElement(By.id("google_image_div")).isDisplayed())
				errors.append("No ad is displayed in right 300x250.");
		}
		
		*/
		//checkBottomAdD();
		
	}
	
	
	
	
	
	private void checkOminitureCall()
	{
		//to be implemented
	}
	
	
	public  void clickFirstArticle()
	{
			List<WebElement> links = driver.findElement(By.className("content")).findElements(By.tagName("a"));
		for (WebElement link: links)
		{
			link.click();
			break;
		}
		
		WaitUtility.sleep(1500);
		/*
	    List<WebElement> feedItems = driver.findElement(By.id("feedItems")).findElements(By.tagName("a"));
		
		for (WebElement item: feedItems)
		{
			item.click();
			break;
		}
		*/
	}
	
	private void clickFirstBlog()
	{
		
		//a.filter-option:nth-child(2)
	//	driver.findElement(By.xpath("//*[@id='homepageFeed']/div[3]/div[3]/div[1]/a[2]")).click();
		blogLink.click();
		WaitUtility.sleep(1500);
		
		driver.findElement(By.cssSelector("section.article-single:nth-child(1) > div:nth-child(2) > figure:nth-child(1) > figcaption:nth-child(2) > a:nth-child(4)")).click();
		/*
		List<WebElement> feedItems = driver.findElement(By.id("feedItems")).findElements(By.tagName("a"));
		
		for (WebElement item: feedItems)
		{
			item.click();
			break;
		}
		*/
	}
	
	private void clickOnPhotoGallery()
	{   //#ComponentContent > main > div.left-narrow-column > section.module.module-image-full.module-gallery.module-national-gallery > div.content > a:nth-child(1) > figure > img
		driver.findElement(By.className("module-national-gallery")).findElements(By.tagName("a")).get(0).click();
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
	  // count =  driver.findElement(By.className("OUTBRAIN")).findElement(By.className("ob_container_recs")).findElements(By.tagName("a")).size();
		count =  driver.findElement(By.className("left-container")).findElements(By.className("ob-rec-image")).size();
	    
	
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
    
   
    public void checkBottomAdD_OBSOLETE()
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
			
			boolean adInFooter= false;
			//Check: Is there any image in the footer?
			if (adURL.length() < 1)
			{	
				try{
					
					adInFooter = driver.findElement(By.className("footer")).findElements(By.tagName("img")).size()> 0;
				}catch(Exception e)
				{
					
				}	
			
			}

			//System.out.println("adURL: " + adURL);
    		
			if (adURL.length() < 4 && !adInFooter)
			{	errors.append("No ad is displayed in the bottom Leader Board.");
				
			}
    }
}
