package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Continue")
	private WebElement continueButton;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(linkText = "Login")
	private WebElement loginBreadcrumb;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[.='Warning: No match for E-Mail Address and/or Password.']")
	private WebElement warningMsg;

	@FindBy(xpath = "//div[.='Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.']")
	private WebElement LimitLoginwarningMsg;

	@FindBy(linkText = "Forgotten Password")
	private WebElement forgotenPassOption;

	@FindBy(xpath = "(//a[.='My Account'])[1]")
	private WebElement myAccountRightColoumnOption;

	public void clickOnContinueButton() {
		continueButton.click();
	}

	public boolean didWeNavigateToLoginPage() {
		return loginBreadcrumb.isDisplayed();
	}

	public void clickOnRegisterOption() {
		registerOption.click();
	}

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterPassword(String passText) {
		passwordField.sendKeys(passText);
	}

	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}

	public String getWarningMsg() {
		return warningMsg.getText();
	}

	public boolean isForgottenPassLinkPresent() {
		return forgotenPassOption.isDisplayed();
	}

	public ForgottenPassPage clickOnForgottenPassLink() {
		forgotenPassOption.click();
		return new ForgottenPassPage(driver);
	}

	public String getEmailPlaceholder() {
		return emailField.getDomAttribute("placeholder");
	}

	public String getPasswordPlaceholder() {
		return passwordField.getDomAttribute("placeholder");
	}

	public AccountPage clickOnMyAccountInformation() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", myAccountRightColoumnOption);
		return new AccountPage(driver);
	}

	public String getLimitLoginWarning() {
		return LimitLoginwarningMsg.getText();
	}

	public String getPasswordFieldType() {
		return passwordField.getDomAttribute("type");
	}

	public WebDriver doubleclickONPasswordFiledAndCopyText(WebDriver driver) {
    	Actions actions = new Actions(driver);
    	actions.doubleClick(passwordField).keyDown(Keys.CONTROL)
    	.sendKeys("c").keyUp(Keys.CONTROL).build().perform();
    	return driver;
    }
	public WebDriver pasteCopyTextIntoEmailTextfield(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.click(emailField).keyDown(Keys.CONTROL)
		.sendKeys("c").keyUp(Keys.CONTROL).build().perform();
		return driver;
	}
	public String getCopiedTextFromEmailField() {
		return emailField.getDomProperty("value");
	}
}
