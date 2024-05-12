package Tests;

import Constants.AboutUsVideoConstants;
import PageObjects.AboutUsVideoPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static PageObjects.VideoPlaying.isVideoPlaying;

@Listeners(TestListener.class)
public class AboutUsVideoTest extends BaseTest{
    @Test
    public void aboutUsVideoTest() throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();

        AboutUsVideoPage aboutUsVideoPage = new AboutUsVideoPage(driver);
        aboutUsVideoPage.navigateToVideoForm();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AboutUsVideoConstants.VIDEO_INPUT)));
        WebElement element = driver.findElement(By.xpath(AboutUsVideoConstants.VIDEO_INPUT));
        Assert.assertTrue("Video is not present in the page", element!=null);
        aboutUsVideoPage.clickVideo();
        Thread.sleep(5000);
        WebElement videoElement = driver.findElement(By.xpath(AboutUsVideoConstants.VIDEO_INPUT));
        if (videoElement != null) {
            // Check if the video is playing
            boolean isPlaying = isVideoPlaying(videoElement);
            if (isPlaying) {
                System.out.println("Video is playing.");
            } else {
                System.out.println("Video is not playing.");
            }
        } else {
            System.out.println("Video element not found.");
        }
        this.takeScreenshot("Video_Playing");
        driver.quit();
    }
}
