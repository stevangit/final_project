package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);
	}
	
	public WebElement getMealSearchBar() {
		return this.driver.findElement(By.name("keywords"));
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
	
	public WebElement getMealWithNumber(String numberMeal) {
		//numberMeal from 1-24
		return this.driver.findElement(By.xpath("//*[@id=\"listing\"]/div[" + numberMeal + "]/div"));
	}								
	
	public void addMeal(int amount, String numberMeal) {
		this.getMealWithNumber(numberMeal).click();
		for (int i = 0; i < amount; i++) {
			this.driver.findElement(By.linkText("Add To Cart"));
		}
	}
	
	public WebElement addToFavorite() {
		return this.driver.findElement(By.className("favourite itemfav link"));
	}
	
		
	
	
}
