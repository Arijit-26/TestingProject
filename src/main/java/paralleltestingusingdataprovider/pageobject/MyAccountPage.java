package paralleltestingusingdataprovider.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import paralleltestingusingdataprovider.reusablecodes.ReusableComponents;

public class MyAccountPage extends ReusableComponents {

	ThreadLocal<WebDriver> driver;
	public MyAccountPage(ThreadLocal<WebDriver> driver2) {
	
		super(driver2);
		this.driver = driver2;	
		PageFactory.initElements(driver2.get(), this);
		// TODO Auto-generated constructor stub
	}
	
	String URL="https://magento.softwaretestingboard.com/customer/account/";
	
	@FindBy(xpath="//a[text()='My Orders']")
	WebElement myOrdersButton;
	

	public MyOrderPage clickOnMyOrders() {
		
		waitForURLToMatch(URL);
		myOrdersButton.click();
		MyOrderPage myOrderPage = new MyOrderPage(driver);
		return myOrderPage;
	}
	
}

