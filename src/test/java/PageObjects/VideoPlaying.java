package PageObjects;

import Tests.BaseTest;
import org.openqa.selenium.WebElement;

public class VideoPlaying extends BaseTest {

    public static boolean isVideoPlaying(WebElement videoElement) {
        return !Boolean.parseBoolean(videoElement.getAttribute("paused"));
    }
}
