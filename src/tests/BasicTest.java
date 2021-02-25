package tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import pages.AuthPage;
import pages.CartSummaryPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;


public abstract class BasicTest {
	protected WebDriver driver;
	protected WebDriverWait waiter;
	protected JavascriptExecutor js;
	protected LoginPage loginPage;
	protected LocationPopUpPage loginPopUpPage;
	protected AuthPage authPage;
	protected CartSummaryPage cartSummaryPage;
	protected MealPage mealPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected String baseUrl = "http://demo.yo-meals.com/";
	protected String user = "customer@dummyid.com";
	protected String password = "12345678a";
	
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
		this.authPage = new AuthPage(driver, waiter, js);
		this.cartSummaryPage = new CartSummaryPage(driver, waiter, js);
		this.mealPage = new MealPage(driver, waiter, js);
		this.notificationSystemPage = new NotificationSystemPage(driver, waiter, js);
		this.profilePage = new ProfilePage(driver, waiter, js);
	}
	
	@AfterMethod
	public void after(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
			Date date = new Date();
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("screenshot\\" + dateFormat.format(date) + ".img");
			Files.copy(srcFile.toPath(), destFile.toPath());
			}
		this.driver.manage().deleteAllCookies();
	}
	
	@AfterClass
		public void clean() {
		this.driver.close();
	}
}
