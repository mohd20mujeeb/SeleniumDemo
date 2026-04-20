package tutorialsninga.register;

import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_RE_17 {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		String browserName = "chrome";
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
