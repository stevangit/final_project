package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getMealSearchBar() {
		return this.driver.findElement(By.xpath("//input[@placeholder='Search Your Favourite Meals']"));
	}
	
	public void selectSorting(String sortOption) {
		new Select(this.driver.findElement(By.name("sort-toggle"))).selectByVisibleText(sortOption);
	}
	
	public WebElement getPaginationPage(String page) {
		return this.driver.findElement(By.xpath("//ul[@class='pagination']/li[" + page + "]"));
	}
	
	public WebElement getPaginationNext() {
		return this.driver.findElement(By.className("next"));
	}
	
	public WebElement getAddToCart() {
		return this.driver.findElement(By.linkText("Add To Cart"));
	}								
	
	public void addMeal(Integer amount) {
		this.driver.findElement(By.name("product_qty")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		this.driver.findElement(By.name("product_qty")).sendKeys(Keys.chord(Keys.DELETE));
		this.driver.findElement(By.name("product_qty")).sendKeys(amount.toString());
		this.getAddToCart().click();
	}
	
	public WebElement addToFavorite() {
		return this.driver.findElement(By.xpath("//a[@title='Favorite']"));
	}
}
