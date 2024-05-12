package Listeners;

import Tests.BaseTest;
import Utils.EmailUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {
    private static final String REPORT_FILE = "target/test-output/extent-reports.html";
    private final ExtentReports extent = new ExtentReports();
    ExtentSparkReporter htmlReporter = new ExtentSparkReporter(REPORT_FILE);
    private final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public TestListener() {
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onStart(ITestContext context) {
        ensureDirectoriesExist();
        htmlReporter = new ExtentSparkReporter("target/test-output/extent-reports.html");
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName(context.getName());
    }

    private void ensureDirectoriesExist() {
        Path reportPath = Paths.get("target", "test-output");
        Path screenshotPath = reportPath.resolve("screenshots");
        try {
            Files.createDirectories(reportPath);  // Ensure the report directory exists
            Files.createDirectories(screenshotPath);  // Ensure the screenshot directory exists
        } catch (IOException e) {
            System.out.println("Failed to create directories for test output");
            throw new RuntimeException("Failed to create directories for test output", e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseTest testInstance = (BaseTest) result.getInstance();
        List<String> screenshots = testInstance.getScreenshotPaths();

        if (!screenshots.isEmpty()) {
            for (String screenshotPath : screenshots) {
                test.get().pass("Screenshot captured", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            }
        } else {
            test.get().pass("Test passed without visual evidence");
        }

        sendEmailWithReport(result, "Test Success Notification: ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseTest testInstance = (BaseTest) result.getInstance();

        String failureScreenshotPath = testInstance.takeScreenshot(result.getMethod().getMethodName() + "_Failure");

        if (failureScreenshotPath != null) {
            test.get().fail("Screenshot at the moment of failure",
                    MediaEntityBuilder.createScreenCaptureFromPath(failureScreenshotPath).build());
        }

        List<String> screenshots = testInstance.getScreenshotPaths();
        for (String screenshotPath : screenshots) {
            test.get().info("Additional screenshot captured during test",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        test.get().fail(result.getThrowable());

        sendEmailWithReport(result, "Test Failure Notification: ");
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private void sendEmailWithReport(ITestResult result, String subject) {
        BaseTest testInstance = (BaseTest) result.getInstance();
        List<String> attachments = new ArrayList<>();
        attachments.add("target/test-output/extent-reports.html"); // Add report
        for (String screenshotPath : testInstance.getScreenshotPaths()) {
            attachments.add("target/test-output/" + screenshotPath); // Add screenshots
        }
        String emailBody = "Please see the attached report and screenshots for detailed test results.";
        EmailUtils.sendEmailWithAttachments("mihaescubogdan86@yahoo.com", subject, emailBody, attachments);
    }
}