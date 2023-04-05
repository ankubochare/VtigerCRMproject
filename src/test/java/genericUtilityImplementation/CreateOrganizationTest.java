package genericUtilityImplementation;

import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import genericLibrary.Autoconstant;
import genericLibrary.ExcelUtility;
import genericLibrary.JavaUtility;
import genericLibrary.PropertyFileUtility;
import genericLibrary.WebDriverUtility;

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
		WebDriver driver = webdriverutil.openBrowserAndApplication(url, Long.parseLong(time));

		/*
		 * WebDriverManager.chromedriver().setup(); WebDriver driver = new
		 * ChromeDriver(); driver.manage().window().maximize();
		 * driver.get("http://localhost:8888/");
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 */

		WebElement logo = driver.findElement(By.xpath("//img[@src='test/logo/vtiger-crm-logo.gif']"));
		if (logo.isDisplayed()) {
			System.out.println("Pass:Login page displayed");
		} else {
			System.out.println("Fail:Login page not found");
		}

		String username = property.getDataFromPropertyFile("username");
		String password = property.getDataFromPropertyFile("password");

		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id=\"submitButton\"]")).click();
		String homeHeader = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		if (homeHeader.contains("Home")) {
			System.out.println("Pass:Home page is displayed");
		} else {
			System.out.println("Fail:Home page not found");
		}


		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		String OrganizationHeader = driver.findElement(By.xpath("//a[contains(@class,'hdrLink')]")).getText();
		if (OrganizationHeader.contains("Organizations")) {

			System.out.println("pass :Organizations page is displayed");
		} else {
		
			System.out.println("Fail:Organizations page not found");
		}
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		String newOrganizationHeader = driver.findElement(By.xpath("//span[@class=\"lvtHeaderText\"]")).getText();
		if (newOrganizationHeader.contains("Creating New Organization")) {

			System.out.println("Pass:Creating New Organization:Home page is displayed");
		} else {
		
			System.out.println("Fail:Creating New Organization:Home page not found");
		}
		Map<String, String> map = excel.fetchMultipleDataBasedOnKeyFromExcel("TestData", "Create Organization");
		String accountName = map.get("Organization Name") + java.generateRandomNumber(100);
		driver.findElement(By.name("accountname")).sendKeys(accountName);

		WebElement industrydropdown = driver.findElement(By.name("industry"));
		webdriverutil.dropDown(industrydropdown, map.get("Industry"));
		// Select s1 = new Select(industrydropdown);
		// s1.selectByVisibleText("Banking");

		driver.findElement(By.xpath("//input[@value='T']")).click();

		WebElement assignto = driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		webdriverutil.dropDown(assignto, map.get("Group"));
		// Select assign = new Select(assignto);
		// assign.selectByVisibleText("Team Selling");

		driver.findElement(By.xpath("//input[@accesskey='S']")).click();

		String newOrganizationCreated = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (newOrganizationCreated.contains("solnet16")) {

			System.out.println("Pass: Organization infomation  page is displayed");
		} else {

			System.out.println("Fail: Organization infomation page not found");
		}

		driver.findElement(By.xpath("//a[@class=\"hdrLink\"]")).click();

		WebElement neworganization = driver
				.findElement(By.xpath("//table[@class='lvt small']/tbody/tr[last()]/td/a[@title='Organizations']"));
		if (neworganization.getText().contains("solnet16")) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test case Failed");
		}
		WebElement img = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverutil.mouseHoverToElement(img);
		// Actions a = new Actions(driver);
		// a.moveToElement(img).perform();

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webdriverutil.closeBrowser();
		excel.closeExcel();

	}





}
