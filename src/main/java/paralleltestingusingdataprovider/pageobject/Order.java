package paralleltestingusingdataprovider.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import paralleltestingusingdataprovider.reusablecodes.ReusableComponents;

public class Order extends ReusableComponents{

	ThreadLocal<WebDriver> driver;
	
	public Order(ThreadLocal<WebDriver> driver2) {
		super(driver2);
		this.driver = driver2;	
		PageFactory.initElements(driver2.get(), this);
	}

	
	@FindBy(xpath="//td[@class='col name']/strong")
	WebElement prodName;
	
	public String returnProdName() throws InterruptedException {
		
		Thread.sleep(5000);
		return prodName.getText();
	}
	
}
