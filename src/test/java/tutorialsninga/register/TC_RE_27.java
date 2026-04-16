package tutorialsninga.register;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RE_27 {
	@Test(dataProvider = "enviornmentsSupplier")
	public void varifyRegisterPageUI(String env) throws IOException
	 {
	 	     WebDriver driver=null;
		     String browserName = env;
		     if(browserName.equals("chrome")) {
	    	  driver =new ChromeDriver();
		     }else if(browserName.equals("firefox")) 
		     {
		    	  driver =new FirefoxDriver();
		     }else if(browserName.equals("edge")) 
		     {
		    	  driver =new EdgeDriver();
		     }
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		      driver.manage().window().maximize();
		      driver.get("https://tutorialsninja.com/demo/");
		      driver.findElement(By.xpath("//span[.='My Account']")).click();
		      driver.findElement(By.linkText("Register")).click();
		      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
		      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
		      driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmale());
		      driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
		      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
		      driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
		      driver.findElement(By.name("agree")).click();
		      driver.findElement(By.xpath("//input[@value='Continue']")).click();
              Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
         }
	@DataProvider(name="enviornmentsSupplier")
	 public Object[][] passTestEnviornents() 
     {
   	  Object[][] envs = {{"chrome"},{"firefox"},{"edge"}};
   	  return envs;
     }
}
