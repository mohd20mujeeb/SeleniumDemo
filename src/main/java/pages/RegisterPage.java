package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public void seletPrivacyPoliy() {
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

}
