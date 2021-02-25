package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getAccountDropDown() {
		return this.driver.findElement(By.className("accounts-link accounts-popup"));
	}
	
	public WebElement getLogout() {
		return this.driver.findElement(By.linkText("Logout"));
	}
	
	public WebElement getProfileDetails() {
		return this.driver.findElement(By.xpath("//a[@href='/member/profile']"));
	}
	
	
}
