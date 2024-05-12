package Tests;

import Constants.SignUpConstants;
import Data.User;
import PageObjects.SignUpPage;
import Utils.DatabaseUtils;
import Utils.JsonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;


public class SignUpTest extends BaseTest{
    private List<User> users;

    @BeforeClass
    public void setUp() {
        DatabaseUtils.createTableUsers();
        this.users = JsonUtils.readUsersFromJson("src/test/resources/signup.json");
        super.setUp();
    }

    @Test
    public void signUpTest() {
        for(User user : users) {
            driver.get(url);
            driver.manage().window().maximize();

            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.navigateToSignUp();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SignUpConstants.USERNAME_FIELD)));

            signUpPage.signUp(user.getUsername(), user.getPassword());

            wait.until(ExpectedConditions.alertIsPresent());

            String alertText = driver.switchTo().alert().getText();

            driver.switchTo().alert().accept();

            assert alertText.equals("Sign up successful.");

            DatabaseUtils.createUser(user);
        }
    }
}
