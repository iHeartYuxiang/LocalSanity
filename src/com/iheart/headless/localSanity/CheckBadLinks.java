package com.iheart.headless.localSanity;


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
import com.iheart.selenium.localSanity.Z100HomePage;



@RunWith(Parameterized.class)

public class CheckBadLinks {

@Parameters
public static Collection<Object[]> data() {

	
	
	Collection<Object[]> params = new ArrayList<>(100);

	//params.add(new Object[] {  "http://www.z100.com"});
      params.add(new Object[] {  "http://www.y100.com"});
      params.add(new Object[] {  "http://www.radio1057.com"});
    //params.add(new Object[] {  "http://m.933flz.com"});
    // params.add(new Object[] {  "http://www.933flz.com"});
   //  params.add(new Object[] {  "http://www.Z100.com"});
    
    // params.add(new Object[] {  "http://mike.iheartmedia.com"});
     //params.add(new Object[] {  "http://m.mike.iheartmedia.com"});
     
    return params;
}
protected static DesiredCapabilities dCaps;

	private final String url;
	
	WebDriver driver;
	
	Z100HomePage homePage;
	 ArticlePage articlePage;
	 Header header;	
		
	//String browser = "firefox";
 //  String browser = "chrome";
	
	
	
	public CheckBadLinks(String url) {
		this.url = url;
	
	}
	
	@Rule public TestName name = new TestName();
	

	///* run headless
	@Before
	public void setUp() throws Exception {

		Page.setURL(url);
		
		
	   // System.setProperty("phantomjs.binary.path", "C:\\Users\\1111128\\workspace\\lib\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe");
		System.setProperty("phantomjs.binary.path", "/Users/1111128/git/lib/phantomjs-2.0.0-macosx/bin/phantomjs");
		
		dCaps = new DesiredCapabilities();
		dCaps.setBrowserName("firefox");
		dCaps.setVersion("39");
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
	
	
	@Test
	 public void testBadLinks() throws Exception
	 {   
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		homePage.goThroughLinks();
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
	    }
	    
	   

}