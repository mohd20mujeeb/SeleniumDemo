package tutorialsninga.register;

import java.io.IOException;
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

public class TC_RE_10 {
	WebDriver driver;

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

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void varifyRegisterWithInvalidEmail() throws InterruptedException, IOException {
		String browserName = "chrome";
		driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
		driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
		driver.findElement(By.id("input-email")).sendKeys("Mujeeb");
		driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
		driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);

		if (browserName.equals("chrome")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please include an '@' in the email address. 'Mujeeb' is missing an '@'.");
		} else if (browserName.equals("firefox")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please enter an email address.");
		} else if (browserName.equals("edge")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please include an '@' in the email address. 'Mujeeb' is missing an '@'.");
		}
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("Mujeeb@");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);

		if (browserName.equals("chrome")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please enter a part following '@'. 'Mujeeb@' is incomplete.");
		} else if (browserName.equals("firefox")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please enter an email address.");
		} else if (browserName.equals("edge")) {

			Assert.assertEquals(driver.findElement(By.id("input-email")).getDomProperty("validationMessage"),
					"Please enter a part following '@'. 'Mujeeb@' is incomplete.");
		}

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("Mujeeb@gmail");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String errorMsg = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[.='E-Mail Address does not appear to be valid!']")).getText(),
				errorMsg);

	}

}
//Thread.sleep(3000);
//File Screenshot1 = driver.findElement(By.xpath("(//form['@class=form-horizontal'])[2]"))
//		.getScreenshotAs(OutputType.FILE);
//File perm = new File("./Screenshot/ActualOutput1.png");
//FileHandler.copy(Screenshot1, perm);
//BufferedImage actualBImg = ImageIO
//		.read(new File(System.getProperty("user.dir") + "\\Screenshot\\ActualOutput1.png"));
//BufferedImage expectedBImg = ImageIO
//		.read(new File(System.getProperty("user.dir") + "\\Screenshot\\ExpectedOutput1.png"));
//ImageDiffer imgDiff = new ImageDiffer();
//ImageDiff diff = imgDiff.makeDiff(expectedBImg, actualBImg);
//Assert.assertFalse(diff.hasDiff());
//driver.findElement(By.id("input-email")).clear();
//driver.findElement(By.id("input-email")).sendKeys("Mujeeb@");
//driver.findElement(By.xpath("//input[@value='Continue']")).click();
//Thread.sleep(3000);
//File Screenshot2 = driver.findElement(By.xpath("(//form['@class=form-horizontal'])[2]"))
//		.getScreenshotAs(OutputType.FILE);
//File perm2 = new File("./Screenshot/ActualOutput2.png");
//FileHandler.copy(Screenshot2, perm2);
//BufferedImage actualBImg1 = ImageIO
//		.read(new File(System.getProperty("user.dir") + "\\Screenshot\\ActualOutput2.png"));
//BufferedImage expectedBImg1 = ImageIO
//		.read(new File(System.getProperty("user.dir") + "\\Screenshot\\ExpectedOutput2.png"));
//ImageDiffer imgDiff1 = new ImageDiffer();
//ImageDiff diff1 = imgDiff1.makeDiff(expectedBImg1, actualBImg1);
//Assert.assertFalse(diff1.hasDiff());
