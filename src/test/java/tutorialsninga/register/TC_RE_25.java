package tutorialsninga.register;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_25 extends Base {
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
	public void verifyRegisterPage()
    {

	      Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	      
	      String expectedPage="Register Account";
	      Assert.assertEquals(driver.findElement(By.xpath("//h1[.='Register Account']")).getText(), expectedPage);
	      
	     String ExpectedUrl="https://tutorialsninja.com/demo/index.php?route=account/register";
	     Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);
	     
	     String ExpectedTitle="Register Account";
	     Assert.assertEquals(driver.getTitle(), ExpectedTitle);
	     
	      
}
}
