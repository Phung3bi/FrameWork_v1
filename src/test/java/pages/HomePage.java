package pages;

import base.BaseValidateHelper;
import org.apache.xmlbeans.DelegateXmlObject;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.security.Key;

public class HomePage {
    private WebDriver driver;
    private BaseValidateHelper valisateHelper;
    private By email = By.xpath("//in");
    private By pass = By.xpath("//in");
    private By emailInput = By.xpath("//input[@placeholder='Email']");
    private By passInput = By.xpath("//input[@placeholder='Password']");
    private By loginbtn = By.xpath("//button[@id='login']");
    private By iframecapcha = By.xpath("//iframe[@title='reCAPTCHA']");
    private By buton_login = By.xpath("//a[@id='btn-login']");
    private String url = "http://localhost";
    private Actions action;




    public HomePage(WebDriver driver) {
        this.driver = driver;
        valisateHelper = new BaseValidateHelper(driver);
        action = new Actions(driver);
    }


    ///////////
    public void getURL() {
        Assert.assertTrue(valisateHelper.verifyUrl(url));
        valisateHelper.clickElement(email);

    }

    public void verifytext() {
        Assert.assertTrue(valisateHelper.verifyElementText(email,"emailhi"));
    }

    public void action() {
        action = new Actions(driver);
        action.sendKeys().build().perform();
    }

    public HomePage signIn(String email, String pass) throws InterruptedException {
        valisateHelper.clickElement(buton_login);
        valisateHelper.setText(emailInput, email);
        valisateHelper.setText(passInput, pass);
//        Thread.sleep(2000);
//        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@title='reCAPTCHA']")));
//        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("iframe[title='reCAPTCHA']"))).click();
        valisateHelper.clickElement(loginbtn);
        return new HomePage(this.driver);

    }

}
