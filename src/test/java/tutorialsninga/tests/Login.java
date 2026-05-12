package tutorialsninga.tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.ChangePasswordPage;
import pages.ForgottenPassPage;
import pages.LandingPage;
import pages.LoginPage;
import pages.LogoutPage;
import tutorialsninga.base.Base;
import utils.CommonUtils;

public class Login extends Base {
	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	ForgottenPassPage forgottenPassPage;
	LogoutPage logoutPage;
	ChangePasswordPage changePasswordPage;

	@BeforeMethod
	public void setup() {
		driver = openBrowserAndAppliation();
		prop = CommonUtils.loadProperties();
		landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		loginPage = landingPage.selectLoginOption();

	}

	@AfterMethod
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		loginPage.enterEmail(CommonUtils.getValidEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLogedIn());
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		loginPage.enterEmail(prop.getProperty("incorrectEmail"));
		loginPage.enterPassword(prop.getProperty("incorrectPassword"));
		accountPage = loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmail() {
		loginPage.enterEmail(prop.getProperty("incorrectEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);
	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidPassword() {
		loginPage.enterEmail(CommonUtils.getValidEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("incorrectPassword"));
		accountPage = loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {
		loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);
	}

	@Test(priority = 6)
	public void verifyForgottenPasswordLink() {
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
		Assert.assertTrue(loginPage.isForgottenPassLinkPresent());
		forgottenPassPage = loginPage.clickOnForgottenPassLink();
		Assert.assertTrue(forgottenPassPage.didWeNavigateToForgottenPassPage());

	}

	@Test(priority = 7)
	public void verifyLoginUsingOnlyKeyboard() {
		Actions act = new Actions(driver);
		for (int i = 1; i <= 23; i++) {
			act.sendKeys(Keys.TAB).perform();
		}
		act.sendKeys(CommonUtils.getValidEmailRandomizeGenerator()).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("validPassword")).pause(Duration.ofSeconds(1))
				.sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER)
				.pause(Duration.ofSeconds(1)).perform();
		accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
		Assert.assertTrue(accountPage.isUserLogedIn());

	}

	@Test(priority = 8)
	public void verifyEmailPasswordPlaceholder() {
		String expectedEmail = "E-Mail Address";
		String expectedPassword = "Password";
		Assert.assertEquals(loginPage.getEmailPlaceholder(), expectedEmail);
		Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPassword);

	}

	@Test(priority = 9)
	public void verifyNotGetLogoutByPressingBrowserBackButton() {
		loginPage.enterEmail(CommonUtils.getValidEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		driver = navigateBack(driver);
		loginPage = new LoginPage(driver);
		accountPage = loginPage.clickOnMyAccountInformation();
		Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
	}

	@Test(priority = 10)
	public void verifyNotLoginafterLogout() {
		loginPage.enterEmail(CommonUtils.getValidEmailRandomizeGenerator());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		logoutPage = accountPage.clickOnLogoutOption();
		Assert.assertTrue(logoutPage.didWeNavigateToLogoutOption());
		driver = navigateBack(driver);
		accountPage = new AccountPage(driver);
		accountPage.clickOnEditYourAccountInformation();
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
	}

	@Test(priority = 11)
	public void verifyLoginUsingInactiveCredentials() {
		loginPage.enterEmail(prop.getProperty("inactiveAccount"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage = loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);

	}

	@Test(priority = 12)
	public void verifyUnssefullLoginAttempt() {
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("incorrectPassword"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		for (int i = 1; i <= 5; i++) {
			loginPage.clickOnLoginButton();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
		}
		String expectedResult = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
		Assert.assertEquals(loginPage.getLimitLoginWarning(), expectedResult);
	}

	@Test(priority = 13)
	public void verifyPasswordtextToogled() {
		String expectedType = "password";
		Assert.assertEquals(loginPage.getPasswordFieldType(), expectedType);

	}

	@Test(priority = 14)
	public void verifyPasswordTextCopyPossible() throws InterruptedException {
		String enterPass = prop.getProperty("validPassword");
		loginPage.enterPassword(enterPass);
		driver = loginPage.doubleclickONPasswordFiledAndCopyText(driver);
		driver = loginPage.pasteCopyTextIntoEmailTextfield(driver);
		Assert.assertNotEquals(loginPage.getCopiedTextFromEmailField(), enterPass);
	}

	@Test(priority = 15)
	public void verifyPasswordIsVisbleInPageSourse() {
		String passwordText = prop.getProperty("samplePassword");
		loginPage.enterPassword(passwordText);
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
		loginPage.clickOnLoginButton();
		Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
	}

	@Test(priority = 16)
	public void verifLoginAfterChangingPassword() {
		String oldPassword = null;
		String newPassword = null;

		oldPassword = prop.getProperty("validPassword2");
		newPassword = prop.getProperty("changedPssword2");
		loginPage.enterEmail(prop.getProperty("existingEmail2"));
		loginPage.enterPassword(oldPassword);
		accountPage = loginPage.clickOnLoginButton();
		changePasswordPage = accountPage.selectChangeYourPassOption();
		changePasswordPage.enterPassword(newPassword);
		changePasswordPage.enterConfirmPassword(newPassword);
		accountPage = changePasswordPage.clickOnContinueButton();
		String expectedMessage = "Success: Your password has been successfully updated.";
		Assert.assertEquals(accountPage.getMessage(), expectedMessage);
		logoutPage = accountPage.clickOnLogoutOption();
		logoutPage.clickOnMyAccountDropMenu();
		loginPage = logoutPage.clickOnloginOption();
		loginPage.enterEmail(prop.getProperty("existingEmail2"));
		loginPage.enterPassword(oldPassword);
		loginPage.clickOnLoginButton();
		String expectedErrorMsg = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(loginPage.getWarningMsg(), expectedErrorMsg);
		loginPage.clearPasswordField();
		loginPage.enterPassword(newPassword);
		accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLogedIn());
		CommonUtils.setProperties("validPassword2", newPassword, prop);
		CommonUtils.setProperties("changedPssword2", oldPassword, prop);
	}
	@Test(priority = 17)
	public void varifyingNavigstingToDifferentPagesFromLoginPage() {
		
	}
}