package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getEditProfileBtn() {
		return this.driver.findElement(By.className("btn btn--primary btn--bordered"));
	}
	
	public WebElement getSettingFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getSettingLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getSettingAddress() {
		return this.driver.findElement(By.name("user_address"));
	}
	
	public WebElement getSettingPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getSettingZip() {
		return this.driver.findElement(By.name("user_zip"));
	}
	
	public void selectCountry(String country) {
		new Select(this.driver.findElement(By.name("user_country_id"))).selectByVisibleText(country);
	}
	
	public void selectState(String state) {
		new Select(this.driver.findElement(By.name("user_state_id"))).selectByVisibleText(state);
	}

	public void selectCity(String city) {
		new Select(this.driver.findElement(By.name("user_city"))).selectByVisibleText(city);
	}
	
	public WebElement getSubmitBtn() {
		return this.driver.findElement(By.name("btn_submit"));
	}
	
	public void uploadPicture(String picturePath) {
		js.executeScript("arguments[0].click();", this.driver.findElement(By.className("upload uploadFile-Js")));
		this.driver.findElement(By.className("upload uploadFile-Js")).sendKeys(picturePath);
	}
	
	public void removePicture() {
		js.executeScript("arguments[0].click();", this.driver.findElement(By.className("remove")));
	}
	
	public void changeAllProfileInputs(String name, String lastname, String address, String zip, String phone, String country, String state, String city) {
		this.getSettingFirstName().sendKeys(name);
		this.getSettingLastName().sendKeys(lastname);
		this.getSettingAddress().sendKeys(address);
		this.getSettingZip().sendKeys(zip);
		this.getSettingPhone().sendKeys(phone);
		this.selectCountry(country);
		this.selectState(state);
		this.selectCity(city);
		this.getSubmitBtn();
	}
	
	
	

	
	



}
