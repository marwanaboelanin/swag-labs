package LoginTests;

import BaseTests.BaseTest;
import Page.LoginPage;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
    }
   @Test
    public void testInvalidLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user1");
        loginPage.setPassword("standard_user2");
        loginPage.clickLogin();
        assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
   }
   @Test
    public void testLoginWithoutPassword(){
       LoginPage loginPage = new LoginPage(driver);
       loginPage.setUsername("standard_user");
       loginPage.setPassword("");
       loginPage.clickLogin();
       assertEquals(loginPage.getErrorMessage(),"Epic sadface: Password is required");
   }
}