package paralleltestingusingdataprovider.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import paralleltestingusingdataprovider.reusablecodes.ReusableComponents;

public class SuccessfulOrderPage extends ReusableComponents {

	ThreadLocal<WebDriver> driver;
	public SuccessfulOrderPage(ThreadLocal<WebDriver> driver2) {
		super(driver2);
		this.driver = driver2;
		PageFactory.initElements(driver2.get(), this);	
	}

	String URL = "https://magento.softwaretestingboard.com/checkout/onepage/success/";
	
	@FindBy(xpath="//h1/span")
	WebElement successMessage;
	
	public boolean successMessageCheck(String realMessage) {
		
		waitForURLToMatch(URL);
		String message = successMessage.getText();
		System.out.println(message);
		return message.equalsIgnoreCase(realMessage);
	}
}
