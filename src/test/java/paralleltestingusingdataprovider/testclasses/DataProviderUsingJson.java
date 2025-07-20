package paralleltestingusingdataprovider.testclasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import paralleltestingusingdataprovider.basetestclass.BaseTest;
import paralleltestingusingdataprovider.pageobject.CartPage;
import paralleltestingusingdataprovider.pageobject.PlaceOrderPage;
import paralleltestingusingdataprovider.pageobject.ProductCatalogue;
import paralleltestingusingdataprovider.pageobject.ProductCatalogueMen;
import paralleltestingusingdataprovider.pageobject.ShippingPage;
import paralleltestingusingdataprovider.pageobject.SuccessfulOrderPage;

public class DataProviderUsingJson extends BaseTest {


@Test(dataProvider="getData")
public void shopping(HashMap<String,String> hmap) throws InterruptedException, IOException {

		ProductCatalogue productCataloguePage = newLoginPage.loginAction(hmap.get("email"),hmap.get("password"));                           
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(hmap.get("product"));
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(hmap.get("product")));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship(hmap.get("Companyname"),hmap.get("AdressLinea"),hmap.get("AdressLineb"),hmap.get("AdressLinec"),hmap.get("Cityname"),hmap.get("Regionname"),hmap.get("Postcode"),hmap.get("Countryname"),hmap.get("Phonenumber"));
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
}

@DataProvider                               
public Object[][] getData() throws IOException {
	
	
	
	List<HashMap<String,String>> data = readJsonFile(System.getProperty("user.dir")+"\\src\\test\\java\\paralleltestingusingdataprovider\\data\\credentials.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};                                           
	
	}
}
