package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessOrderPage {

	WebDriver driver;

	public SuccessOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath="//tbody//h1")
	WebElement  SuccessText;


	public String validateText() throws InterruptedException {
		Thread.sleep(2000);
		String text=SuccessText.getText();
		return text;
	}
}


