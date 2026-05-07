package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement confirmField;

	@FindBy(name = "agree")
	private WebElement privacyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsLetter;

	@FindBy(xpath = "//div[.='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//div[.='Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//div[.='E-Mail Address does not appear to be valid!']")
	private WebElement emailWarning;

	@FindBy(xpath = "//div[.='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//div[.='Password must be between 4 and 20 characters!']")
	private WebElement passwordWarning;

	@FindBy(xpath = "//div[.='Warning: You must agree to the Privacy Policy!']")
	private WebElement policyWarning;

	@FindBy(xpath = "//input[@name='newsletter'][@value='0']")
	private WebElement newsletterNo;

	@FindBy(linkText = "Register")
	private WebElement registerBreadCrumb;

	@FindBy(xpath = "//span[.='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;

	@FindBy(xpath = "//div[.='Password confirmation does not match password!']")
	private WebElement confirmPasswordWarning;

	@FindBy(xpath = "//div[.='Warning: E-Mail Address is already registered!']")
	private WebElement existingEmailWarning;

	@FindBy(css = "label[for='input-firstname']")
	private WebElement firstNameLable;

	@FindBy(css = "label[for='input-lastname']")
	private WebElement lastNameLable;

	@FindBy(css = "label[for='input-email']")
	private WebElement emailNameLable;

	@FindBy(css = "label[for='input-telephone']")
	private WebElement telephoneLable;

	@FindBy(css = "label[for='input-password']")
	private WebElement passLable;

	@FindBy(css = "label[for='input-confirm']")
	private WebElement conpassLable;

	@FindBy(xpath = "//a/i[@class='fa fa-phone']")
	private WebElement phoneIconOption;

	@FindBy(xpath = "//a/i[@class='fa fa-heart']")
	private WebElement heartIconOption;

	@FindBy(xpath = "//a[@title='Shopping Cart']")
	private WebElement shopingCartOption;

	@FindBy(xpath = "//span[.='Checkout']")
	private WebElement checkOutOption;

	@FindBy(linkText = "Qafox.com")
	private WebElement qafozLogoOption;

	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement searchOption;

	@FindBy(linkText = "Account")
	private WebElement accountBreadcrumb;

	@FindBy(xpath = "//a/i[@class='fa fa-home']")
	private WebElement homeOption;
	
	@FindBy(xpath = "//a[@class='agree']")
	private WebElement privacyPolicyOption;
	
	@FindBy(xpath = "//button[.='×']")
	private WebElement closePrivacyPolicyDialogOption;
	
	@FindBy(xpath = "//button[.='×']")
	private WebElement xOption;
	
	@FindBy(xpath = "//h1[.='Register Account']")
	private WebElement registerHeading;
	
	private By xOptionPrivacyPoilicy=By.xpath("//button[.='×']");
	
	@FindBy(linkText = "Logout")
	private WebElement LogoutOption;

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmText) {
		confirmField.sendKeys(confirmText);
	}

	public void selectPrivacyPoliy() {
		privacyField.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsletterOption() {
		newsLetter.click();
	}

	public String getFirstNameWarning() {
		return firstNameWarning.getText();
	}

	public String getLastNameWarning() {
		return lastNameWarning.getText();
	}

	public String getEmailWarning() {
		return emailWarning.getText();
	}

	public String getTelphoneWarning() {
		return telephoneWarning.getText();
	}

	public String getPasswordWarning() {
		return passwordWarning.getText();
	}

	public String getPolicyWarning() {
		return policyWarning.getText();
	}

	public void selectNoNewsletterOption() {
		newsletterNo.click();
	}

	public boolean didWeNavigateToRegisterPage() {
		return registerBreadCrumb.isDisplayed();
	}

	public void clickOnRegisterBreadCrumb() {
		registerBreadCrumb.click();
	}

	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}

	public String getConfirmPasswordWarning() {
		return confirmPasswordWarning.getText();
	}

	public String getExistingEmailWarning() {
		return existingEmailWarning.getText();
	}

	public String getEmailValidationMsg() {
		return emailField.getDomProperty("validationMessage");
	}

	public void clearEmailField() {
		emailField.clear();
	}

	public String getFirstNamePlaceholder() {
		return firstNameField.getDomAttribute("placeholder");
	}

	public String getLastNamePlaceholder() {
		return lastNameField.getDomAttribute("placeholder");
	}

	public String getEmailPlaceholder() {
		return emailField.getDomAttribute("placeholder");
	}

	public String getTelephonePlaceholder() {
		return telephoneField.getDomAttribute("placeholder");
	}

	public String getPasswordPlaceholder() {
		return passwordField.getDomAttribute("placeholder");
	}

	public String getConfirmPasswordPlaceholder() {
		return confirmField.getDomAttribute("placeholder");
	}

	public String getFirstNameLabelContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String fnContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				firstNameLable);
		return fnContent;
	}

	public String getFirstNameColorContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String fnColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", firstNameLable);
		return fnColor;
	}

	public String getLastNameLabelContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String lnContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", lastNameLable);
		return lnContent;
	}

	public String getLastNameLabelColor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String lnColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", lastNameLable);
		return lnColor;
	}

	public String getEmailNameLableContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String emailContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				emailNameLable);
		return emailContent;
	}

	public String getEmailNameLableColor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String emailColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", emailNameLable);
		return emailColor;
	}

	public String getTelephoneLableContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String telephoneContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');",
				telephoneLable);
		return telephoneContent;
	}

	public String getTelephoneLableColor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String telephoneColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", telephoneLable);
		return telephoneColor;
	}

	public String getPassLableContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String passContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", passLable);
		return passContent;
	}

	public String getPassLableColor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String passColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", passLable);
		return passColor;
	}

	public String getConpassLableContent(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String conpassContent = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", conpassLable);
		return conpassContent;
	}

	public String getConpassLableColor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String conpassColor = (String) js.executeScript(
				"return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", conpassLable);
		return conpassColor;
	}

	public String getFirstNameHeight() {
		return firstNameField.getCssValue("height");
	}

	public String getFirstNameWidth() {
		return firstNameField.getCssValue("width");
	}

	public void clearFirstNameField() {
		firstNameField.clear();
	}

	public boolean isFirstNameWarningDisplayed() {
		boolean status = false;
		try {
			status = firstNameWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getLastNameHeight() {
		return lastNameField.getCssValue("height");
	}

	public String getLastNameWidth() {
		return lastNameField.getCssValue("width");
	}

	public void clearLastNameField() {
		lastNameField.clear();
	}

	public boolean isLastNameWarningDisplayed() {
		boolean status = false;
		try {
			status = lastNameWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getEmaleHeight() {
		return emailField.getCssValue("height");
	}

	public String getEmaleWidth() {
		return emailField.getCssValue("width");
	}

	public boolean isEmaleWarningDisplayed() {
		boolean status = false;
		try {
			status = emailWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getTelephoneHeight() {
		return telephoneField.getCssValue("height");
	}

	public String getTelephoneWidth() {
		return telephoneField.getCssValue("width");
	}

	public void clearTelephoneField() {
		telephoneField.clear();
	}

	public boolean isTelephoneWarningDisplayed() {
		boolean status = false;
		try {
			status = telephoneWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getPasswordHeight() {
		return passwordField.getCssValue("height");
	}

	public String getPasswordWidth() {
		return passwordField.getCssValue("width");
	}

	public void clearPasswordField() {
		passwordField.clear();
	}

	public boolean isPasswordWarningDisplayed() {
		boolean status = false;
		try {
			status = passwordWarning.isDisplayed();
		} catch (NoSuchElementException e) {
			status = false;
		}
		return status;
	}

	public String getConPasswordHeight() {
		return confirmField.getCssValue("height");
	}

	public String getConPasswordWidth() {
		return confirmField.getCssValue("width");
	}

	public boolean isPrivacyPolicySelected() {
		return privacyField.isSelected();
	}

	public String getPasswordType() {
		return passwordField.getAttribute("type");
	}

	public String getConfirmPasswordType() {
		return confirmField.getAttribute("type");
	}

	public ContactUsPage selectPhoneIconOption() {
		phoneIconOption.click();
		return new ContactUsPage(driver);
	}

	public LoginPage selectHeartIconOption() {
		heartIconOption.click();
		return new LoginPage(driver);
	}

	public ShoppingCartPage selectShoppingCartOption() {
		shopingCartOption.click();
		return new ShoppingCartPage(driver);
	}

	public ShoppingCartPage selectCheckoutOption() {
		checkOutOption.click();
		return new ShoppingCartPage(driver);
	}

	public LandingPage selectQfoxLogoOption() {
		qafozLogoOption.click();
		return new LandingPage(driver);
	}

	public SearchPage selectSearchOption() {
		searchOption.click();
		return new SearchPage(driver);
	}

	public LoginPage selectAccountBreadcrumb() {
		accountBreadcrumb.click();
		return new LoginPage(driver);
	}

	public LandingPage selectHomeoption() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", homeOption);
		return new LandingPage(driver);
	}
	
	public void clickOnPolicyOption() {
		privacyPolicyOption.click();
	}
	public boolean waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(WebDriver driver, int seconds) {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(xOptionPrivacyPoilicy));
	      return xOption.isDisplayed();
	}
	public void closePrivacyPolicyDialog() {
		xOption.click();
	}
	public String getRegisterHeading() {
		return registerHeading.getText();
	}
	public boolean isLogoutOptionDisplayed() {
		return LogoutOption.isDisplayed();
	}
	

}
