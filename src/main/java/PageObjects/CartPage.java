package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement  Checkout;
	
	
	
	public List<WebElement> kartItems() {
		return cartItems;
	}
	
	public boolean validateProd(String myProd) {
		Boolean mt  = kartItems().stream().anyMatch(s->s.getText().equalsIgnoreCase(myProd));
		return mt;
		
	}
	
	public void checkOut() {
		explicitWait4(Checkout);
		Checkout.click();
	}

}
