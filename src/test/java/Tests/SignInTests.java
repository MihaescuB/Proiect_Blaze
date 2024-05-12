package Tests;

import Constants.SignInConstants;
import Constants.SignUpConstants;
import Data.User;
import PageObjects.SignInPage;
import PageObjects.SignUpPage;
import Utils.DatabaseUtils;
import Utils.RandomGeneratorUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import Listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(TestListener.class)
public class SignInTests extends BaseTest{
    private List<User> users;

    @BeforeClass
    public void setUpUsers() {
        DatabaseUtils.createTableUsers();
        this.users = DatabaseUtils.getUsers();
    }

    @Test
    public void signInAllFlowTest() {
        driver.get(url);
        driver.manage().window().maximize();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.navigateToSignUp();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SignUpConstants.USERNAME_FIELD)));

        String username = RandomGeneratorUtils.generateRandomUsername(10);

        signUpPage.signUp(username, "Password");

        wait.until(ExpectedConditions.alertIsPresent());
        signUpPage.acceptAlert();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.navigateToSignIn();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.USERNAME_FIELD)));

        signInPage.signIn(username, "Password");
    }

    @Test
    public void signIn() {
        driver.get(url);
        driver.manage().window().maximize();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.navigateToSignIn();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SignInConstants.USERNAME_FIELD)));

        Assert.assertTrue("No users found in the database",users.size() > 0);

        User user = users.get(0);

        signInPage.signIn(user.getUsername(), user.getPassword());
    }
}
