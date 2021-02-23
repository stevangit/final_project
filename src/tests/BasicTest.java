package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.LocationPopUpPage;
import pages.LoginPage;


public class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected LoginPage loginPage;
	protected LocationPopUpPage loginPopUpPage;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver_lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 10);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.loginPage = new LoginPage(driver, waiter, js);
		this.loginPopUpPage = new LocationPopUpPage(driver, waiter, js);
	}
	
	@AfterMethod
	public void after() {
		
	}
	
	@AfterClass
		public void clean(ITestResult result) throws IOException  {
		if (result.getStatus() == ITestResult.FAILURE) {	
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("data\\skrinshot.jpg");
		Files.copy(srcFile.toPath(), destFile.toPath());
		}
		this.driver.quit();
	}
}
