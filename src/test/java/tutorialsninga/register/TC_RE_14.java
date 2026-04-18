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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RE_14 {
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
	
@Test
public void verifyMandatoryFieldsSymbolAndColor() {
	  String expectedContent="\"* \"";
	  String expectedColor="rgb(255, 0, 0)";
	  WebElement firstNameLable= driver.findElement(By.cssSelector("label[for='input-firstname']"));
	  JavascriptExecutor js = (JavascriptExecutor)driver;
      String fnContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", firstNameLable);
      String fnColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", firstNameLable);
      Assert.assertEquals(fnContent, expectedContent);
      Assert.assertEquals(fnColor, expectedColor);
      
      WebElement lastNameLable= driver.findElement(By.cssSelector("label[for='input-lastname']"));
      String lnContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", lastNameLable);
      String lnColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", lastNameLable);
      Assert.assertEquals(lnContent, expectedContent);
      Assert.assertEquals(lnColor, expectedColor);
      
      WebElement emailNameLable= driver.findElement(By.cssSelector("label[for='input-email']"));
      String emailContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", emailNameLable);
      String emailColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", emailNameLable);
      Assert.assertEquals(emailContent, expectedContent);
      Assert.assertEquals(emailColor, expectedColor);
      
      WebElement telephoneLable= driver.findElement(By.cssSelector("label[for='input-telephone']"));
      String telephoneContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", telephoneLable);
      String telephoneColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", telephoneLable);
      Assert.assertEquals(telephoneContent, expectedContent);
      Assert.assertEquals(telephoneColor, expectedColor);
      
      WebElement passLable= driver.findElement(By.cssSelector("label[for='input-password']"));
      String passContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", passLable);
      String passColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", passLable);
      Assert.assertEquals(passContent, expectedContent);
      Assert.assertEquals(passColor, expectedColor);
      
      WebElement conpassLable= driver.findElement(By.cssSelector("label[for='input-confirm']"));
      String conpassContent = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", conpassLable);
      String conpassColor = (String)js.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", conpassLable);
      Assert.assertEquals(conpassContent, expectedContent);
      Assert.assertEquals(conpassColor, expectedColor);
      
      
      
      driver.quit();
      
}
}
