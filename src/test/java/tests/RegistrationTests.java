package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import pages.UserData;
import utilities.CSVWriterUtil;
import utilities.DriverFactory;
import org.openqa.selenium.WebDriver;
import utilities.ExcelWriterUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Listeners(listeners.TestListener.class)
public class RegistrationTests {

    WebDriver driver;
    RegistrationPage reg;

    // data paths
    private static final String DATA_DIR = System.getProperty("user.dir") + "/test-data";
    private static final String CSV_PATH = DATA_DIR + "/generatedUsers.csv";
    private static final String XLSX_PATH = DATA_DIR + "/generatedUsers.xlsx";


    @BeforeMethod
    public void setup() throws Exception {
        // ensure data directory exists
        Path p = Paths.get("test-data/generatedUsers.csv");
        if (!Files.exists(p)) Files.createDirectories(p);

        driver = DriverFactory.getDriver();
        driver.get("https://petstore.octoperf.com/actions/Account.action?newAccountForm=");
        reg = new RegistrationPage(driver);
    }

    @Test
    public void testDynamicRegistrationAndSave() throws InterruptedException {
        // 1. generate user
        UserData user = reg.generateUserData();

        // 2. fill and submit
        reg.fillForm(user);
        reg.clickSave();

        // 3. basic assertion that registration succeeded (adjust the success text to your app)
       /* Assert.assertTrue(driver.getPageSource().contains("Your account has been created") ||
                        driver.getPageSource().contains("Thank you, your account has been created"),
                "Registration success message not found!");*/
        //Assert.assertTrue(driver.getCurrentUrl().endsWith("https://petstore.octoperf.com//actions/Catalog.action"));
       // Assert.assertTrue(driver.findElement(By.xpath("//div[@id='Content']//a[contains(@href,'FISH')]")));

        // 4. write to CSV and Excel (append)
        CSVWriterUtil.appendUserToCSV(CSV_PATH, user);
        ExcelWriterUtil.appendUserToExcel(XLSX_PATH, user);

    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
