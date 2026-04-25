package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
  WebDriver driver;
  
  public RegisterPage(WebDriver driver)
  {
	  this.driver=driver;
	  PageFactory.initElements(driver, this);
  }
 @FindBy(id="input-firstName")
 private WebElement firstNameField;
  
 @FindBy(id="input-lastname")
 private WebElement lastNameField;
 
 @FindBy(id="input-email")
 private WebElement emailField;
 
 @FindBy(id="input-telephone")
 private WebElement telephoneField;
 
 @FindBy(id="input-password")
 private WebElement passwordField;
 
 @FindBy(id="input-confirm")
 private WebElement confirmField;
 
 @FindBy(id="agree")
 private WebElement privacyFieldField;
 
 @FindBy(xpath ="//input[@value='Continue']")
 private WebElement continueButton;
 
  public void enterFirstName(String firstNameText) 
  {
	  firstNameField.sendKeys(firstNameText);
  }
  
  public void enterLastName(String lastNameText) 
  {
	  lastNameField.sendKeys(lastNameText);
  }
  public void enterEmail(String emailText) 
  {
	  emailField.sendKeys(emailText);
  }
  public void enterTelephone(String telephoneText) 
  {
	  telephoneField.sendKeys(telephoneText);
  }
  public void enterPassword(String passwordText) 
  {
	  passwordField.sendKeys(passwordText);
  }public void confirmPassword(String confirmText) 
  {
	  confirmField.sendKeys(confirmText);
  }
  public void seletPrivacyPoliy() 
  {
	  confirmField.click();
  }
  public void clickOnContinueButton() 
  {
	  continueButton.click();
  }


}
