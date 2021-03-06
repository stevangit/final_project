package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getLoginPage() {
		return this.driver.findElement(By.xpath("//*[@href='/guest-user/login-form']"));
	}
	
	public WebElement getEmailField() {
		return this.driver.findElement(By.name("username"));
	}
	
	public WebElement getPasswordField() {
		return this.driver.findElement(By.name("password"));
	}
	
	public WebElement getLoginBtn() {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void logIn(String email, String pass) {
		this.getEmailField().clear();
		this.getEmailField().sendKeys(email);
		this.getPasswordField().clear();
		this.getPasswordField().sendKeys(pass);
		this.getLoginBtn().click();
	}
	
}
