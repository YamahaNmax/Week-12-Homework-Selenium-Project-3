package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    String baseUrl = "http://the-internet.herokuapp.com/login";
    String password = "SuperSecretPassword!";

    @Before

    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test

    public void UserShouldLoginSuccessfullyWithValidCredentials() {

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordLoginField = driver.findElement(By.id("password"));
        passwordLoginField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        String actualText = driver.findElement(By.tagName("h2")).getText();
        String expectedText = "Secure Area";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test

    public void verifyTheUsernameErrorMessage() {

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith1");

        WebElement passwordLoginField = driver.findElement(By.id("password"));
        passwordLoginField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        String actualText = driver.findElement(By.id("flash")).getText();
        String expectedText = "Your username is invalid!\n" +
                "×";
        Assert.assertEquals(actualText, expectedText);
    }

    @Test

    public void verifyThePasswordErrorMessage() {

        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("tomsmith");

        WebElement passwordLoginField = driver.findElement(By.id("password"));
        passwordLoginField.sendKeys("SuperSecretPassword");

        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        String actualText = driver.findElement(By.id("flash")).getText();
        String expectedText = "Your password is invalid!\n" +
                "×";
        Assert.assertEquals(actualText, expectedText);
    }


    @After
    public void endTest() {
        closeBrowser();
    }
}
