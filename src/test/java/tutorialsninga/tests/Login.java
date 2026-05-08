package tutorialsninga.tests;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.ForgottenPassPage;
import pages.LandingPage;
import pages.LoginPage;
import tutorialsninga.base.Base;
import utils.CommonUtils;

public class Login extends Base {
	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	LoginPage loginPage;
	AccountPage accountPage;
	ForgottenPassPage forgottenPassPage;

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
		String expectedPassword="Password";
		Assert.assertEquals(loginPage.getEmailPlaceholder(), expectedEmail);
		Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPassword);
		
	}
}
