package tutorialsninga.register;
import java.time.Duration;

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
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RE_21 {
	WebDriver driver;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
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

	@Test
	  public void verifyPolicyCheckBox()
	    {
		      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
		      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
		      driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmale());
		      driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
		      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
		      driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
		     // driver.findElement(By.name("agree")).click();
		      driver.findElement(By.xpath("//input[@value='Continue']")).click();
		      String errormsg="Warning: You must agree to the Privacy Policy!";
		      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Warning: You must agree to the Privacy Policy!']")).getText(), errormsg);
		      
		  
		      
	    }
}
