package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_09 {
	@Test
public  void registeWithSameEmail() {
		
		
		WebDriver driver=new ChromeDriver();
				
			      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			      driver.manage().window().maximize();
			      driver.get("https://tutorialsninja.com/demo/");
			      driver.findElement(By.xpath("//span[.='My Account']")).click();
			      driver.findElement(By.linkText("Register")).click();
			      driver.findElement(By.id("input-firstname")).sendKeys("Arun");
			      driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
			      driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail.com");
			      driver.findElement(By.id("input-telephone")).sendKeys("09246812111");
			      driver.findElement(By.id("input-password")).sendKeys("12345");
			      driver.findElement(By.id("input-confirm")).sendKeys("12345");
			      driver.findElement(By.name("agree")).click();
			      driver.findElement(By.xpath("//input[@value='Continue']")).click();
			      String EmailErrorMsg="Warning: E-Mail Address is already registered!";
			      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Warning: E-Mail Address is already registered!']")).getText(), EmailErrorMsg);
			      // Assert.assertTrue(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']")).isDisplayed());

			      driver.quit();
}            
}
