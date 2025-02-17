package Automated.Tests;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Automated.components.BaseTest;
import Automated.components.Retry;
import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.ProductCataulogue;


public class NegativeTest extends BaseTest {

	String email = "raavan123@nomail.com";
	String password = "Kk@@@12345";
	String myProd = "ZARA COAT 3";

	ProductCataulogue poaductCata;
	CartPage carkpage;
	CheckOutPage checkout;

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void errorTest() {
		logpage.login(email, password);
		AssertJUnit.assertEquals("Incorrect email or password.", logpage.errormessage());

	}

	@Test(groups= {"ErrorHandling"})
	public void productErrorValidation() throws InterruptedException, IOException {

		poaductCata = new ProductCataulogue(driver);
		carkpage = new CartPage(driver);
		checkout = new CheckOutPage(driver);

		logpage.login("raavan123@nomail.com", "Kk@12345");

		poaductCata.addToKart(myProd);

		AssertJUnit.assertTrue(carkpage.validateProd("ZARA COAT 3"));

	}

}
