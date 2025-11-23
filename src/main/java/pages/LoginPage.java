package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By username = By.name("username");
    private By password = By.name("password");
    private By signon = By.name("signon");
    private By error = By.xpath("//*[contains(text(),'Invalid username') or contains(text(),'Signon failed')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public void enterUsername(String u) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).clear();
        driver.findElement(username).sendKeys(u);
    }

    public void enterPassword(String p) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(p);
    }

    public void clickLogin() {
        driver.findElement(signon).click();
    }

    public boolean isErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(error)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}