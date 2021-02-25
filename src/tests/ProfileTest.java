package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {
	
	@Test
	public void editProfileTest() {
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		this.loginPopUpPage.closeLocationDialog();
		this.loginPage.getLoginPage().click();
		this.loginPage.logIn(this.user, this.password);
		Assert.assertEquals(this.notificationSystemPage.getMessageText(), "Login Successfull");
		this.driver.navigate().to(this.baseUrl + "/member/profile");
		this.profilePage.changeAllProfileInputs("Kiki", "Riki", "Tamo 21", "11111", "053532532", "United States", "California", "Los Angeles");
		Assert.assertEquals(this.notificationSystemPage.getMessageText(), "Setup Successful");
		this.authPage.getLogout().click();
		Assert.assertEquals(this.notificationSystemPage.getMessageText(), "Logout Successfull!");

		
	}
}
