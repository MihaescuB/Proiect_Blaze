package PageObjects;

import java.util.logging.Logger;

import Constants.ContactConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactPage {
    private final WebDriver driver;
    private final Logger logger;
    private final By contactLink = By.xpath(ContactConstants.CONTACT_LINK);
    private final By emailInput = By.xpath(ContactConstants.EMAIL_INPUT);
    private final By nameInput = By.xpath(ContactConstants.NAME_INPUT);
    private final By messageTextArea = By.xpath(ContactConstants.MESSAGE_TEXTAREA);
    private final By sendMessageButton = By.xpath(ContactConstants.SEND_MESSAGE_BUTTON);

    public ContactPage(WebDriver driver, Logger logger) {
        this.driver = driver;
        this.logger = logger;
    }

    public void navigateToContactForm() {
        this.driver.findElement(contactLink).click();
        this.logger.info("Navigated to the contact form.");
    }

    public void fillContactForm(String email, String name, String message) {
        this.driver.findElement(emailInput).sendKeys(email);
        this.driver.findElement(nameInput).sendKeys(name);
        this.driver.findElement(messageTextArea).sendKeys(message);
        this.logger.info("Filled the contact form with provided data.");
    }

    public void submitContactForm() {
        this.driver.findElement(sendMessageButton).click();
        this.logger.info("Submitted the contact form.");
    }

    public void acceptAlert() {
        this.driver.switchTo().alert().accept();
        this.logger.info("Accepted the alert.");
    }
}
