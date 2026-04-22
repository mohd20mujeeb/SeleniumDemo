package tutorialsninga.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_13 extends Base {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test
	public void verifyPlaceholder() {

		String expectedFN = "First Name";
		String expectedLN = "Last Name";
		String expectedEmail = "E-Mail";
		String expectedTelephone = "Telephone";
		String expectedPass = "Password";
		String expectedConPass = "Password Confirm";

		Assert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("placeholder"), expectedFN);
		Assert.assertEquals(driver.findElement(By.id("input-lastname")).getDomAttribute("placeholder"), expectedLN);
		Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), expectedEmail);
		Assert.assertEquals(driver.findElement(By.id("input-telephone")).getDomAttribute("placeholder"),
				expectedTelephone);
		Assert.assertEquals(driver.findElement(By.id("input-password")).getDomAttribute("placeholder"), expectedPass);
		Assert.assertEquals(driver.findElement(By.id("input-confirm")).getDomAttribute("placeholder"), expectedConPass);
	}
}
