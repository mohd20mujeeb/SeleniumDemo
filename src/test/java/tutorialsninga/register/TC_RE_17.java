package tutorialsninga.register;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_17 extends Base {
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

	@Test(dataProvider = "passwordSupplier")
	public void verifyPasswordFollowingStandard(String passwordText) {
		driver.findElement(By.id("input-firstname")).sendKeys("Md");
		driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
		driver.findElement(By.id("input-email")).sendKeys(generateEmale());
		driver.findElement(By.id("input-telephone")).sendKeys("8858846651");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.id("input-password")).sendKeys(passwordText);
		driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String errormsg = "Password is not matching standard ";
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='Password must be between 4 and 20 characters!']")).getText(),
				errormsg);

	}

	public String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";
	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] datapass() {
		Object[][] data = { { "12345" }, { "abcdefghi" }, { "abcd12345" }, { "ABCDE456#" }, { "abcd123$" } };
		return data;
	}
}
