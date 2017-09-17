package ddf;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class NewTest {
	WebDriver driver;

	@Test(dataProvider = "dp")
	public void f(String userName, String password) {
		
		String baseUrl = "http://hms.techcanvass.co.in/";
		driver.navigate().to(baseUrl);
		driver.findElement(By.id("txtUserName")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("LoginButton")).click();

		String actualUser = driver.findElement(By.id("ctl00_lblloginuser")).getText();

		Assert.assertEquals(userName, actualUser);

	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "Pradnya", "1994" }, new Object[] { "asok", "asdb" }, };
	}
}
