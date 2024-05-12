package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserUtils {

    public static WebDriver setUpBrowser(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", getPath("chromedriver"));
                driver = new ChromeDriver(new ChromeOptions());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", getPath("geckodriver"));
                driver = new FirefoxDriver(new FirefoxOptions());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", getPath("msedgedriver"));
                driver = new EdgeDriver(new EdgeOptions());
                break;
            default:
                System.out.println("Unsupported browser. Defaulting to Chrome.");
                System.setProperty("webdriver.chrome.driver", getPath("chromedriver"));
                driver = new ChromeDriver(new ChromeOptions());
        }
        return driver;
    }

    private static String getPath(String driverName) {
        String os = System.getProperty("os.name").toLowerCase();
        String driverPath = "src/test/resources/drivers/";

        if (os.contains("win")) {
            return driverPath + driverName + ".exe";
        } else {
            return driverPath + driverName;
        }
    }
}
