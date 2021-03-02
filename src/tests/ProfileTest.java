package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority=0)
	public void editProfile() {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		this.locationPopUpPage.closeLocationDialog();
		this.loginPage.getLoginPage().click();
		this.loginPage.logIn(this.user, this.password);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Login Successfull");

		this.driver.navigate().to(this.baseUrl + "/member/profile");
		this.profilePage.changeAllProfileInputs("Kiki", "Riki", "Tamo 21", "11111", "053532532", "United States", "California", "Los Angeles");
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Setup Successful");
		this.authPage.getLogout().click();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Logout Successfull!");
	}

	@Test(priority=1)
	public void changeProfileImage() throws IOException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		this.locationPopUpPage.closeLocationDialog();
		this.loginPage.getLoginPage().click();
		this.loginPage.logIn(this.user, this.password);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Login Successfull");
		
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		String imgPath = new File("img/sa.png").getCanonicalPath();
		this.profilePage.uploadPicture(imgPath);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Profile Image Uploaded Successfully");
		this.notificationSystemPage.messageGoneWaiter();
		
		this.profilePage.removePicture();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Profile Image Deleted Successfully");
		this.notificationSystemPage.messageGoneWaiter();
		this.authPage.getLogout().click();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Logout Successfull!");
	}

	@Test(priority=2)
	public void addMealToCart() throws InterruptedException {
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopUpPage.closeLocationDialog();
		this.mealPage.addMeal(2);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "The Following Errors Occurred: "
				+ "Please Select Location");
		this.notificationSystemPage.messageGoneWaiter();
		this.locationPopUpPage.getHeaderLocation().click();
		this.locationPopUpPage.enterLocation("City Center - Albany");
		Thread.sleep(1000);
		this.mealPage.addMeal(2);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Meal Added To Cart");
		this.notificationSystemPage.messageGoneWaiter();
	}

	@Test(priority=3)
	public void addMealToFavorite() {
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.locationPopUpPage.closeLocationDialog();
		this.mealPage.addToFavorite().click();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Please login first!");
		
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		this.loginPage.getLoginPage().click();
		this.loginPage.logIn(this.user, this.password);
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Login Successfull");
		
		this.driver.navigate().to(this.baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
		this.mealPage.addToFavorite().click();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Product has been added to your favorites.");
	}

	@Test(priority = 4)
	public void clearCart() throws IOException, InterruptedException {
		this.driver.navigate().to(this.baseUrl + "meals");
		this.locationPopUpPage.enterLocation("City Center - Albany");
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			String location = row.getCell(0).getStringCellValue();
			int amount = (int) row.getCell(1).getNumericCellValue();
			this.driver.navigate().to(location);
			Thread.sleep(1000);
			this.mealPage.addMeal(amount);
		}
		wb.close();
		this.cartSummaryPage.clearShoppingCart();
		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "All meals removed from Cart successfully");
		

//		
//			XSSFRow row = sheet.getRow(i);
//			String location = row.getCell(0).getStringCellValue();
//			int amount = (int) row.getCell(2).getNumericCellValue();
//			this.locationPopUpPage.enterLocation(location);
//			Thread.sleep(1000);
//			
//			for (int j = 3; j < row.getLastCellNum(); j++) {
//				this.mealPage.getMealSearchBar().clear();
//				String mealName = row.getCell(j).getStringCellValue();
//				System.out.println(mealName);
//				this.js.executeScript("arguments[0].sendKeys(arguments[1]);" , this.mealPage.getMealSearchBar(), mealName);
//				this.mealPage.getMealSearchBar().sendKeys(Keys.chord(Keys.ENTER));
//				
//				this.driver.findElement(By.xpath("//div[@class='product-head']")).click();
//				Thread.sleep(1000);
//				this.mealPage.addMeal(amount);
//				this.notificationSystemPage.messageGoneWaiter();
//				this.driver.navigate().back();
//			}

	}
}
