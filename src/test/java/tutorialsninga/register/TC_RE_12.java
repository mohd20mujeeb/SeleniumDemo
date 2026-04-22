package tutorialsninga.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TC_RE_12 extends Base{
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
	public void registerUsingKeyboard() {
		Actions act = new Actions(driver);
		for (int i = 1; i <= 23; i++) {
			act.sendKeys(Keys.TAB).perform();
		}
		act.sendKeys("Mohd").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
				.sendKeys("Mujeeb").sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(generateEmale())
				.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("876755786").sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys("12345").sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
				.sendKeys("12345").sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.ARROW_LEFT)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).pause(Duration.ofSeconds(1)).build().perform();

		Assert.assertTrue(driver.findElement(By.linkText("Success")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
	}

	public String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";

	}
}
