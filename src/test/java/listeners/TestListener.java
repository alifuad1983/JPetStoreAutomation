package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = utilities.ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test failed: " + result.getThrowable());

        Object currentClass = result.getInstance();
        // WebDriver driver = ((Utilities.BrowserSetup)((BaseTest) currentClass)).driver;
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://petstore.octoperf.com/actions/Account.action?signonForm=");


        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getName());
            test.get().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String takeScreenshot(WebDriver driver, String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "test-output/screenshots/" + testName + ".png";
        File destFile = new File(path);
        destFile.getParentFile().mkdirs();

        try {
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
