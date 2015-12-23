package com.iheart.junit.localSanity;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;




import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;






import com.iheart.selenium.localSanity.ArticlePage;
import com.iheart.selenium.localSanity.Header;
import com.iheart.selenium.localSanity.Page;
import com.iheart.selenium.localSanity.Utils;
import com.iheart.selenium.localSanity.LocalPage;



@RunWith(Parameterized.class)

public class RunParallel {

@Parameters
public static Collection<Object[]> data() {

	/*
return Arrays.asList(new Object[]
		{"http://www.y100.com"} ,
		{"http://www.933flz.com"},
		{"http://www.Z100.com"}
		);
*/
	
	Collection<Object[]> params = new ArrayList<>(100);

	//params.add(new Object[] {  "http://www.z100.com"});
      params.add(new Object[] {  "http://www.y100.com"});
      params.add(new Object[] {  "http://www.radio1057.com"});
    //params.add(new Object[] {  "http://m.933flz.com"});
    // params.add(new Object[] {  "http://www.933flz.com"});
   //  params.add(new Object[] {  "http://www.Z100.com"});
    
  //   params.add(new Object[] {  "http://mike.iheartmedia.com"});
    // params.add(new Object[] {  "http://m.mike.iheartmedia.com"});
     
    return params;
}
protected static DesiredCapabilities dCaps;

	private final String url;
	
	WebDriver driver;
	
	LocalPage homePage;
	 ArticlePage articlePage;
	 Header header;	
		
	String browser = "firefox";
   //String browser = "chrome";
	
	
	
	public RunParallel(String url) {
		this.url = url;
	
	}
	
	@Rule public TestName name = new TestName();
	

	/* run headless
	@Before
	public void setUp() throws Exception {

		Page.setURL(url);
		
		
		// System.setProperty("phantomjs.binary.path", "C:\\Users\\1111128\\workspace\\lib\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
		dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", true);
		
		driver = new PhantomJSDriver(dCaps);
		driver.get(url + "/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		 Page.setDriver (driver);
	     homePage = PageFactory.initElements(driver, Z100HomePage.class);
	     articlePage = PageFactory.initElements(driver, ArticlePage.class);
	     header = PageFactory.initElements(driver, Header.class);
	        
	      Page.getErrors().delete(0, Page.getErrors().length());
	}
	*/
	
	
//headful run
	@Before
   public void init() {
       driver = Utils.launchBrowser(url, browser);
      	Page.setURL(url);
       Page.setDriver (driver);
       homePage = PageFactory.initElements(driver, LocalPage.class);
       
       articlePage = PageFactory.initElements(driver, ArticlePage.class);
       header = PageFactory.initElements(driver, Header.class);
       
       Page.getErrors().delete(0, Page.getErrors().length());
   }

	
	 @Test
	 public void test_AL_498_nowPlayingWidegt() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		header.AL_498_nowPlayingWidegt();
	 	}catch(Exception e)
	 	{
	 		handleException(e);
	 	}  	
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	 
	 @Test
	 public void test_AL_543_articleDetail() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		articlePage.AL_543_articleDetail();
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
	 public void test_AL_490_NowPlayingBar_DeskTop() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		header.AL_490_NowPlayingBar_DeskTop();
	 	}catch(Exception e)
	 	{
	 		handleException(e);
	 	}  	
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	 
	 
	 @Test
	 public void test_AL_491_NowPlayingBar_Mobile() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		//METHOD IS NOT IMPLEMENTED IT
	 		header.AL_491_NowPlayingBar_Mobile();
	 	}catch(Exception e)
	 	{
	 		handleException(e);
	 	}  	
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	 
	 
	 
	 
	 @Test
	 public void test_AL_534_eyesToEars() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		articlePage.AL_534_eyesToEars();
	 	}catch(Exception e)
	 	{
	 		handleException(e);
	 	}  	
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
 
	
	 
	 @Test
	 public void test_AL_544_BlogDetails() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		articlePage.AL_544_BlogDetails();
	 	}catch(Exception e)
	 	{
	 		handleException(e);
	 	}  	
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
 
	
	 @Test
	 public void test_AL_547_URLredirect() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		articlePage.AL_547_URLredirect();
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
	        /*
	        try{
	    	   Page.takeScreenshot(driver, name.getMethodName());
            }catch(Exception eX)
            {
            	
            }
            */
	    }
	    
	   

}