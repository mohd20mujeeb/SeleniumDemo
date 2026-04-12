package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_18 {
	@Test
    public void verifyAllTextfieldSize()
    {
    	WebDriver driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.xpath("//span[.='My Account']")).click();
		  driver.findElement(By.linkText("Register")).click();
		  String expectedHeight ="34px";
		  String  expectedWidth ="701.25px";
		  String actualHeightFn=  driver.findElement(By.id("input-firstname")).getCssValue("height");
		  String actualwidthFn=  driver.findElement(By.id("input-firstname")).getCssValue("width");
	      Assert.assertEquals(actualHeightFn, expectedHeight);
	      Assert.assertEquals(actualwidthFn, expectedWidth);
	      
	      String actualHeightLn=  driver.findElement(By.id("input-lastname")).getCssValue("height");
		  String actualwidthLn=  driver.findElement(By.id("input-lastname")).getCssValue("width");
	      Assert.assertEquals(actualHeightLn, expectedHeight);
	      Assert.assertEquals(actualwidthLn, expectedWidth);
	      
	      String actualHeightTel=  driver.findElement(By.id("input-telephone")).getCssValue("height");
		  String actualwidthTel=  driver.findElement(By.id("input-telephone")).getCssValue("width");
	      Assert.assertEquals(actualHeightTel, expectedHeight);
	      Assert.assertEquals(actualwidthTel, expectedWidth);
	      
	      String actualHeightPass=  driver.findElement(By.id("input-password")).getCssValue("height");
		  String actualwidthPass=  driver.findElement(By.id("input-password")).getCssValue("width");
	      Assert.assertEquals(actualHeightPass, expectedHeight);
	      Assert.assertEquals(actualwidthPass, expectedWidth);
	      
	      
	      driver.quit();
		  
	      //driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
	      //driver.findElement(By.id("input-email")).sendKeys(generateEmale());
	      //driver.findElement(By.id("input-telephone")).sendKeys("8858846651"); 
	      //driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
	     // driver.findElement(By.id("input-password")).sendKeys(passwordText);
	     // driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
    }
}
