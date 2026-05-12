package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeadersOption {
	WebDriver driver;

	public HeadersOption(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//a/i[@class='fa fa-phone']")
	private WebElement phoneIconOption;

	@FindBy(xpath = "//a/i[@class='fa fa-heart']")
	private WebElement heartIconOption;

	@FindBy(xpath = "//a[@title='Shopping Cart']")
	private WebElement shopingCartOption;

	@FindBy(xpath = "//span[.='Checkout']")
	private WebElement checkOutOption;
	
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
	
}
