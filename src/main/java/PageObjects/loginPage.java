package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class loginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public loginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@type='email']")
	WebElement emailbox;
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement passwordbox;
	@FindBy(xpath="//input[@id='login']")
	WebElement submitbtn;
	@FindBy(xpath="//div[contains(@class,'flyInOut')]")
	WebElement errormessge;


	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String errormessage() {
		explicitWait2(errormessge);
		String messgae =errormessge.getText();
		return messgae;
	}
	
	public AbstractComponent login(String email,String password) {
		
		emailbox.sendKeys(email);
		passwordbox.sendKeys(password);
		submitbtn.click();
		AbstractComponent ab = new AbstractComponent(driver);
		return ab;
		
		
		
	}

}
