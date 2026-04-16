package tutorialsninga.register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RE_26 {
    @Test
	public void varifyRegisterPageUI() throws IOException
 {
    	 WebDriver driver =new ChromeDriver();
			
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.xpath("//span[.='My Account']")).click();
	      driver.findElement(By.linkText("Register")).click();
	      
	      TakesScreenshot ts=(TakesScreenshot)driver;
	      File scrScreenshot = ts.getScreenshotAs(OutputType.FILE);
	      try {
	      FileHandler.copy(scrScreenshot, new File(System.getProperty("user.dir")+"\\Screenshot\\actualRegisterUI.png"));
	      }catch(IOException e){
	    	  e.printStackTrace();
	      } 
	     
	      Assert.assertTrue(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshot\\actualRegisterUI.png", System.getProperty("user.dir")+"\\Screenshot\\expectedRegisterUI.png"));
 }
}
