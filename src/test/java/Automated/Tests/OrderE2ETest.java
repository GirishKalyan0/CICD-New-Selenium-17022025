package Automated.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AbstractComponents.AbstractComponent;

import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import Automated.components.BaseTest;
import Automated.components.Retry;
import PageObjects.CartPage;
import PageObjects.CheckOutPage;
import PageObjects.OrderPage;
import PageObjects.ProductCataulogue;
import PageObjects.SuccessOrderPage;

public class OrderE2ETest extends BaseTest {

	String email = "raavan123@nomail.com";
	String password = "Kk@12345";
	String myProd = "ZARA COAT 3";
	String country = "ind";
	String successText = "THANKYOU FOR THE ORDER.";

	ProductCataulogue poaductCata;
	CartPage carkpage;
	CheckOutPage checkout;
	SuccessOrderPage success;

	@Test(dataProvider = "getDataset", groups = "purchase",retryAnalyzer=Retry.class)
	public void orderZaraCoat(HashMap <String,String> input) throws InterruptedException, IOException {

		poaductCata = new ProductCataulogue(driver);
		carkpage = new CartPage(driver);
		checkout = new CheckOutPage(driver);
		success = new SuccessOrderPage(driver);

		logpage.login(input.get("email"), input.get("password"));

		poaductCata.addToKart(input.get("myprod"));

		AssertJUnit.assertTrue(carkpage.validateProd(input.get("myprod")));
		carkpage.checkOut();

		SuccessOrderPage yo = checkout.placeOrder(country);
		String xx = yo.validateText();
		AssertJUnit.assertEquals(xx, successText);

	}

	@Test//(dependsOnMethods = { "orderZaraCoat" })

	public void checkOrder() {
		AbstractComponent ab = logpage.login(email, password);
		OrderPage orders = ab.gotoOrders();
		Assert.assertTrue(orders.validateProd(myProd));
		
	}

	@DataProvider
	public Object[][] getDataset() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//
//		map.put("email", "raavan123@nomail.com");
//		map.put("password", "Kk@12345");
//		map.put("myprod", "ADIDAS ORIGINAL");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//
//		map2.put("email", "raavan123@nomail.com");
//		map2.put("password", "Kk@12345");
//		map2.put("myprod", "ZARA COAT 3");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/main/java/Resources/DataManager.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
		
		//return new Object[][] { {"raavan123@nomail.com", "Kk@12345","ADIDAS ORIGINAL"}, 
		//{"raavan123@nomail.com", "Kk@12345","ADIDAS ORIGINAL"} };
	}
	
	
}
