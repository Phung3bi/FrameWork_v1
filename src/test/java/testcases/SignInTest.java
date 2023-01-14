package testcases;

import base.BaseSetup;
import base.BaseValidateHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignInPage;

import java.util.concurrent.TimeUnit;


public class SignInTest {
    private WebDriver driver;
    private BaseValidateHelper validatehelper;
    private SignInPage signInPage;


    public By loginBtn = By.id("btn-login");
    public By selectdropdown = By.id("dropdown");

    @Test
    public void login() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        validatehelper = new BaseValidateHelper(driver);
        signInPage = new SignInPage(driver);

        driver.get("http://anhtester.com");
        signInPage.signIn("anhtester@mailinator.com","Demo@123");

        Thread.sleep(2000);
        driver.quit();





    }
}

