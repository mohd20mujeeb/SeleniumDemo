package tutorialsninga.register;



import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_08 {
	@Test
public  void verifyInalidRegisterPasswordEroor() {
		
		
		WebDriver driver=new ChromeDriver();
				
			      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			      driver.manage().window().maximize();
			      driver.get("https://tutorialsninja.com/demo/");
			      driver.findElement(By.xpath("//span[.='My Account']")).click();
			      driver.findElement(By.linkText("Register")).click();
			      driver.findElement(By.id("input-firstname")).sendKeys("Mohd");
			      driver.findElement(By.id("input-lastname")).sendKeys("Mujeeb");
			      driver.findElement(By.id("input-email")).sendKeys(generateEmale());
			      driver.findElement(By.id("input-telephone")).sendKeys("8858476236");
			      driver.findElement(By.id("input-password")).sendKeys("Mujeeb@123");
			      driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@12");
			      driver.findElement(By.name("agree")).click();
			      driver.findElement(By.xpath("//input[@value='Continue']")).click();
			  
			      Assert.assertTrue(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']")).isDisplayed());
               
			      //Assert.assertEquals(driver.findElement(By.xpath("//div[.='Password confirmation does not match password!']")).getText(), ); 
			      driver.quit();
}              
                public   String generateEmale() {
				return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
}
}
