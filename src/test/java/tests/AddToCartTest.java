package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductPage;
import utilities.DriverFactory;

@Listeners(listeners.TestListener.class)
public class AddToCartTest {

    WebDriver driver;
    ProductPage pp = new ProductPage(driver);

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://petstore.octoperf.com/actions/Catalog.action");
    }
    @Test
    public void addItemToCart() {


        pp.open();
        pp.goToFishCategory();
        pp.selectFirstProduct();
        pp.selectFirstItem();
        pp.addToCart();

        Assert.assertTrue(pp.isCartDisplayed(),
                "Item was NOT added to the cart!");

    }
}
