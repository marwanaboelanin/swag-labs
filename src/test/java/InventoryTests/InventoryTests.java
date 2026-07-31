package InventoryTests;

import BaseTests.BaseTest;
import Page.InventoryPage;
import Page.LoginPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class InventoryTests extends BaseTest {
    @Test
    public void testInventoryPageElements(){
        int Expected_Product_Count = 6;
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"));
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertEquals(inventoryPage.getPageTitle(), "Swag Labs");
        assertTrue(inventoryPage.isCartIconDisplayed());
        assertEquals(inventoryPage.getProductCount(),Expected_Product_Count);
    }
}
