package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
   WebDriver driver;
   public AccountPage(WebDriver driver) 
   {
	   this.driver=driver;
	   PageFactory.initElements(driver, this);
   }
   
   @FindBy(linkText = "Edit your account information")
   private WebElement editYourAccountInformationOption;
   
   @FindBy(linkText = "Subscribe / unsubscribe to newsletter")
   private WebElement suscribeUnsuscribeNewsLetterOption;
   
   public boolean didWeNavigateToAccountPage() 
   {
	   return editYourAccountInformationOption.isDisplayed();
   }
  
   public NewsletterPage SelectSuscribeUnsuscribeNewsletterOption() {
		   suscribeUnsuscribeNewsLetterOption.click();
		   return new NewsletterPage(driver);
		}
   public EditAccountInformationPage clickOnEditYourAccountInformation() {
	   editYourAccountInformationOption.click();
	   return new EditAccountInformationPage(driver);
   }
}
