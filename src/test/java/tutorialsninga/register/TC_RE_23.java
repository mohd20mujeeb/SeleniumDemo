package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RE_23 {
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
	public void verifyAllRegisterLink() 
  {
	 
      driver.findElement(By.xpath("//a/i[@class='fa fa-phone']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Contact Us")).isDisplayed());
      driver.navigate().back();
      driver.findElement(By.xpath("//a/i[@class='fa fa-heart']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
      driver.navigate().back();
      driver.findElement(By.xpath("//a[@title='Shopping Cart']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Shopping Cart")).isDisplayed());
      driver.navigate().back();
      driver.findElement(By.xpath("//span[.='Checkout']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Shopping Cart")).isDisplayed());
      driver.navigate().back();
      driver.findElement(By.linkText("Qafox.com")).click();
      String expectedURL="https://tutorialsninja.com/demo/index.php?route=common/home";
      Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
      driver.navigate().back();
      driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Search")).isDisplayed());
      driver.navigate().back();
      driver.findElement(By.linkText("Register")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
      
      driver.findElement(By.linkText("Account")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
      driver.navigate().back();
      
      WebElement icon = driver.findElement(By.xpath("//a/i[@class='fa fa-home']"));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", icon);

      Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
      driver.navigate().back();
      
      driver.findElement(By.linkText("login page")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
      driver.navigate().back();
      
      driver.findElement(By.xpath("//a[@class=\"agree\"]")).click();
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
      WebElement xOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='×']")));
      Assert.assertTrue(xOption.isDisplayed());
      xOption.click();
      
      driver.findElement(By.xpath("//input[@type='submit']")).click();
      Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
      driver.findElement(By.linkText("Register")).click();

      
}
  
}
