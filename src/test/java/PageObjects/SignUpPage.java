package PageObjects;

import Constants.SignUpConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    private final WebDriver driver;

    private final By signUpLink = By.xpath(SignUpConstants.SIGN_UP_LINK);
    private final By usernameField = By.xpath(SignUpConstants.USERNAME_FIELD);
    private final By passwordField = By.xpath(SignUpConstants.PASSWORD_FIELD);
    private final By signUpButton = By.xpath(SignUpConstants.SIGN_UP_BUTTON);

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToSignUp() {
        driver.findElement(signUpLink).click();
    }

    public void signUp(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signUpButton).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }
}
