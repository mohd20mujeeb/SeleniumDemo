package tutorialsninga.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RE_16 {
@Test
     public void varifyRegisterByEnteringSpacce() {
	WebDriver driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().window().maximize();
    driver.get("https://tutorialsninja.com/demo/");
    driver.findElement(By.xpath("//span[.='My Account']")).click();
	  driver.findElement(By.linkText("Register")).click();
	  driver.findElement(By.id("input-firstname")).sendKeys(" ");
      driver.findElement(By.id("input-lastname")).sendKeys(" ");
      driver.findElement(By.id("input-email")).sendKeys(" ");
      driver.findElement(By.id("input-telephone")).sendKeys(" ");
      driver.findElement(By.id("input-password")).sendKeys(" ");
      driver.findElement(By.id("input-confirm")).sendKeys(" ");
      driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
      driver.findElement(By.name("agree")).click();
      driver.findElement(By.xpath("//input[@value='Continue']")).click();
      String expectedErrorFn="First Name must be between 1 and 32 characters!";
      String expectedErrorLn="Last Name must be between 1 and 32 characters!";
      String expectedErrorEmail="E-Mail Address does not appear to be valid!";
      String expectedErrorTelephone="Telephone must be between 3 and 32 characters!";
      String expectedErrorPass="Password must be between 4 and 20 characters!";
      Assert.assertEquals(driver.findElement(By.xpath("//div[.='First Name must be between 1 and 32 characters!']")).getText(), expectedErrorFn);
      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Last Name must be between 1 and 32 characters!']")).getText(), expectedErrorLn);
      Assert.assertEquals(driver.findElement(By.xpath("//div[.='E-Mail Address does not appear to be valid!']")).getText(), expectedErrorEmail);
      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Telephone must be between 3 and 32 characters!']")).getText(), expectedErrorTelephone);
      Assert.assertEquals(driver.findElement(By.xpath("//div[.='Password must be between 4 and 20 characters!']")).getText(), expectedErrorPass);
      driver.quit();
}
}
