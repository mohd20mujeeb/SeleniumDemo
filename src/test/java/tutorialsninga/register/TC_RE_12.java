package tutorialsninga.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RE_12 {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		String browserName = "firefox";
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
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
