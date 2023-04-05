package genericLibrary;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtility {
	private WebDriver driver;

	public WebDriver openBrowserAndApplication(String url, long time) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		return driver;
	}

	public void mouseHoverToElement(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();

	}

	/**
	 * This method is used to handle dropdown by text
	 * 
	 * @param element
	 * @param text
	 */
	public void dropDown(WebElement element, String text) {
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	public void dropDown(String text, WebElement element) {
		Select s = new Select(element);
		s.selectByValue(text);
	}

	/**
	 * This method is used to switch to frame
	 * 
	 * @param index
	 */
	public void switchToFrame(String index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch back from the frame
	 */
	public void switchBackFromFrame() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used scroll the web page till the element
	 * 
	 * @param element
	 */
	public void scrollTillElement(Object element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method is used to take the screenshot
	 * 
	 * @param javaUtility
	 */
	public void getScreenshot(WebDriver driver, JavaUtility javaUtility, String classname) {
		String currentTime = javaUtility.currentTime();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/" + classname + "_" + currentTime + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to handle alert popup
	 */
	public void alertPopup() {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to get parent window title
	 */
	public String getParentWindow() {
		return driver.getWindowHandle();
	}

	/**
	 * This method is used to switch to specified window
	 * 
	 * @param windowID
	 */
	public void switchToWindow(String windowID) {
		driver.switchTo().window(windowID);
	}

	/**
	 * This method is used to handle child browser popup
	 * 
	 * @param expectedTitle
	 */
	public void handleChildBrowserPopup(Object expectedTitle) {
		Set<String> windowTitles = driver.getWindowHandles();
		for (String windowID : windowTitles) {
			driver.switchTo().window(windowID);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	/**
	 * This method is used to close browser
	 */
	public void closeBrowser() {
		driver.quit();
	}

}
