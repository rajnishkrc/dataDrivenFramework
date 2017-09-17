package ddf;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import excelPackage.ReadWriteExcel;

public class Test {
	static WebDriver driver;

	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;

		} catch (Exception e) {

			return false;
		}

	}

	public static void main(String[] args) {

		ReadWriteExcel rw = new ReadWriteExcel();
		rw.readExcel();

		System.out.println(rw.password);
		System.out.println(rw.userName);

		String userName = null;
		String password = null;

		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		String baseUrl = "http://hms.techcanvass.co.in/";
		for (int i = 0; i < 2; i++) {

			userName = rw.userName.get(i);
			password = rw.password.get(i);

			driver.navigate().to(baseUrl);

			driver.findElement(By.id("txtUserName")).sendKeys(userName);
			driver.findElement(By.id("txtPassword")).sendKeys(password);
			driver.findElement(By.id("LoginButton")).click();

			String actualUser = driver.findElement(By.id("ctl00_lblloginuser")).getText();

			Assert.assertEquals(userName, actualUser);

			if (isAlertPresent()) {
				String message = driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();
				driver.close();

				Assert.fail("Login Failed. Alert message: " + message);

			}

			System.out.println("Test" + (i + 1) + "Ran");

			// driver.close();
			// driver.quit();

		}
	}

}
