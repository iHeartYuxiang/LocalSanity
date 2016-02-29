package com.iheart.junit.localSanity;


import com.iheart.selenium.localSanity.*;

import static org.junit.Assert.*; 

import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RunSanityTest {

	 WebDriver driver;
		
	 LocalPage homePage;
	 ArticlePage articlePage;
	 Header header;
		
		
	 String browser = "firefox";
	// String browser = "chrome";
	   
		 
		final String URL = "http://www.y100.com";
		
		@Rule public TestName name = new TestName();
		
		
		@Before
	    public void init() {
	        driver = Utils.launchBrowser(URL, browser);
	        Page.setURL(URL);
	        Page.setDriver (driver);
	        WaitUtility.sleep(2000);//Wait for page to load
	        
	        homePage = PageFactory.initElements(driver, LocalPage.class);
	        articlePage = PageFactory.initElements(driver, ArticlePage.class);
	        header = PageFactory.initElements(driver, Header.class);
	        
	        Page.getErrors().delete(0, Page.getErrors().length());
	    }

		
		 @Test
		 public void test_AL_855_nowPlayingWidegt() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		header.AL_855_nowPlayingWidegt();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
		 
		 @Test
		 public void test_AL_495_articleDetail() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		articlePage.AL_495_articleDetail();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
		 
		
		 @Test
		 public void testAL_485_listenLive() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		homePage.testAL_485_listenLive();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
		
		 
		 @Test
		 public void test_AL_2205_NowPlayingBar_DeskTop() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		header.AL_2205_NowPlayingBar_DeskTop();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
		 
		 
		 @Test
		 public void test_AL_857_NowPlayingBar_Mobile() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		//METHOD IS NOT IMPLEMENTED IT
		 		header.AL_857_NowPlayingBar_Mobile();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
		 
		 
		 
		 
		 @Test
		// public void test_AL_534_eyesToEars() throws Exception
		 public void test_AL_189_eyesToEars() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		articlePage.AL_189_eyesToEars();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
	 
		
		 
		 @Test
		// public void test_AL_544_BlogDetails() throws Exception
		 public void test_AL_501_BlogDetails() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		articlePage.AL_501_BlogDetails();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
	 
		
		 @Test
		// public void test_AL_547_URLredirect() throws Exception
		 public void test_AL_2049_URLredirect() throws Exception
		 {   
		 	System.out.println("test method:" +  name.getMethodName() );
		 	try{
		 		articlePage.AL_2049_URLredirect();
		 	}catch(Exception e)
		 	{
		 		handleException(e);
		 	}  	
		 	System.out.println(name.getMethodName() + " is Done.");
		 }
	 
		 
	    @After
	    public void tearDown() throws Exception{
	        driver.quit(); 
	    	if (Page.getErrors().length() > 0)
				 fail(Page.getErrors().toString());
	    	
	    }
	
	    private void handleException(Exception e)
	    {   Page.getErrors().append("Exception is thrown.");
	        e.printStackTrace();
            try{
	    	   Utils.takeScreenshot(driver, name.getMethodName());
            }catch(Exception eX)
            {
            	
            }
	    }
	    
	   
}

