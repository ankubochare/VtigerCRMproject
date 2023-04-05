package pompages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericLibrary.WebDriverUtility;

public class CreatingnewOrganizationpage {

	@FindBy(xpath = "\"//span[@class=\\\"lvtHeaderText\\\"]\"")
	private WebElement PageHeader;

	@FindBy(name = "accountname")
	private WebElement OrgName;
	@FindBy(name = "industry")
	private WebElement industrydropdown;

	@FindBy(xpath = "//input[@value='T']")
	private WebElement groupradiobutton;
	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement assignTodropdown;
	@FindBy(xpath = "//input[@accesskey='S']")
	private WebElement saveButton;

	public void setOrganizationName(String organizationName) {
		OrgName.sendKeys(organizationName);
	}

	public void selectIndustry(WebDriverUtility webdriverutil, String industry) {
		webdriverutil.dropDown(industrydropdown, industry);
	}

	public void clickGroupRadioButton() {
		groupradiobutton.click();
	}

	public void selectGroupFromDropdown(WebDriverUtility webdriverutil, String group) {
		webdriverutil.dropDown(assignTodropdown, group);
	}

	public void clickSaveButton() {
		saveButton.click();

	}

	public CreatingnewOrganizationpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getPageHeaderText() {
		return PageHeader.getText();
	}
}
