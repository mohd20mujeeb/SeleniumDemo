package tutorialsninga.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_07 extends Base {
	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver=openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void regierWithAllPossibleWays() {

		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());

		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());

		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());

		

	}
}
