package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private By cartIcon = By.className("shopping_cart_link");
    private By products = By.className("inventory_item");
    private By backIcon = By.id("add-to-cart-sauce-labs-backpack");
    private By onesieIcon = By.id("add-to-cart-sauce-labs-onesie");
    private By boltIcon = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By openMenu = By.tagName("button");
    private By logOut = By.linkText("Logout");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isCartIconDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public int getProductCount() {
        List<WebElement> items = driver.findElements(products);
        return items.size();
    }
    public CartPage clickCartIcon(){
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }
    public void clickBack(){
        driver.findElement(backIcon).click();
    }
    public void clickBolt(){
        driver.findElement(boltIcon).click();
    }
    public void clickOnesie(){
        driver.findElement(onesieIcon).click();
    }
    public String getButtonTextForProduct(String productName) {
        String idSuffix = productName.toLowerCase().replace(" ", "-");
        By addButton = By.id("add-to-cart-" + idSuffix);
        By removeButton = By.id("remove-" + idSuffix);
        if (!driver.findElements(addButton).isEmpty()) {
            return driver.findElement(addButton).getText();
        } else {
            return driver.findElement(removeButton).getText();
        }
    }
    public void clickmenuAndLogout(){
        driver.findElement(openMenu).click();
        driver.findElement(logOut).click();
    }
    public String getPriceForProduct(String productName) {
        List<WebElement> names = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().equals(productName)) {
                return prices.get(i).getText();
            }
        }
        return null;
    }
}