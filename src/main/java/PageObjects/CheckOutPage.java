package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement  countryTextBox;

	@FindBy(xpath="//section[contains(@class,'ta-results')]")
	WebElement  countriesBox;
	@FindBy(xpath="//span[text()=' India']")
	WebElement  India;

	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement  placeOrder;

	public SuccessOrderPage placeOrder(String con) {
		countryTextBox.sendKeys(con);
		explicitWait2(countriesBox);
		India.click();
		placeOrder.click();

		return new SuccessOrderPage(driver);
	}

}
