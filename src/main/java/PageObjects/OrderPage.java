package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//tr/td[2])[1]")
	List<WebElement> orderItems;

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement  Checkout;


	public boolean validateProd(String myProd) {
		Boolean mt  = orderItems.stream().anyMatch(s->s.getText().equalsIgnoreCase(myProd));
		//Assert.assertTrue(mt);
		return mt;

	}

}
