package tutorialsninga.register;

import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TC_RE_11 extends Base{
	WebDriver driver;
	Properties prop;
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setup() {
        prop=CommonUtils.loadProperties();
		driver=openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test
	public void varifyRegisterWithInvalidTelephone() {
		
	      driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
	      driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
	      driver.findElement(By.id("input-email")).sendKeys( generateEmale());
	      driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("invalidTelephone"));
	      driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
	      driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
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
