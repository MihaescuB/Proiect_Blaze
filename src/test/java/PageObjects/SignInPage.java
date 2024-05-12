package PageObjects;

import Constants.SignInConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private final WebDriver driver;

    private final By signInLink = By.xpath(SignInConstants.SIGN_IN_LINK);
    private final By usernameField = By.xpath(SignInConstants.USERNAME_FIELD);
    private final By passwordField = By.xpath(SignInConstants.PASSWORD_FIELD);
    private final By signInButton = By.xpath(SignInConstants.SIGN_IN_BUTTON);

    private final By loginLegionUser = By.xpath(SignInConstants.LOGIN_LEGION_USER);

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToSignIn() {
        driver.findElement(signInLink).click();
    }

    public void navigateToLegionUser() {
        driver.findElement(loginLegionUser);
    }

    public void signIn(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}
