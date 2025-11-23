package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Faker faker = new Faker();

    // Locators (example for JPetStore)
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By repeatPasswordField = By.name("repeatedPassword");
    private By firstNameField = By.name("account.firstName");
    private By lastNameField = By.name("account.lastName");
    private By emailField = By.name("account.email");
    private By phoneField = By.name("account.phone");
    private By addressField = By.name("account.address1");
    private By addressField2 = By.name("account.address2");
    private By cityField = By.name("account.city");
    private By stateField = By.name("account.state");
    private By zipField = By.name("account.zip");
    private By countryField = By.name("account.country");
    private By saveButton = By.xpath("//input[@value='Save Account Information']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String generateUsername() {
        return "user_" + faker.number().digits(8);
    }

    public String generatePassword() {
        return faker.internet().password(8, 12);
    }

    public UserData generateUserData() {
        UserData u = new UserData();
        u.username = generateUsername();
        u.password = generatePassword();
        u.firstName = faker.name().firstName();
        u.lastName = faker.name().lastName();
        u.email = faker.internet().emailAddress();
        u.phone = faker.phoneNumber().cellPhone();
        u.address1 = faker.address().streetAddress();
        u.address2 = faker.address().streetAddress();
        u.city = faker.address().city();
        u.state = faker.address().state();
        u.zip = faker.address().zipCode();
        u.country = faker.address().country();
        return u;
    }

    public void fillForm(UserData user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(user.username);
        driver.findElement(passwordField).sendKeys(user.password);
        driver.findElement(repeatPasswordField).sendKeys(user.password);

        driver.findElement(firstNameField).sendKeys(user.firstName);
        driver.findElement(lastNameField).sendKeys(user.lastName);
        driver.findElement(emailField).sendKeys(user.email);
        driver.findElement(phoneField).sendKeys(user.phone);
        driver.findElement(addressField).sendKeys(user.address1);
        driver.findElement(addressField2).sendKeys(user.address2);
        driver.findElement(cityField).sendKeys(user.city);
        driver.findElement(stateField).sendKeys(user.state);
        driver.findElement(zipField).sendKeys(user.zip);
        driver.findElement(countryField).sendKeys(user.country);
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }
}
