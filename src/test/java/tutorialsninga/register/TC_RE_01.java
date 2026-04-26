package tutorialsninga.register;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.LandingPage;
import pages.RegisterPage;
import utils.CommonUtils;

public class TC_RE_01 extends Base {

	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setup() {

		driver = openBrowserAndAppliation();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		registerPage = landingPage.selectRegisterOption();

	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void verifyRegisterWithMandatoryFields() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.seletPrivacyPoliy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		String expectedHeading = "Your Account Has Been Created!";
		Assert.assertEquals(accountSuccessPage.getPageHeading(), expectedHeading);
		String expectedDetailsOne = "Congratulations! Your new account has been successfully created!";
		String expectedDetailsTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedDetailsThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expectedDetailsFour = "contact us";
		String actualProperDetails = accountSuccessPage.getPageContent();
		Assert.assertTrue(actualProperDetails.contains(expectedDetailsOne));
		Assert.assertTrue(actualProperDetails.contains(expectedDetailsTwo));
		Assert.assertTrue(actualProperDetails.contains(expectedDetailsThree));
		Assert.assertTrue(actualProperDetails.contains(expectedDetailsFour));

		accountPage = accountSuccessPage.clickOnContinueButton();
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());

	}
}
