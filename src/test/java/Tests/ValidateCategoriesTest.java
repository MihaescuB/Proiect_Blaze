package Tests;

import Constants.OrderConstants;
import Constants.SignInConstants;
import PageObjects.PlaceOrderPage;
import PageObjects.SignInPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import Listeners.TestListener;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ValidateCategoriesTest extends BaseTest{

    //Validarea Categoriilor din Pagina principala Home

    @Test
    public void validateCategoriesTest(){

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
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver,wait,logger);
        placeOrderPage.navigateToHome();
        this.takeScreenshot("Navigate to Home sucessfully");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.SAMSUNG_GALAXY_INPUT)));
        String textt = driver.findElement(By.xpath(OrderConstants.SAMSUNG_GALAXY_INPUT)).getText();
        Assert.assertTrue("Samsung galaxy s6 phone not visible", textt.contains("Samsung galaxy s6"));
        String text1 = driver.findElement(By.xpath( OrderConstants.NEXUS6_INPUT)).getText();
        Assert.assertTrue("Nexus 6 phone not visible", text1.contains("Nexus 6"));
        String text2 = driver.findElement(By.xpath(OrderConstants.NOKIA_LUMIA_INPUT)).getText();
        Assert.assertTrue("Nokia lumia 1520 phone not visible", text2.contains("Nokia lumia 1520"));
        placeOrderPage.selectLaptops();
        this.takeScreenshot("Select Laptops sucessfully");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.MACBOOK_INPUT)));
        String text3 = driver.findElement(By.xpath(OrderConstants.SONYVAIO_INPUT)).getText();
        Assert.assertTrue("Sony vaio i5 laptop not visible", text3.contains("Sony vaio i5"));
        String text4 = driver.findElement(By.xpath(OrderConstants.MACBOOK_INPUT)).getText();
        Assert.assertTrue("MacBook air laptop not visible", text4.contains("MacBook air"));
        placeOrderPage.selectMonitors();
        this.takeScreenshot("Select Monitors sucessfully");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.APPLE_MONITOR_INPUT)));
        String text5 = driver.findElement(By.xpath(OrderConstants.APPLE_MONITOR_INPUT)).getText();
        Assert.assertTrue("Apple monitor 24 monitor not visible", text5.contains("Apple monitor 24"));
        String text6 = driver.findElement(By.xpath(OrderConstants.ASUS_INPUT)).getText();
        Assert.assertTrue("ASUS Full HD monitor not visible", text6.contains("ASUS Full HD"));
        placeOrderPage.selectMonitors();

}
}