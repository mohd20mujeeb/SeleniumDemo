package tutorialsninga.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TC_RE_24 extends Base{
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
	public void verifyConfirmPass()
    {
	      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
	      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
	      driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmale());
	      driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
	      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
	      //driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@value='Continue']")).click();
	      String errormsg="Password confirmation does not match password!";
	      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']")).getText(), errormsg);
	 
	  
	      
    }
	
}
