package PageObjects;

import Constants.AboutUsVideoConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AboutUsVideoPage {

    private final WebDriver driver;

    private final By videoLink = By.xpath(AboutUsVideoConstants.VIDEO_LINK);

    private final By videoInput = By.xpath(AboutUsVideoConstants.VIDEO_INPUT);

    public void clickVideo(){
        this.driver.findElement(videoInput).click();
    }

    public void navigateToVideoForm() {
        this.driver.findElement(videoLink).click();
    }



    public AboutUsVideoPage(WebDriver driver) {
        this.driver = driver;
    }
}
