package tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {
	
//	@Test(priority=0)
//	public void editProfileTest() {
//		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
//		this.loginPopUpPage.closeLocationDialog();
//		this.loginPage.getLoginPage().click();
//		this.loginPage.logIn(this.user, this.password);
//		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Login Successfull");
	
//		this.driver.navigate().to(this.baseUrl + "/member/profile");
//		this.profilePage.changeAllProfileInputs("Kiki", "Riki", "Tamo 21", "11111", "053532532", "United States", "California", "Los Angeles");
//		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Setup Successful");
//		this.authPage.getLogout().click();
//		this.sa.assertEquals(this.notificationSystemPage.getMessageText(), "Logout Successfull!");
//	}
	
	@Test(priority=1)
	public void changeProfileImageTest() throws IOException {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		this.loginPopUpPage.closeLocationDialog();
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
}
