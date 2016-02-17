package com.iheart.selenium.localSanity;


import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Header extends Page{

	ArticlePage articlePage; 
	
	@FindBy(css="body > div.pageWrapper > div.nowPlayingWrapper.full > div > div.playlist > div.header.current > div.trackInfo > span.playingTitle")
	private WebElement nowPlayingWidget;
	@FindBy(css="body > div.pageWrapper > div.nowPlayingWrapper.full > div > div.playlist > div.header.current > div.trackInfo > a.trackTitle")
    private WebElement trackTitleInWidget;


	@FindBy(id="ihrnp_info") private WebElement ihrnp_info;
	@FindBy(id="ihrnp_track") private WebElement ihrnp_track;
	@FindBy(id="ihrnp_artist") private WebElement ihrnp_artist;

	
	//@FindBy(css="body > div.pageWrapper > div.nowPlayingWrapper.full > div > a > span.cta > span:nth-child(1)")
	@FindBy(css=".listen-live > a:nth-child(1)")
	  private WebElement listenLive;

	//public void AL_498_nowPlayingWidegt()
	public void AL_855_nowPlayingWidegt()
	{    
		if (!isHeaderPlaying())
		{
			System.out.println("No song is playing. Do nothing.");
			return;
		}
		
	/*
	   String trackInfo = driver.findElement(By.cssSelector(".link-track > figure:nth-child(1) > figcaption:nth-child(2) > h4:nth-child(2)")).getText();
		
		System.out.println("See trackInfo:" + trackInfo);
		*/
		//parse the track and artist in the widget
		String trackInWidget = parseTrackInfo().get("trackName");
		
		String artistInWidget =  parseTrackInfo().get("artist");
				
		//driver.findElement(By.className("trackInfo")).findElement(By.className("artistName")).getText();
		
		//Now go to any article, then do the check:
		
		articlePage = PageFactory.initElements(driver, ArticlePage.class);
		articlePage.clickFirstArticle();
		System.out.println("See track/artist in article page:" + articlePage.getPlayingTrack());
		System.out.println("See track/artist in article page:" + articlePage.getPlayingArtist());
		
		if (!trackInWidget.equalsIgnoreCase(articlePage.getPlayingTrack()))
			errors.append("Song track doesn't match between widget and ihrnp section.");
		if (!artistInWidget.equalsIgnoreCase(articlePage.getPlayingArtist()))
			errors.append("Artist  doesn't match between widget and ihrnp section.");
	}
	
	private Map<String, String> parseTrackInfo()
	{
		 String trackInfo = driver.findElement(By.cssSelector(".link-track > figure:nth-child(1) > figcaption:nth-child(2) > h4:nth-child(2)")).getText();
			
		System.out.println("See trackInfo:" + trackInfo);
		
		return parseTrackInfo(trackInfo);
		
	}
	
	
	//Return trackName and Artist
	private Map<String, String> parseTrackInfo(String trackInfo)
	{  
		if (!trackInfo.contains(" by "))
			return null;
		Map<String, String> result = new HashMap<String,String>();
		String track = trackInfo.split(" by ")[0].trim();
		String artist = trackInfo.split(" by ")[1].trim();
		result.put("trackName", track);
		result.put("artist", artist);
		
		System.out.println("track/artist:"+ track + "/" + artist );
		
		return result;
		
	}
	
	
	public void AL_485_listenLive()
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
	
	//public void AL_490_NowPlayingBar_DeskTop()
	public void AL_2205_NowPlayingBar_DeskTop()
	{     
		if (!isHeaderPlaying())
		{
			System.out.println("No song is playing. Do nothing.");
			return;
		}
		//Need to compare iheart page with local side by side
		//listenLive.click();
		//WaitUtility.sleep(1000);
	    //String trackInWidget = driver.findElement(By.className("trackInfo")).findElements(By.tagName("a")).get(0).getText();
		//String artistInWidget = driver.findElement(By.className("trackInfo")).findElements(By.tagName("a")).get(1).getText(); 
		String trackInWidget = parseTrackInfo().get("trackName");
		
		String artistInWidget =  parseTrackInfo().get("artist");
		
		System.out.println("Now playing:" + trackInWidget + " BY " + artistInWidget);
		
		listenLive.click();
		WaitUtility.sleep(1000);
		
		String windowPrevious = switchWindow();
		//get song track from iheart.com
		//String trackPlayingInIheart = driver.findElement(By.cssSelector("#hero > div.hero-content > div > div.profile-info > div > ul > li.station-description.type-secondary.type-small.tight > p")).getText();
		String trackPlayingInIheart = "";
		try{
			
			//.station-description > div:nth-child(1) > p:nth-child(1) > div:nth-child(1) > a:nth-child(2)
	    	trackPlayingInIheart = driver.findElement(By.className("station-now-playing")).getText();
		}catch(Exception e)
		{
			driver.navigate().refresh();
			//Could it be that: Preroll is triggered, then soft gate.
		    
			trackPlayingInIheart = driver.findElement(By.className("station-now-playing")).getText();
		}
		System.out.println("Traking playing in iheart.com:" + trackPlayingInIheart);
		
		
		
		//if (!trackPlayingInIheart.equals(trackInWidget))
		if (!trackInWidget.contains(trackPlayingInIheart))
			errors.append("Track playing in widget doesn't match up with the one in iheart .");
		
	}
	
	//public void AL_491_NowPlayingBar_Mobile()
	public void AL_857_NowPlayingBar_Mobile()
	{   //
		WaitUtility.sleep(2000);
		if (!isHeaderPlaying())
		{
			System.out.println("No song is playing. Do nothing.");
			return;
		}
		String mobileURL = driver.getCurrentUrl().replace("//www.", "//m.");
		System.out.println("Mobile:" + mobileURL);
		
		driver.get(mobileURL);
		
		
		String trackInWidget = driver.findElement(By.id("title")).getText();
		String artistInWidget = driver.findElement(By.id("artist")).getText();
		
		System.out.println("Now playing in mobile:" + trackInWidget + " by " + artistInWidget);
		
		driver.findElement(By.className("localHeader")).findElements(By.tagName("a")).get(0).click();
		driver.findElement(By.className("listen-live")).findElement(By.tagName("a")).click();
		
	
		String windowPrevious = switchWindow();
		WaitUtility.sleep(500);
		//get song track from iheart.com
		//String trackPlayingInIheart = driver.findElement(By.cssSelector("#hero > div.hero-content > div > div.profile-info > div > ul > li.station-description.type-secondary.type-small.tight > p")).getText();
		String trackPlayingInIheart = driver.findElement(By.cssSelector(".station-description > div:nth-child(1) > p:nth-child(1) > div:nth-child(1) > a:nth-child(2)")).getText();
			
		System.out.println("Traking playing in iheart.com:" + trackPlayingInIheart);
		
		//switch to the previous window
		driver.switchTo().window(windowPrevious);
		
		if (!trackPlayingInIheart.equalsIgnoreCase(trackInWidget))
			errors.append("Track playing in widget doesn't match up with the one in iheart .");
		
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
	
	private  boolean isHeaderPlaying()
	{   System.out.println("url:" + Page.getURL());
		WaitUtility.sleep(3000);
	    String trackInWidget = "";
		try{
			if (isMobileSite(Page.getURL()))
				trackInWidget = driver.findElement(By.id("title")).getText();
			else
				//trackInWidget = driver.findElement(By.className("trackInfo")).findElements(By.tagName("a")).get(0).getText();
				trackInWidget = driver.findElement(By.cssSelector("body > div.page-wrapper > div.nowplaying-wrapper.full > section > section.playlist.left > div.header.current > a > figure > figcaption > h4")).getText();
			  
				System.out.println("IsPlaying:" + trackInWidget);
		    if (trackInWidget.contains("#1 Hit Music Station") || trackInWidget.startsWith("Z100") || trackInWidget.startsWith("z100"))
				return false;
			else 
				return true;
			
		}catch(Exception e)
		{    e.printStackTrace();
	          return false;
		}
		   
		
		
	   
	}
	
	
}
