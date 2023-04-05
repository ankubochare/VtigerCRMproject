package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	@FindBy(xpath = "//img[@src='test/logo/vtiger-crm-logo.gif']")
	private WebElement logo;

	@FindBy(xpath = "//input[@name='user_name']")
	private WebElement usernametextfield;

	@FindBy(xpath = "//input[@name='user_password']")
	private WebElement passwordfield;
	@FindBy(xpath = "//input[@id=\"submitButton\"]")
	private WebElement loginbutton;

	public Loginpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogo() {
		return logo;
	}

	public void loginApplication(String username, String password) {

		usernametextfield.sendKeys(username);
		passwordfield.sendKeys(password);
		loginbutton.click();
	}
}




