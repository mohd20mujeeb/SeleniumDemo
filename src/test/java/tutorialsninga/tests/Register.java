package tutorialsninga.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pages.AccountPage;
import pages.AccountSuccessPage;
import pages.LandingPage;
import pages.LoginPage;
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
	LoginPage loginPage;

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
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedFirstNameWarning);
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastNameWarning);
		Assert.assertEquals(registerPage.getEmailWarning(), expectedemailwarning);
		Assert.assertEquals(registerPage.getTelphoneWarning(), expectedTelephoneWarning);
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
		Assert.assertEquals(registerPage.getPolicyWarning(), expectedPrivacyPolicyWarning);

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
		accountSuccessPage = registerPage.clickOnContinueButton();
		accountPage = accountSuccessPage.clickOnContinueButton();
		newsletterPage = accountPage.SelectSuscribeUnsuscribeNewsletterOption();
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
		accountSuccessPage = registerPage.clickOnContinueButton();
		accountPage = accountSuccessPage.clickOnContinueButton();
		newsletterPage = accountPage.SelectSuscribeUnsuscribeNewsletterOption();
		Assert.assertTrue(newsletterPage.getNewsLetterSuscription());
		Assert.assertTrue(newsletterPage.isNoNewsletterOptionSelected());

	}

	@Test(priority = 6)
	public void regierWithAllPossibleWays() {

		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());

		registerPage.clickOnMyAccount();
		loginPage = registerPage.selectLoginOption();

		loginPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
		registerPage.clickOnMyAccount();
		loginPage = registerPage.selectLoginOption();
		loginPage.clickOnRegisterOption();
		Assert.assertTrue(registerPage.didWeNavigateToRegisterPage());
	}

	@Test(priority = 7)
	public void verifyInalidRegisterPasswordEroor() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("missmatchingPass"));
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();

		String conPassWarMsg = "Password confirmation does not match password!";
		assertEquals(conPassWarMsg, registerPage.getConfirmPasswordWarning());
	}

	@Test(priority = 8)
	public void registeWithSameEmail() {

		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("existingEmail"));
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();
		String EmailErrorMsg = "Warning: E-Mail Address is already registered!";
		Assert.assertEquals(registerPage.getExistingEmailWarning(), EmailErrorMsg);

	}

	@Test(priority = 9)
	public void varifyRegisterWithInvalidEmail() throws InterruptedException, IOException {
		String browserName = "chrome";
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();
		Thread.sleep(3000);

		if (browserName.equals("chrome") || browserName.equals("edge")) {

			Assert.assertEquals(registerPage.getEmailValidationMsg(),
					"Please include an '@' in the email address. 'Mujeeb' is missing an '@'.");
		} else if (browserName.equals("firefox")) {

			Assert.assertEquals(registerPage.getEmailValidationMsg(), "Please enter an email address.");
		}
		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailTwo"));
		registerPage.clickOnContinueButton();
		Thread.sleep(3000);

		if (browserName.equals("chrome") || browserName.equals("edge")) {

			Assert.assertEquals(registerPage.getEmailValidationMsg(),
					"Please enter a part following '@'. 'Mujeeb@' is incomplete.");
		} else if (browserName.equals("firefox")) {

			Assert.assertEquals(registerPage.getEmailValidationMsg(), "Please enter an email address.");

		}

		registerPage.clearEmailField();
		registerPage.enterEmail(prop.getProperty("invalidEmailThree"));
		registerPage.clickOnContinueButton();

		String errorMsg = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(registerPage.getEmailWarning(), errorMsg);

	}

	@Test(priority = 10)
	public void varifyRegisterWithInvalidTelephone() {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("invalidTelephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();
		String ExpectedErrorMessage = "Invalid Telephone Number";
		boolean state = false;
		try {
			String actualWarningessage = registerPage.getTelphoneWarning();
			if (actualWarningessage.equals(ExpectedErrorMessage)) {
				state = true;
			}
		} catch (NoSuchElementException e) {
			state = false;
		}
		Assert.assertTrue(state);

	}

	@Test(priority = 11)
	public void registerUsingKeyboard() {
		Actions act = new Actions(driver);
		for (int i = 1; i <= 23; i++) {
			act.sendKeys(Keys.TAB).perform();
		}
		act.sendKeys(prop.getProperty("firstName")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("lastName")).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(CommonUtils.generateEmale()).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("telephone")).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword")).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.ARROW_LEFT).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.SPACE)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER)
				.pause(Duration.ofSeconds(1)).build().perform();
		accountSuccessPage = new AccountSuccessPage(driver);
		Assert.assertTrue(accountSuccessPage.didWeNavigateToAccountSuccessPage());
		Assert.assertTrue(accountSuccessPage.isUserLoggedIn());

	}

	@Test(priority = 12)
	public void verifyPlaceholder() {

		String expectedFN = "First Name";
		String expectedLN = "Last Name";
		String expectedEmail = "E-Mail";
		String expectedTelephone = "Telephone";
		String expectedPass = "Password";
		String expectedConPass = "Password Confirm";

		Assert.assertEquals(registerPage.getFirstNamePlaceholder(), expectedFN);
		Assert.assertEquals(registerPage.getLastNamePlaceholder(), expectedLN);
		Assert.assertEquals(registerPage.getEmailPlaceholder(), expectedEmail);
		Assert.assertEquals(registerPage.getTelephonePlaceholder(), expectedTelephone);
		Assert.assertEquals(registerPage.getPasswordPlaceholder(), expectedPass);
		Assert.assertEquals(registerPage.getConfirmPasswordPlaceholder(), expectedConPass);
	}

	@Test(priority = 13)
	public void verifyMandatoryFieldsSymbolAndColor() {
		String expectedContent = "\"* \"";
		String expectedColor = "rgb(255, 0, 0)";
		Assert.assertEquals(registerPage.getFirstNameLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getFirstNameColorContent(driver), expectedColor);

		Assert.assertEquals(registerPage.getLastNameLabelContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getLastNameLabelColor(driver), expectedColor);

		Assert.assertEquals(registerPage.getEmailNameLableContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getEmailNameLableColor(driver), expectedColor);

		Assert.assertEquals(registerPage.getTelephoneLableContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getTelephoneLableColor(driver), expectedColor);

		Assert.assertEquals(registerPage.getPassLableContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getPassLableColor(driver), expectedColor);

		Assert.assertEquals(registerPage.getConpassLableContent(driver), expectedContent);
		Assert.assertEquals(registerPage.getConpassLableColor(driver), expectedColor);

	}

	@Test(priority = 14)
	public void varifyRegisterByEnteringSpacce() {

		registerPage.enterFirstName(" ");
		registerPage.enterLastName(" ");
		registerPage.enterEmail(" ");
		registerPage.enterTelephone(" ");
		registerPage.enterPassword(" ");
		registerPage.enterConfirmPassword(" ");
		registerPage.selectYesNewsletterOption();
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();

		String expectedErrorFn = "First Name must be between 1 and 32 characters!";
		String expectedErrorLn = "Last Name must be between 1 and 32 characters!";
		String expectedErrorEmail = "E-Mail Address does not appear to be valid!";
		String expectedErrorTelephone = "Telephone must be between 3 and 32 characters!";
		String expectedErrorPass = "Password must be between 4 and 20 characters!";
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedErrorFn);
		Assert.assertEquals(registerPage.getLastNameWarning(), expectedErrorLn);
		Assert.assertEquals(registerPage.getEmailWarning(), expectedErrorEmail);
		Assert.assertEquals(registerPage.getTelphoneWarning(), expectedErrorTelephone);
		Assert.assertEquals(registerPage.getPasswordWarning(), expectedErrorPass);

	}

	@Test(priority = 15, dataProvider = "passwordSupplier")
	public void verifyPasswordFollowingStandard(String passwordText) {
		registerPage.enterFirstName(prop.getProperty("firstName"));
		registerPage.enterLastName(prop.getProperty("lastName"));
		registerPage.enterEmail(CommonUtils.generateEmale());
		registerPage.enterTelephone(prop.getProperty("invalidTelephone"));
		registerPage.selectYesNewsletterOption();
		registerPage.enterPassword(passwordText);
		registerPage.enterConfirmPassword(passwordText);
		registerPage.seletPrivacyPoliy();
		registerPage.clickOnContinueButton();

		String errormsg = "Password is not matching standard ";
		boolean state = false;
		try {
			String actualWarningMsg = registerPage.getPasswordWarning();
			if (actualWarningMsg.equals(errormsg)) {
				state = true;
			}
		} catch (NoSuchElementException e) {
			state = false;
		}
		Assert.assertTrue(state);

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] datapass() {
		Object[][] data = { { "12345" }, { "abcdefghi" }, { "abcd12345" }, { "ABCDE456#" }, { "abcd123$" } };
		return data;
	}

	@Test(priority = 16)
	public void verifyRegisteringAccountFieldsHeightWidthAligment() throws IOException {

		String expectedHeight = "34px";
		String expectedWidth = "701.25px";
		String actualFirstNameFieldHeight = registerPage.getFirstNameHeight();
		String expectedFirstNameFieldWidth = registerPage.getFirstNameWidth();
		Assert.assertEquals(actualFirstNameFieldHeight, expectedHeight);
		Assert.assertEquals(expectedFirstNameFieldWidth, expectedWidth);

		registerPage.enterFirstName("");
		registerPage.clickOnContinueButton();
		String expectedWarning = "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("a");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("ab");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnop");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());

		registerPage = new RegisterPage(driver);
		registerPage.clearFirstNameField();
		registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnopq");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(registerPage.getFirstNameWarning(), expectedWarning);

		// ---------------------

		registerPage = new RegisterPage(driver);
		String actualLastNameFieldHeight = registerPage.getLastNameHeight();
		String actualLastNameFieldWidth = registerPage.getLastNameWidth();
		Assert.assertEquals(actualLastNameFieldHeight, expectedHeight);
		Assert.assertEquals(actualLastNameFieldWidth, expectedWidth);

		expectedWarning = "Last Name must be between 1 and 32 characters!";
		registerPage.clearLastNameField();
		registerPage.enterLastName("");
		registerPage.clickOnContinueButton();
		Assert.assertEquals(
				registerPage.getLastNameWarning(),
				expectedWarning);

		registerPage = new RegisterPage(driver);
		registerPage.clearLastNameField();
		registerPage.enterLastName("a");
		registerPage.clickOnContinueButton();
		Assert.assertFalse(registerPage.isLastNameWarningDisplayed());

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("ab");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopq");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopabcdefghijklmnop");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		lastNameField = driver.findElement(By.id("input-lastname"));
		lastNameField.clear();
		lastNameField.sendKeys("abcdefghijklmnopabcdefghijklmnopq");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
				expectedWarning);

		WebElement emailField = driver.findElement(By.id("input-email"));

		String actualEmailFieldHeight = emailField.getCssValue("height");
		String actualEmailFieldWidth = emailField.getCssValue("width");

		Assert.assertEquals(actualEmailFieldHeight, expectedHeight);
		Assert.assertEquals(actualEmailFieldWidth, expectedWidth);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		emailField.clear();
		emailField.sendKeys("abcdefghijklmnopabcdefghijklmnopqabcdefghijklmnopabcdefghijklmno@gmail.com");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		// ----------------------------------------

		WebElement telephoneField = driver.findElement(By.id("input-telephone"));
		String actualTelephoneFieldHeight = telephoneField.getCssValue("height");
		String actualTelephoneFieldWidth = telephoneField.getCssValue("width");

		Assert.assertEquals(actualTelephoneFieldHeight, expectedHeight);
		Assert.assertEquals(actualTelephoneFieldWidth, expectedWidth);

		expectedWarning = "Telephone must be between 3 and 32 characters!";

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField.clear();
		telephoneField.sendKeys("");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("a");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("ab");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abc");
		continueButton.click();
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
					.isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcd");
		continueButton.click();
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
					.isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnop");
		continueButton.click();
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
					.isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnopabcdefghijklmnop");
		continueButton.click();
		try {
			Assert.assertFalse(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div"))
					.isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		telephoneField = driver.findElement(By.id("input-telephone"));
		telephoneField.clear();
		telephoneField.sendKeys("abcdefghijklmnopabcdefghijklmnopq");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				expectedWarning);

		// -----------------------
		WebElement passwordField = driver.findElement(By.id("input-password"));
		String actualPasswordFieldHeight = passwordField.getCssValue("height");
		String actualPasswordFieldWidth = passwordField.getCssValue("width");

		Assert.assertEquals(actualPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualPasswordFieldWidth, expectedWidth);

		expectedWarning = "Password must be between 4 and 20 characters!";

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField.clear();
		passwordField.sendKeys("");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("a");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("ab");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abc");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedWarning);

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcd");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcde");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghij");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghi");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghij");
		continueButton.click();
		try {
			Assert.assertFalse(
					driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).isDisplayed());
		} catch (NoSuchElementException e) {
			Assert.assertTrue(true);
		}

		continueButton = driver.findElement(By.xpath("//input[@value='Continue']"));
		passwordField = driver.findElement(By.id("input-password"));
		passwordField.clear();
		passwordField.sendKeys("abcdefghijabcdefghijk");
		continueButton.click();
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				expectedWarning);

		String actualConfirmPasswordFieldHeight = driver.findElement(By.id("input-confirm")).getCssValue("height");
		String actualConfirmPasswordFieldWidth = driver.findElement(By.id("input-confirm")).getCssValue("width");

		Assert.assertEquals(actualConfirmPasswordFieldHeight, expectedHeight);
		Assert.assertEquals(actualConfirmPasswordFieldWidth, expectedWidth);

		driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=account/register");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot,
				new File(System.getProperty("user.dir") + "\\Screenshot\\registerPageActualAligment.png"));

		Assert.assertFalse(CommonUtils.compareTwoScreenshots(
				System.getProperty("user.dir") + "\\Screenshot\\registerPageActualAligment.png",
				System.getProperty("user.dir") + "\\Screenshot\\expectedRegisterUI.png"));

	}

}
