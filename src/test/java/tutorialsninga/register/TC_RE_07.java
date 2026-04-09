package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_07 {
@Test
   public void regierWithAllPossibleWays() {
	WebDriver driver=new ChromeDriver();
	
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().window().maximize();
    driver.get("https://tutorialsninja.com/demo/");
    driver.findElement(By.xpath("//span[.='My Account']")).click();
    driver.findElement(By.linkText("Register")).click();
    Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
    
    driver.findElement(By.xpath("//span[.='My Account']")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.linkText("Continue")).click();
    Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
    
    driver.findElement(By.xpath("//span[.='My Account']")).click();
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.linkText("Register")).click();
    Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
    
    driver.quit();
    
    
    
    
}
}
