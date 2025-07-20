package paralleltestingusingdataprovider.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import paralleltestingusingdataprovider.reusablecodes.ReusableComponents;

public class PlaceOrderPage extends ReusableComponents{

	ThreadLocal<WebDriver> driver;
	public PlaceOrderPage(ThreadLocal<WebDriver> driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2.get(), this);
	}
	
	String URL ="https://magento.softwaretestingboard.com/checkout/#payment";
	
	By locator1 = By.cssSelector(".checkout");
	By locator2 = By.cssSelector(".loading-mask");
	
	@FindBy(css=".checkout")
	WebElement checkOutButton;
	
	public SuccessfulOrderPage placeOrder() {
		
		waitForURLToMatch(URL);
		elementIsPresent(locator1);
		elementIsClickable(locator1);
		elementIsInvisible(locator2);
		checkOutButton.click();
		SuccessfulOrderPage successfulOrderPage = new SuccessfulOrderPage(driver);
		return successfulOrderPage;
	}

}
