package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCataulogue extends AbstractComponent {

	WebDriver driver;

	public ProductCataulogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> allProdts;
	@FindBy(xpath="//div[@id='toast-container']")
	WebElement  toast;
	@FindBy(css=".ng-animating")
	WebElement  spinner;
	@FindBy(xpath="//button[contains(@routerlink,'cart')]")
	WebElement  cart;

	By productsDis = By.cssSelector(".mb-3");
	By addtoKart = By.cssSelector(".card-body button:last-of-type");

	public List<WebElement> productList() {
		explicitWait(productsDis);
		return allProdts;
	}

	public WebElement getProdByName(String myProd) throws InterruptedException {
		WebElement prod = productList().stream().filter(s -> s.findElement(By.cssSelector("b")).getText()
				.equals(myProd)).findFirst().orElse(null);
		return prod;

	}

	public void addToKart(String myProd) throws InterruptedException {
		WebElement prod = getProdByName(myProd);
		prod.findElement(addtoKart).click();
		// explicitWait2(toast);
		// explicitWait3(spinner);
		Thread.sleep(5000);
		cart.click();
	}

}
