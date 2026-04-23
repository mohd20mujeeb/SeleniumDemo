package tutorialsninga.register;

import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TC_RE_03 extends Base{

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setup() {
		prop=CommonUtils.loadProperties();
		driver	=	openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void verifyRegisterAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(generateEmale());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);
		String actualDetailsOne = "Congratulations! Your new account has been successfully created!";
		String actualDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String actualDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String actualDetailsFour = "contact us";
		String expectedProperDetails = driver.findElement(By.id("content")).getText();
		Assert.assertTrue(expectedProperDetails.contains(actualDetailsOne));
		Assert.assertTrue(expectedProperDetails.contains(actualDetailsTwo));
		Assert.assertTrue(expectedProperDetails.contains(actualDetailsThree));
		Assert.assertTrue(expectedProperDetails.contains(actualDetailsFour));
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}

	public String generateEmale() {
		return new Date().toString().replaceAll(" ", "").replaceAll("\\:", "") + "@gmail.com";
	}
}
