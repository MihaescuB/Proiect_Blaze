package Tests;

import Constants.OrderConstants;
import Constants.SignInConstants;
import PageObjects.PlaceOrderPage;
import PageObjects.SignInPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import Listeners.TestListener;

import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class PlaceOrderTest extends BaseTest{


    //test pentru adaugare in cos 4 produse diferite + purchase
    @Test
    public void placeOrder() {
        driver.get(url);
        driver.manage().window().maximize();
        SignInPage signInPage = new SignInPage(driver);
        signInPage.navigateToSignIn();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.USERNAME_FIELD)));
        signInPage.signIn("Legion","Password");
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.LOGIN_LEGION_USER)));
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver,wait,logger);
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.SAMSUNG_GALAXY_INPUT)));
        placeOrderPage.selectSamsunGalaxy();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        //((JavascriptExecutor) driver).executeScript("window.close();");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.NOKIA_LUMIA_INPUT)));
        placeOrderPage.selectNokiaLumia();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        //((JavascriptExecutor) driver).executeScript("window.close();");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.LAPTOPS_LINK)));
        placeOrderPage.selectLaptops();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.SONYVAIO_INPUT)));
        placeOrderPage.selectSonyVaio();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        //((JavascriptExecutor) driver).executeScript("window.close();");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.MONITORS_LINK)));
        placeOrderPage.selectMonitors();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ASUS_INPUT)));
        placeOrderPage.selectAsus();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ADD_CART_INPUT)));
        placeOrderPage.addToCart();
        //((JavascriptExecutor) driver).executeScript("window.close();");
        wait.until(ExpectedConditions.alertIsPresent());
        placeOrderPage.acceptAlert();
        placeOrderPage.navigateToHome();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.CART_LINK)));
        placeOrderPage.selectCart();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ORDER_BUTTON)));
        placeOrderPage.selectOrderButton();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.ORDER_NAME)));
        placeOrderPage.fillOrderForm("Test name","Test country","Test city","Test credit card",
                "May","2024");
        placeOrderPage.selectPurchaseOrder();
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(OrderConstants.VALIDATION_MESSAGE)));
        String purchase = driver.findElement(By.xpath(OrderConstants.VALIDATION_MESSAGE)).getText();
        Assert.assertTrue("Purchase failed", purchase.contains("Thank you for your purchase!"));
        this.takeScreenshot("Order placed successfully");
        placeOrderPage.okButton();

    }
}
