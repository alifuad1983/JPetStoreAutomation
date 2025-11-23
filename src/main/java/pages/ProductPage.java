package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
   // private Logger log = LoggerFactory.getLogger(ProductPage.class);

    // Category, product, item, add to cart
    private By fishCategory = By.xpath("//div[@id='Content']//a[contains(@href,'FISH')]");
    private By firstProduct = By.xpath("//table[@id='Catalog']//tr[2]/td[1]/a");
    private By firstItem = By.xpath("//table[@id='Catalog']//tr[2]/td[1]/a");
    private By addToCartButton = By.xpath("//a[text()='Add to Cart']");
    private By cartTitle = By.xpath("//h2[text()='Shopping Cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
       // log.info("Opening JPetStore homepage...");
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");
    }

    public void goToFishCategory() {
      //  log.info("Navigating to Fish category...");
        driver.findElement(fishCategory).click();
    }

    public void selectFirstProduct() {
      //  log.info("Selecting first product from the category...");
        driver.findElement(firstProduct).click();
    }

    public void selectFirstItem() {
     //   log.info("Selecting first item inside the product...");
        driver.findElement(firstItem).click();
    }

    public void addToCart() {
     //   log.info("Adding item to the cart...");
        driver.findElement(addToCartButton).click();
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartTitle).isDisplayed();
    }
}
