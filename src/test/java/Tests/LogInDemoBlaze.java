package Tests;

import Constants.SignInConstants;
import PageObjects.SignInPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import Listeners.TestListener;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LogInDemoBlaze extends BaseTest{

    
    //sign in
    @Test
    public void logInDemoBlaze(){
        this.logger.info("Starting Login Test");
        driver.get(url);
        driver.manage().window().maximize();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.navigateToSignIn();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.USERNAME_FIELD)));
        signInPage.signIn("Legion","Password");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.LOGIN_LEGION_USER)));
        String text = driver.findElement(By.xpath(SignInConstants.LOGIN_LEGION_USER)).getText();
        Assert.assertTrue("Log in failed", text.contains("Welcome Legion"));
        this.takeScreenshot("Login sucessfully");

    }

}
