package tutorialsninga.register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RE_26 {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void setup() {

		String browserName = "chrome";
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
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
