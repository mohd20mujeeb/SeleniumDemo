package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TC_RE_24 {

	@Test
	public void verifyConfirmPass()
    {
		 WebDriver driver =new ChromeDriver();
			
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	      driver.manage().window().maximize();
	      driver.get("https://tutorialsninja.com/demo/");
	      driver.findElement(By.xpath("//span[.='My Account']")).click();
	      driver.findElement(By.linkText("Register")).click();
	      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
	      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
	      driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateEmale());
	      driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
	      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
	      //driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
	      driver.findElement(By.name("agree")).click();
	      driver.findElement(By.xpath("//input[@value='Continue']")).click();
	      String errormsg="Password confirmation does not match password!";
	      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']")).getText(), errormsg);
	      driver.quit();
	  
	      
    }
	
}
