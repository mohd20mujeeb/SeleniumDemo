package tutorialsninga.register;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import utils.CommonUtils;

public class TC_RE_19 extends Base{
	WebDriver driver;
	Properties prop;

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeMethod
	public void setup() {
        prop = CommonUtils.loadProperties();
        driver	=openBrowserAndAppliation();
		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test
	public void verifyTextfieldSpaceTriming() {
		
        SoftAssert softAssert= new SoftAssert();		
		String enteredFirstName = "      "+prop.getProperty("firstName")+"      ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = "      "+ prop.getProperty("lastName")+"     ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "       " + CommonUtils.generateEmale() + "        ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredTelephone = "    "+ prop.getProperty("telephone")+"    ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredTelephone);
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.linkText("Continue")).click();
		driver.findElement(By.linkText("Edit your account information")).click();
		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getDomAttribute("value"), enteredFirstName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getDomAttribute("value"), enteredLastName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getDomAttribute("value"), enteredEmail.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getDomAttribute("value"), enteredTelephone.trim());
		softAssert.assertAll();
	}

}
