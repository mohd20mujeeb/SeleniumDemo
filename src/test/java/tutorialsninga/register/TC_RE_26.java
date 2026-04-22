package tutorialsninga.register;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TC_RE_26 extends Base {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void setup() {

		driver	=openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

    @Test
	public void varifyRegisterPageUI() throws IOException
 {
    	 
	      TakesScreenshot ts=(TakesScreenshot)driver;
	      File scrScreenshot = ts.getScreenshotAs(OutputType.FILE);
	      
//	      FileHandler.copy(scrScreenshot,
//	    	        new File(System.getProperty("user.dir") + "\\Screenshot\\expectedRegisterUI.png"));
	      try {
	      FileHandler.copy(scrScreenshot, new File(System.getProperty("user.dir")+"\\Screenshot\\actualRegisterUI.png"));
	      }catch(IOException e){
	    	  e.printStackTrace();
	      } 
	     
	      Assert.assertTrue(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshot\\actualRegisterUI.png", System.getProperty("user.dir")+"\\Screenshot\\expectedRegisterUI.png"));
 }
}
