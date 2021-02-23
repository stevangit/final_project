package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopUpPage extends BasicPage {

	public LocationPopUpPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}

	public WebElement getHeaderLocation() {
		return this.driver.findElement(By.className("location-selector"));
	}

	public WebElement getLocationCloseBtn() {
		return this.driver.findElement(By.className("close-btn close-btn-white"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	public void openLocationDialog() {
		getHeaderLocation().click();
	}

	public void enterLocation(String locationName) {
		getKeyword().click();
		String atribut = getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", getLocationInput(), atribut);
		js.executeScript("arguments[0].click();", getSubmit());
	}

	public void closeLocationDialog() {
		getLocationCloseBtn().click();
	}
	
	
}
