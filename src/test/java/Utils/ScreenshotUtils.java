package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static String takeScreenshot(WebDriver driver, String testName) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get("screenshots/"));
            Files.copy(file.toPath(), Paths.get(filePath));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
