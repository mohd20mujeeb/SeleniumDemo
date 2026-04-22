package tutorialsninga.register;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_11 extends Base{
	WebDriver driver;
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {

		driver=openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test
	public void varifyRegisterWithInvalidTelephone() {
		
	      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
	      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
	      driver.findElement(By.id("input-email")).sendKeys( generateEmale());
	      driver.findElement(By.id("input-telephone")).sendKeys("abcd");
	      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
	      driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
          driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@value='Continue']")).click();
	      
	      String ExpectedErrorMessage = "Invalid Telephone Number";
	      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Invalid Telephone Number']")).getText(), ExpectedErrorMessage);
	} 
	public   String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
}
}
