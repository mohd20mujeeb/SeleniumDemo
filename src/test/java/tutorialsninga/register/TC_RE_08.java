package tutorialsninga.register;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_08 extends Base{
	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver	= openBrowserAndAppliation();
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
	public void verifyInalidRegisterPasswordEroor() {
		driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
		driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
		driver.findElement(By.id("input-email")).sendKeys(generateEmale());
		driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
		driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@12");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']"))
				.isDisplayed());

		// Assert.assertEquals(driver.findElement(By.xpath("//div[.='Password
		// confirmation does not match password!']")).getText(), );
	}

	public String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";
	}
}
