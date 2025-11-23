package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance("test-output/ExtentReport.html");
        }
        return extent;
    }

    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("JPetStore Test Report");
        reporter.config().setReportName("JPetStore Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Ali Fuad");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");

        return extent;
    }
}
