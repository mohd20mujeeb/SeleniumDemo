package tutorialsninga.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC_RE_01 {
@Test
	public  void verifyRegisterWithMandatoryFields() {
		WebDriver driver=new ChromeDriver();
		//ctrl+shit+o for importing
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
          driver.findElement(By.id("input-confirm")).sendKeys("Mujeeb@123");
          driver.findElement(By.name("agree")).click();
          driver.findElement(By.xpath("//input[@value='Continue']")).click();
          Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
          String expectedHeading="Your Account Has Been Created!";
          Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);
          String actualDetailsOne="Congratulations! Your new account has been successfully created!";
          String actualDetailsTwo="You can now take advantage of member privileges to enhance your online shopping experience with us.";
          String actualDetailsThree="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
          String actualDetailsFour="contact us";
          String expectedProperDetails=driver.findElement(By.id("content")).getText();
          Assert.assertTrue(expectedProperDetails.contains(actualDetailsOne));
          Assert.assertTrue(expectedProperDetails.contains(actualDetailsTwo));
          Assert.assertTrue(expectedProperDetails.contains(actualDetailsThree));
          Assert.assertTrue(expectedProperDetails.contains(actualDetailsFour));
          driver.findElement(By.linkText("Continue")).click();
          Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
driver.quit();
}
	public  String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:","")+"@gmail.com";
	}

}
