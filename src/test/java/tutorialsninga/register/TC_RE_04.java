package tutorialsninga.register;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_04 extends Base{

	WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver	=openBrowserAndAppliation();
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
	public void verifyErrormessageAllFields() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedemailwarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='First Name must be between 1 and 32 characters!']")).getText(),
				expectedFirstNameWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='Last Name must be between 1 and 32 characters!']")).getText(),
				expectedLastNameWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='E-Mail Address does not appear to be valid!']")).getText(),
				expectedemailwarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='Telephone must be between 3 and 32 characters!']")).getText(),
				expectedTelephoneWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='Password must be between 4 and 20 characters!']")).getText(),
				expectedPasswordWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='Warning: You must agree to the Privacy Policy!']")).getText(),
				expectedPrivacyPolicyWarning);

	}
}
