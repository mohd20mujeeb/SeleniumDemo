package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {
	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement continueOption;

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPasswordField.sendKeys(confirmPasswordText);
	}
	public AccountPage clickOnContinueButton() {
		continueOption.click();
		return new AccountPage(driver);
	}
}
