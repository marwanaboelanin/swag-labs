package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By linkedinIcon = By.partialLinkText("Linked");
    private By facebookIcon = By.partialLinkText("Facebo");
    private By twitterIcon = By.partialLinkText("Twitt");
    private By continueShopping = By.id("continue-shopping");
    private By cartItem = By.className("cart_item");
    private By checkOut = By.id("checkout");
    private By yourCart = By.tagName("span");
    private By cartItemNames = By.className("inventory_item_name");
    private By removeBolt = By.id("remove-sauce-labs-bolt-t-shirt");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueBotton = By.id("continue");
    private By itemTotalLabel = By.className("summary_subtotal_label");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickLinkedInCart(){
        driver.findElement(linkedinIcon).click();
    }
    public void clickFacebookCart(){
        driver.findElement(facebookIcon).click();
    }
    public void clickTwitterCart(){
        driver.findElement(twitterIcon).click();
    }
    public boolean isCartEmpty() {
        return driver.findElements(cartItem).isEmpty();
    }
    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(cartItemNames);
        List<String> names = new ArrayList<>();
        for (WebElement item : items) {
            names.add(item.getText());
        }
        return names;
    }
    public void removeBolt(){
        driver.findElement(removeBolt).click();
    }
    public void clickCheckOut(){
        driver.findElement(checkOut).click();
    }
    public void fillCheckoutInfo(String firstName, String lastName, String zip) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(zip);
        driver.findElement(continueBotton).click();
    }
    public double getItemTotal() {
        String text = driver.findElement(itemTotalLabel).getText(); // "Item total: $88.97"
        String amount = text.split("\\$")[1].trim();
        return Double.parseDouble(amount);
    }
}
