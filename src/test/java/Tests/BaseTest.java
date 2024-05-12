package Tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import Utils.BrowserUtils;
import Utils.ConfigUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseTest {
    private static final String BASE_DIR = "target/test-output"; // Base directory for all outputs
    private static final String SCREENSHOT_SUBDIR = "screenshots";

    private FileHandler fileHandler;
    protected String url;
    protected Logger logger;
    protected String browser;
    protected WebDriver driver;
    protected WebDriverWait wait;
    private final List<String> screenshotPaths = new ArrayList<>();

    public BaseTest() {
        this.logger = Logger.getLogger(BaseTest.class.getName());

        System.setProperty(
            "java.util.logging.SimpleFormatter.format",
            "%1$tF %1$tT %4$s %2$s - %5$s%6$s%n"
        );

        try {
            this.fileHandler = new FileHandler("logs.log");
            this.logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            this.fileHandler.setFormatter(formatter);
            this.logger.setUseParentHandlers(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String takeScreenshot(String fileName) {
        String directoryPath = BASE_DIR + "/" + SCREENSHOT_SUBDIR;
        Path destinationPath = Paths.get(directoryPath, fileName + "_" + System.currentTimeMillis() + ".png");
        try {
            Files.createDirectories(destinationPath.getParent()); // Ensure directory exists
            Files.copy(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath(), destinationPath);
            String relativePath = SCREENSHOT_SUBDIR + "/" + destinationPath.getFileName().toString();
            screenshotPaths.add(relativePath); // Store relative path for later use
            return relativePath;
        } catch (Exception e) {
            logger.severe("Failed to save screenshot: " + e.getMessage());
        }
        return null;
    }

    public void getBrowser(String browserName){
        this.driver = BrowserUtils.setUpBrowser(browserName);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public List<String> getScreenshotPaths() {
        return screenshotPaths;
    }

    public void addScreenshotPath(String path) {
        screenshotPaths.add(path);
    }

    public void clearScreenshotPaths() {
        screenshotPaths.clear();
    }

    //    @Parameters("browser")
//    @BeforeMethod
//    public void setUp(String browser) {
//        this.browser = browser;
//        this.url = ConfigUtils.getProperty("url", "https://www.demoblaze.com");
//        this.driver = BrowserUtils.setUpBrowser(this.browser);
//        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(15000));
//    }

    @BeforeMethod
    public void setUp() {
        this.browser = ConfigUtils.getProperty("browser", "chrome");
        this.url = ConfigUtils.getProperty("url", "https://www.demoblaze.com");
        this.driver = BrowserUtils.setUpBrowser(this.browser);
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(15000));
    }

    @AfterMethod
    public void cleanUp() {
        clearScreenshotPaths();
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
        if(fileHandler != null) {
            fileHandler.close();
        }
    }
}
