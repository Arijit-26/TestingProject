package paralleltestingusingdataprovider.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import paralleltestingusingdataprovider.reusablecodes.ReusableComponents;

public class LoginPage extends ReusableComponents {

	ThreadLocal<WebDriver> driver;                           
	
	  
	public LoginPage(ThreadLocal<WebDriver> webDriver) {
		super(webDriver);					     
		this.driver = webDriver;                
		PageFactory.initElements(webDriver.get(), this);     
		  
		
	}
	
	  
	
	@FindBy(id="email")				  
	WebElement emailField;         	  
	  
	  
	  
	@FindBy(id="pass")
	WebElement pwdField;
	
	@FindBy(id="send2")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@role='alert']")
	WebElement errorMessage;
	
	
	public void goTo()
	{
		driver.get().get("https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/");
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	public ProductCatalogue loginAction(String email,String password) {
		scrollbypixels();
		emailField.sendKeys(email);
		pwdField.sendKeys(password);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	
	public String wrongCredentials() {
		waitTillVisible(errorMessage);
		return errorMessage.getText();
	}
}
