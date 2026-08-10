package CartTests;
import BaseTests.BaseTest;
import Page.CartPage;
import Page.InventoryPage;
import Page.LoginPage;
import Utility.WindowManager;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTests extends BaseTest {
    @Test
    public void testVerifyLinkedInLink() {
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        cartPage.clickLinkedInCart();
        getWindowManager().switchToNewTab();
        assertTrue(getWindowManager().getCurrentUrl().contains("linkedin"));
    }

    @Test
    public void testVerifyFacebookLink() {
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        cartPage.clickFacebookCart();
        getWindowManager().switchToNewTab();
        assertTrue(getWindowManager().getCurrentUrl().contains("facebook"));
    }

    @Test
    public void testVerifyTwitterLink() {
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        cartPage.clickTwitterCart();
        getWindowManager().switchToNewTab();
        assertTrue(getWindowManager().getCurrentUrl().contains("x.com"));
    }

    @Test
    public void VerifyCartIsEmpty() {
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        assertTrue(cartPage.isCartEmpty());
    }
    @Test
    public void getThreeProducts(){
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickBack();
        inventoryPage.clickBolt();
        inventoryPage.clickOnesie();
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        assertEquals(cartPage.getCartItemNames().size(), 3);
    }
    @Test
    public void  RemoveOneProduct (){
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickBack();
        inventoryPage.clickBolt();
        inventoryPage.clickOnesie();
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        cartPage.removeBolt();
        driver.navigate().back();
        assertEquals(inventoryPage.getButtonTextForProduct("Sauce Labs Bolt T-Shirt"), "Add to cart");
        assertEquals(inventoryPage.getButtonTextForProduct("Sauce Labs Backpack"), "Remove");
        assertEquals(inventoryPage.getButtonTextForProduct("Sauce Labs Onesie"), "Remove");
    }
    @Test public void VerifyCartTotalPrice (){
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        double backpackPrice = Double.parseDouble(inventoryPage.getPriceForProduct("Sauce Labs Backpack").replace("$", ""));
        double boltPrice = Double.parseDouble(inventoryPage.getPriceForProduct("Sauce Labs Bolt T-Shirt").replace("$", ""));
        double onesiePrice = Double.parseDouble(inventoryPage.getPriceForProduct("Sauce Labs Onesie").replace("$", ""));
        double expectedSum = backpackPrice + boltPrice + onesiePrice;
        inventoryPage.clickBack();
        inventoryPage.clickBolt();
        inventoryPage.clickOnesie();
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        cartPage.clickCheckOut();
        cartPage.fillCheckoutInfo("marwan","aboelanin","7474");
        assertEquals(cartPage.getItemTotal(), expectedSum,0.01);
    }

    @Test
    public void CheckoutWithanEmptyCart(){
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        assertTrue(cartPage.isCartEmpty());
        cartPage.clickCheckOut();
        cartPage.fillCheckoutInfo("marwan","aboelanin","7474");
    }
    @Test
    public void CartStateAfterLogoutAndLogin(){
        var loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        var inventoryPage = new InventoryPage(driver);
        inventoryPage.clickBack();
        inventoryPage.clickBolt();
        inventoryPage.clickmenuAndLogout();
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryPage.clickCartIcon();
        var cartPage = new CartPage(driver);
        assertEquals(cartPage.getCartItemNames().size(), 2);
    }
}
