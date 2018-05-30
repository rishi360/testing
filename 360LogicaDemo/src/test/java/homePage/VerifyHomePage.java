package homePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyHomePage {

	// initialize variables
	String pageHeader;
	String subHeader;
	WebDriver driver;
	boolean productTable;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")
						+ "//src//test//resources//webDriver/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test
	public void testVerifyHomePage() {

		// launch the application
		driver.get("http://192.168.9.21:30006/");

		// verify page header
		pageHeader = driver.findElement(By.className("page-header")).getText();
		Assert.assertEquals("Welcome to the Microservices My Demo a Day",
				pageHeader, "page header doesn't match");

		// verify page sub header
		subHeader = driver.findElement(By.className("sub-header")).getText();
		Assert.assertEquals("Please see the product!", subHeader,
				"page sub header doesn't match");

		// verify product table
		try {
			driver.findElement(By.id("product-table")).isDisplayed();
			productTable = true;
		} catch (Exception ex) {
			productTable = false;
		}

		Assert.assertTrue(productTable,
				"Product table is not available on the page");
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
