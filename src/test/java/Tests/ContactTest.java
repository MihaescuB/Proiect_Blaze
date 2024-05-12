package Tests;

import Constants.ContactConstants;
import PageObjects.ContactPage;
import Utils.RandomGeneratorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ContactTest extends BaseTest {

    @Test
    public void contactTest() {
        this.logger.info("Starting Contact Test");

        driver.get(url);
        driver.manage().window().maximize();

        ContactPage contactPage = new ContactPage(driver, logger);
        contactPage.navigateToContactForm();

        this.logger.info("Navigated to Contact Form");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ContactConstants.EMAIL_INPUT)));
        this.takeScreenshot("ContactForm_Visible");

        this.logger.info("Contact Form is visible");

        contactPage.fillContactForm(
            RandomGeneratorUtils.generateEmail(),
            RandomGeneratorUtils.generateName(),
            RandomGeneratorUtils.generateMessage()
        );

        this.logger.info("Filled Contact Form");

        contactPage.submitContactForm();
        wait.until(ExpectedConditions.alertIsPresent());
        contactPage.acceptAlert();
        this.takeScreenshot("ContactForm_Submitted");
    }
}
