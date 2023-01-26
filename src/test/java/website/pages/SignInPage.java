package website.pages;

import common.BaseValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;
    private BaseValidateHelper valisateHelper;

    private By emailInput = By.xpath("//input[@placeholder='Email']");
    private By passInput = By.xpath("//input[@placeholder='Password']");
    private By loginbtn = By.xpath("//button[@id='login']");
    private By iframecapcha = By.xpath("//iframe[@title='reCAPTCHA']");

    private By buton_login = By.xpath("//a[@id='btn-login']");

    public SignInPage(WebDriver driver)
    {
        this.driver = driver;
        valisateHelper = new BaseValidateHelper(driver);
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

