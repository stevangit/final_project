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
		return this.driver.findElement(By.xpath("//div[@class='accounts-link accounts-popup']"));
	}
	
	public WebElement getLogout() {
		if (getAccountDropDown().isSelected()) {
		} else {
			getAccountDropDown().click();
		}
		return this.driver.findElement(By.linkText("Logout"));
	}
	
	
	
}
