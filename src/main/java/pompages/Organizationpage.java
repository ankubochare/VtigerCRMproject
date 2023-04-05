package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationpage {

	@FindBy(xpath = "//a[contains(@class,'hdrLink')]")
	private WebElement organizationHedar;

	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement plusimage;

	@FindBy(xpath = "//table[@class='lvt small']/tbody/tr[last()]/td/a[@title='Organizations']")
	private WebElement neworganization;

	public Organizationpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getPageHeaderText() {
		return organizationHedar.getText();
	}

	public void clickonOrganizationstab() {
		plusimage.click();
	}

	public String getNewOrganization() {
		return neworganization.getText();
	}
}
