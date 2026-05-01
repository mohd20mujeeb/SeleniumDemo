package tutorialsninga.tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.LandingPage;
import pages.NewsletterPage;
import pages.RegisterPage;
import utils.CommonUtils;

public class Register extends Base {

	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	AccountPage accountPage;
	NewsletterPage newsletterPage;

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

	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFields() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
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

	@Test(priority = 2)
	public void verifyRegisterAllFields() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());

		String expectedProperDetailsOne = "Your Account Has Been Created!";
		String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
		String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String expectedProperDetailsFive = "contact us";
		String actualProperDetails = accountSuccessPage.getPageContent();
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
		accountPage = accountSuccessPage.clickOnContinueButton();
		accountPage.didWeNavigateToAccountPage();

	}
	
	@Test(priority = 3)
	public void verifyErrormessageAllFields() {
	    registerPage.clickOnContinueButton();
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedemailwarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		Assert.assertEquals(registerPage.getFirstNameWarning(),expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getLastNameWarning(),expectedLastNameWarning);
		Assert.assertEquals(registerPage.getEmailWarning(),expectedemailwarning);
		Assert.assertEquals(registerPage.getTelphoneWarning(),expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getPasswordWarning(),expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPolicyWarning(),expectedPrivacyPolicyWarning);

	}
	
	@Test(priority = 4)
	public void verifyRegisterNewsletter() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectYesNewsletterOption();
        registerPage.seletPrivacyPoliy();
	    accountSuccessPage=registerPage.clickOnContinueButton();
		accountPage=accountSuccessPage.clickOnContinueButton();
		newsletterPage=accountPage.SelectSuscribeUnsuscribeNewsletterOption();
		Assert.assertTrue(newsletterPage.getNewsLetterSuscription());
		Assert.assertTrue(newsletterPage.isYesNewsletterOptionSelected());
	}
	
	@Test(priority = 5)
	public void verifyRegisterNewsletterNo() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
	    registerPage.selectNoNewsletterOption();
	    registerPage.seletPrivacyPoliy();
	    accountSuccessPage=registerPage.clickOnContinueButton();
		accountPage=accountSuccessPage.clickOnContinueButton();
		newsletterPage=accountPage.SelectSuscribeUnsuscribeNewsletterOption();
		Assert.assertTrue(newsletterPage.getNewsLetterSuscription());
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionSelected());

	}
	
	@Test(priority = 6)
	public void regierWithAllPossibleWays() {

		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());

		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());

		driver.findElement(By.xpath("//span[.='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.linkText("Register")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());

		

	}

}
