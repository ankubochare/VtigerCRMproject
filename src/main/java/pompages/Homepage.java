package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class Homepage {

	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement pageHeaderText;

	@FindBy(xpath = "//a[.='Organizations']")
	private WebElement organizationsTab;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorimg;

	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signout;

	public Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getPageHeaderText() {
		return pageHeaderText.getText();
	}

	public void clickonOrganizationstab() {
		organizationsTab.click();
	}

	public void SighOutApplication(WebDriverUtility webdriverutil) {
		webdriverutil.mouseHoverToElement(administratorimg);
		signout.click();
	}

}
