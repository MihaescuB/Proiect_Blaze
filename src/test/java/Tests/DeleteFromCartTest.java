package Tests;

import Constants.OrderConstants;
import PageObjects.PlaceOrderPage;
import Listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class DeleteFromCartTest extends BaseTest {


    @Test
    public void deleteFromCart(){
        this.logger.info("Starting Delete from Cart Test");
        driver.get(url);
        driver.manage().window().maximize();

        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver,wait,logger);
        placeOrderPage.navigateToHome();

        this.logger.info("Navigated to Home");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.SAMSUNG_GALAXY_INPUT)));
        this.takeScreenshot("Samsung Galaxy visible");
        placeOrderPage.selectSamsunGalaxy();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        this.logger.info("Samsung Galaxy S6 added to cart");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.NOKIA_LUMIA_INPUT)));
        this.takeScreenshot("Nokia Lumia visible");
        placeOrderPage.selectNokiaLumia();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        this.logger.info("Nokia Lumia Added to cart");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        placeOrderPage.selectCart();
        placeOrderPage.deleteItems();
        this.logger.info("Items deleted successfully");
        this.takeScreenshot("Deleted successfully!");
    }
}
