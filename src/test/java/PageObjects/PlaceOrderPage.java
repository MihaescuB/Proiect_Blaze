package PageObjects;


import Constants.OrderConstants;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.xpath.operations.Or;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.OptionalDouble;
import java.util.logging.Logger;

public class PlaceOrderPage {

    private final WebDriver driver;

    private final WebDriverWait wait;

    private final Logger logger;

    private final By homeLink = By.xpath(OrderConstants.HOME_LINK);

    private final By samsungGalaxyInput = By.xpath(OrderConstants.SAMSUNG_GALAXY_INPUT);

    private final By addCartInput = By.xpath(OrderConstants.ADD_CART_INPUT);

    private final By nokiaLumiaInput = By.xpath(OrderConstants.NOKIA_LUMIA_INPUT);

    private final By cartLink = By.xpath(OrderConstants.CART_LINK);

    private final By laptopsLink = By.xpath(OrderConstants.LAPTOPS_LINK);

    private final By sonyVaioInput = By.xpath(OrderConstants.SONYVAIO_INPUT);

    private final By monitorsLink = By.xpath(OrderConstants.MONITORS_LINK);

    private final By asusInput = By.xpath(OrderConstants.ASUS_INPUT);

    private final By orderButton = By.xpath(OrderConstants.ORDER_BUTTON);

    private final By orderName = By.xpath(OrderConstants.ORDER_NAME);

    private final By orderCountry = By.xpath(OrderConstants.ORDER_COUNTRY);

    private final By orderCity = By.xpath(OrderConstants.ORDER_CITY);

    private final By orderCard = By.xpath(OrderConstants.ORDER_CARD);

    private final By orderMonth = By.xpath(OrderConstants.ORDER_MONTH);

    private final By orderYear = By.xpath(OrderConstants.ORDER_YEAR);

    private final By purchaseOrder = By.xpath(OrderConstants.PURCHASE_ORDER);

    private final By validationMessage = By.xpath(OrderConstants.VALIDATION_MESSAGE);

    private final By okButton = By.xpath(OrderConstants.OKButton);


    public PlaceOrderPage(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.wait = wait;
    }

    public void navigateToHome(){
        this.driver.findElement(homeLink).click();
        this.logger.info("Navigated to the home.");
    }

    public void addToCart(){
        this.driver.findElement(addCartInput).click();
        this.logger.info("Add to cart.");
    }

    public void selectSamsunGalaxy(){
        this.driver.findElement(samsungGalaxyInput).click();
        this.logger.info("Add to cart Samsung Galaxy.");
    }

    public void selectNokiaLumia(){
        this.driver.findElement(nokiaLumiaInput).click();
        this.logger.info("Add to cart Nokia Lumia.");
    }

    public void selectCart(){
        this.driver.findElement(cartLink).click();
        this.logger.info("Cart section selected.");
    }

    public void selectLaptops(){
        this.driver.findElement(laptopsLink).click();
        this.logger.info("Laptops section selected.");
    }

    public void selectMonitors(){
        this.driver.findElement(monitorsLink).click();
        this.logger.info("Montiors section selected.");
    }

    public void selectSonyVaio(){
        this.driver.findElement(sonyVaioInput).click();
        this.logger.info("Sony Vaio selected.");
    }

    public void selectAsus(){
        this.driver.findElement(asusInput).click();
        this.logger.info("Asus selected.");
    }

    public void selectOrderButton(){
        this.driver.findElement(orderButton).click();
        this.logger.info("Order button selected.");
    }

    public void selectPurchaseOrder(){
        this.driver.findElement(purchaseOrder).click();
        this.logger.info("Purchase Order button selected.");
    }

    public void fillOrderForm(String name, String country, String city, String card, String mounth, String year){
        this.driver.findElement(orderName).sendKeys(name);
        this.driver.findElement(orderCountry).sendKeys(country);
        this.driver.findElement(orderCity).sendKeys(city);
        this.driver.findElement(orderCard).sendKeys(card);
        this.driver.findElement(orderMonth).sendKeys(mounth);
        this.driver.findElement(orderYear).sendKeys(year);
        this.logger.info("Filled the Order form with provided data.");
    }

    public void validationMessage(){
        this.driver.findElement(validationMessage);
        this.logger.info("Validation message present");
    }

    public void okButton(){
        this.driver.findElement(okButton).click();
        this.logger.info("OK BUTTON clicked");
    }

    public void acceptAlert() {
        this.driver.switchTo().alert().accept();
        this.logger.info("Accepted the alert.");
    }

    public void deleteItems(){
        while (true) {
            WebElement deleteButton = null;
            try {
                deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Delete']")));
            } catch (Exception e) {

                break;
            }
            deleteButton.click();

            wait.until(ExpectedConditions.stalenessOf(deleteButton));
        }
    }


}
