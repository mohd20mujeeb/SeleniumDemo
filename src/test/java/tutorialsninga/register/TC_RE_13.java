package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_13 {
   @Test
	public void  verifyPlaceholder() {
	   WebDriver driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.xpath("//span[.='My Account']")).click();
		  driver.findElement(By.linkText("Register")).click();
		  
		  String expectedFN ="First Name";
		  String expectedLN="Last Name";
		  String expectedEmail="E-Mail";
		  String expectedTelephone="Telephone";
		  String expectedPass="Password";
		  String expectedConPass="Password Confirm";
		  
		  Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"),expectedFN);
		  Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"),expectedLN);
		  Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"),expectedEmail);
		  Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"),expectedTelephone);
		  Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"),expectedPass);
		  Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"),expectedConPass);
		  
		  driver.quit();
  }
}
