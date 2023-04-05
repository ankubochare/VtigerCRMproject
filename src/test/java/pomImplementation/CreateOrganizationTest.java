package pomImplementation;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import genericLibrary.Autoconstant;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;
import pompages.CreatingnewOrganizationpage;
import pompages.Homepage;
import pompages.Loginpage;
import pompages.Organizationpage;

public class CreateOrganizationTest {
	public static void main(String[] args) {
		ExcelUtility excel = new ExcelUtility();
		JavaUtility java = new JavaUtility();
		PropertyFileUtility property = new PropertyFileUtility();

		WebDriverUtility webdriverutil = new WebDriverUtility();
		property.propertyFileInitialization(Autoconstant.PROPERTY_FILE_PATH);
		excel.excelFileInitialization(Autoconstant.EXCEL_FILE_PATH);

		Random random = new Random();
		int randomNum = random.nextInt(100);
		String url = property.getDataFromPropertyFile("url");
		String time = property.getDataFromPropertyFile("timeouts");
		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");
		WebDriver driver = webdriverutil.openBrowserAndApplication(url, Long.parseLong(time));

		Loginpage loginpage = new Loginpage(driver);
		Homepage homepage = new Homepage(driver);
		Organizationpage organization = new Organizationpage(driver);
		CreatingnewOrganizationpage creatingneworganizationpage = new CreatingnewOrganizationpage(driver);

		if (loginpage.getLogo().isDisplayed()) {
			System.out.println("Pass:Login page displayed");
		} else {
			System.out.println("Fail:Login page not found");
		}
		loginpage.loginApplication(username, password);
		// driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		String homeHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if (homepage.getPageHeaderText().contains("Home")) {
			System.out.println("Pass:Home page is displayed");
		} else {
			System.out.println("Fail:Home page not found");
		}

		homepage.clickonOrganizationstab();

		if (organization.getPageHeaderText().contains("Organizations")) {

			System.out.println("pass :Organizations page is displayed");
		} else {
		
			System.out.println("Fail:Organizations page not found");
		}
		organization.clickonOrganizationstab();

		// driver.findElement(By.xpath("//img[@title=\"Create
		// Organization...\"]")).click();
		// String newOrganizationHeader =
		// driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if (creatingneworganizationpage.getPageHeaderText().contains("Creating New Organization")) {

			System.out.println("Pass:Creating New Organization:Home page is displayed");
		} else {
		
			System.out.println("Fail:Creating New Organization:Home page not found");
		}
		Map<String, String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		String accountName = map.get("Organization Name") + java.generateRandomNumber(100);
		// driver.findElement(By.name("accountname")).sendKeys(accountName);
		creatingneworganizationpage.setOrganizationName(accountName);

		creatingneworganizationpage.selectIndustry(webdriverutil, map.get("Industry"));
		creatingneworganizationpage.clickGroupRadioButton();

		// WebElement industrydropdown = driver.findElement(By.name("industry"));
		// webdriverutil.dropDown(industrydropdown, map.get("Industry"));
		// Select s1 = new Select(industrydropdown);
		// s1.selectByVisibleText("Banking");

		// driver.findElement(By.xpath("//input[@value='T']")).click();

		creatingneworganizationpage.selectGroupFromDropdown(webdriverutil, map.get("Group"));

		// Select assign = new Select(assignto);
		// assign.selectByVisibleText("Team Selling");
		creatingneworganizationpage.clickSaveButton();
		// driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		// String newOrganizationCreated =
		// driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organization.getNewOrganization().contains("solnet16")) {

			System.out.println("Pass: Organization infomation  page is displayed");
		} else {

			System.out.println("Fail: Organization infomation page not found");
		}

		// driver.findElement(By.xpath("//a[@class=\"hdrLink\"]")).click();

		homepage.clickonOrganizationstab();
		if (organization.getPageHeaderText().contains("solnet16")) {
			System.out.println("pass : organization page displayed");
		} else {
			System.out.println("Fail: organization page not found");
		}
		
		String neworganizationName = driver
				.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td/a[@title='Organizations']"))
				.getText();
		System.out.println(neworganizationName);
		if (organization.getNewOrganization().equalsIgnoreCase(neworganizationName)) {
			System.out.println("Test Case Passed");
			excel.writeDataIntoExcel("TestData", "pass", Autoconstant.EXCEL_FILE_PATH, "Creat Organization");
		} else {
			System.out.println("Test case Failed");
			excel.writeDataIntoExcel("TestData", "Fail", Autoconstant.EXCEL_FILE_PATH, "Creat Organization");
		}
		// WebElement img =
		// driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		// webdriverutil.mouseHoverToElement(img);
		// Actions a = new Actions(driver);
		// a.moveToElement(img).perform();

		homepage.SighOutApplication(webdriverutil);
		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webdriverutil.closeBrowser();
		excel.closeExcel();

	}





}
