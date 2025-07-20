package paralleltestingusingdataprovider.testclasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import paralleltestingusingdataprovider.basetestclass.BaseTest;
import paralleltestingusingdataprovider.pageobject.CartPage;
import paralleltestingusingdataprovider.pageobject.MyAccountPage;
import paralleltestingusingdataprovider.pageobject.MyOrderPage;
import paralleltestingusingdataprovider.pageobject.Order;
import paralleltestingusingdataprovider.pageobject.PlaceOrderPage;
import paralleltestingusingdataprovider.pageobject.ProductCatalogue;
import paralleltestingusingdataprovider.pageobject.ProductCatalogueMen;
import paralleltestingusingdataprovider.pageobject.ShippingPage;
import paralleltestingusingdataprovider.pageobject.SuccessfulOrderPage;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestClassForLoadTesting extends BaseTest{
	
	String username;
	String password; 
	String product;
	String comapnyName;
	String line1;
	String line2;
	String line3; 
	String cityName; 
	String regionName;
	String postCode; 
	String countryName;
	String phone;
	
	public TestClassForLoadTesting(String username,String password, String product,String comapnyName,String line1,String line2, String line3, String cityName, String regionName, String postCode, String countryName,String phone) {
		
		this.username = username;
		this.password=password;
		this.product=product;
		this.comapnyName=comapnyName;
		this.line1=line1;
		this.line2=line2;
		this.line3=line3;
		this.cityName=cityName;
		this.regionName=regionName;
		this.postCode=postCode;
		this.countryName=countryName;
		this.phone=phone;
	}
	
	@Test
	public void shopping() throws InterruptedException, IOException {
		
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
	
	@Test(dependsOnMethods = { "shopping" })                                                
	public void checkMyOrders() throws InterruptedException {
		
		ProductCatalogue productCataloguePage = newLoginPage.loginAction(username,password);
		MyAccountPage myAccountPage = productCataloguePage.clickOnMyAccount();
		MyOrderPage myOrderPage=myAccountPage.clickOnMyOrders();
		Order order = myOrderPage.checkOrder();
		String productName = order.returnProdName();
		Assert.assertTrue(productName.equalsIgnoreCase(product));
		
	}
}