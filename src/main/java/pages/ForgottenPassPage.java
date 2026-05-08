package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPassPage {
	WebDriver driver;

	    public ForgottenPassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	    @FindBy(linkText = "Forgotten Password")
	    private WebElement forgottenPassBreadcrub;
	    
	    public boolean didWeNavigateToForgottenPassPage() {
	    	return forgottenPassBreadcrub.isDisplayed();
	    }
}
