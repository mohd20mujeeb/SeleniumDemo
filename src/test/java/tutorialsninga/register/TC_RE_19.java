package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utils.CommonUtils;

public class TC_RE_19 {
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
	public void verifyTextfieldSpaceTriming() {
		
        SoftAssert softAssert= new SoftAssert();		
		String enteredFirstName = "      Mohd      ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = "      Mujeeb     ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "       " + CommonUtils.generateEmale() + "        ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredTelephone = "    8858476236    ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredTelephone);
		driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Edit your account information")).click();
		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("value"), enteredFirstName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getDomAttribute("value"), enteredLastName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getDomAttribute("value"), enteredEmail.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getDomAttribute("value"), enteredTelephone.trim());
		softAssert.assertAll();
	}

}
