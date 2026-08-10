package Utility;

import org.openqa.selenium.WebDriver;

public class WindowManager {
    private WebDriver driver ;
    public WindowManager(WebDriver driver){
        this.driver = driver;
    }

    public void switchToNewTab() {
        String originalWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void goToUrl(String url){
        driver.navigate().to(url);
    }
    public void goBack(){
        driver.navigate().back();
    }
}
