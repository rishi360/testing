package homePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	WebDriverWait wait;

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
		
		wait= new WebDriverWait(driver,30);

		// verify page header
		WebElement pageHeader= driver.findElement(By.className("page-header"));
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
		Assert.assertEquals("Welcome to the Acuma Devops Capabilty!",
				pageHeader.getText(), "Page Header doesn't match");

		// verify page sub header
		WebElement subHeader= driver.findElement(By.className("sub-header"));
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
		Assert.assertEquals("Please see a product!", subHeader.getText(),
				"Page Sub Header doesn't match");

		// verify product table
		List<WebElement> productTable= driver.findElements(By.xpath("//table[@id='product-table']/tbody/tr"));
		wait.until(ExpectedConditions.visibilityOfAllElements(productTable));
		boolean status = false;
		if(productTable.size()>0){
			status= true;
		}
		Assert.assertTrue(status, "Data is not present in the table");
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
}
