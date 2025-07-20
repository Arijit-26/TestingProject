
package paralleltestingusingdataprovider.testclasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import paralleltestingusingdataprovider.basetestclass.BaseTest;
import paralleltestingusingdataprovider.pageobject.CartPage;
import paralleltestingusingdataprovider.pageobject.LoginPage;
import paralleltestingusingdataprovider.pageobject.MyAccountPage;
import paralleltestingusingdataprovider.pageobject.MyOrderPage;
import paralleltestingusingdataprovider.pageobject.Order;
import paralleltestingusingdataprovider.pageobject.PlaceOrderPage;
import paralleltestingusingdataprovider.pageobject.ProductCatalogue;
import paralleltestingusingdataprovider.pageobject.ProductCatalogueMen;
import paralleltestingusingdataprovider.pageobject.ShippingPage;
import paralleltestingusingdataprovider.pageobject.SuccessfulOrderPage;

public class TestClassMain extends BaseTest {


	@Test(dataProvider="dataProvidingThroughExcel", retryAnalyzer =paralleltestingusingdataprovider.basetestclass.Retry.class)
	public void shopping(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws InterruptedException, IOException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(username,password);
		ProductCatalogueMen products = productCataloguePage.clickon("https://magento.softwaretestingboard.com/");
		CartPage cartPage = products.clickOnRequiredProduct(product);
		cartPage.selectFeaturesAndAddToCart();
		Assert.assertTrue(cartPage.checkInCart(product));
		ShippingPage shippingPage = cartPage.checkOut();
		PlaceOrderPage placeOrderPage = shippingPage.ship(comapnyName,line1,line2,line3,cityName,regionName,postCode,countryName,phone);
		SuccessfulOrderPage successfulOrderPage = placeOrderPage.placeOrder();
		Assert.assertTrue(successfulOrderPage.successMessageCheck("Thank you for your purchase!"));
		
	}
	
	@Test(dependsOnMethods = { "shopping" },dataProvider="dataProvidingThroughExcel")                                                 //The test 'checkMyOrders' depends on the method 'shopping()'. So when we run this test, first 'shopping()' will be executed and only then will 'checkMyOrders()' be executed, because 'checkMyOrders()' won't run until 'shopping()' is completed
	
	public void checkMyOrders(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) throws InterruptedException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(username,password);
		MyAccountPage myAccountPage = productCataloguePage.clickOnMyAccount();
		MyOrderPage myOrderPage=myAccountPage.clickOnMyOrders();
		Order order = myOrderPage.checkOrder();
		String productName = order.returnProdName();
		Assert.assertTrue(productName.equalsIgnoreCase(product));
		
	}
	
	
	
}
		