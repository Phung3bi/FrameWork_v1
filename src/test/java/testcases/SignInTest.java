package testcases;

import Until.captureHelpers;
import Until.ExcelHelpers;
import Until.PropertiesFile;
import base.BaseSetup;
import base.BaseValidateHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignInPage;

import java.io.File;
import java.io.IOException;


public class SignInTest {
    private WebDriver driver;
    private BaseValidateHelper validatehelper;
    private SignInPage signInPage;
    private ExcelHelpers excel;
    private captureHelpers captureHelpers;

    public By loginBtn = By.id("btn-login");
    public By selectdropdown = By.id("dropdown");

    @BeforeClass
    public void setupBrownser() throws Exception {
        driver = new BaseSetup().setupDriver("chrome");
        validatehelper = new BaseValidateHelper(driver);
        excel = new ExcelHelpers();
        // Gọi hàm để khởi tạo file properties
        PropertiesFile.setPropertiesFile();
        //record bat dau
        captureHelpers.startRecord("Tenfile");

        // Đọc data từ file properties với key là "browser"
        driver = new BaseSetup().setupDriver(PropertiesFile.getPropValue("browser"));
    }

    @Test(priority =1)
    public void login() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        validatehelper = new BaseValidateHelper(driver);
        signInPage = new SignInPage(driver);

        driver.get("http://anhtester.com");
        signInPage.signIn("anhtester@mailinator.com","Demo@123");
        validatehelper.waitForPageLoaded();

        Thread.sleep(2000);
        driver.quit();

    }
    @Test(priority =2)
    public void signInPage() throws Exception {
        excel.setExcelFile("src/main/resources/file.xlsx","sheet1");
        signInPage = new SignInPage(driver);
        driver.get(new BaseSetup().getUrl());
        signInPage.signIn(excel.getCellData("user",1),excel.getCellData("pass",1));
    }
    @Test(priority =3)
    public void signInPageReadExcelDynamic() throws Exception {

        //Setup đường dẫn của file excel
        excel.setExcelFile("src/test/resources/Book1.xlsx", "Sheet1");

        signInPage = new SignInPage(driver);
        driver.get("https://crm.anhtester.com");

        for (int i = 0; i < 6; i++) {
            signInPage.signIn(excel.getCellData("username", i), excel.getCellData("password", i));
            Thread.sleep(1000);
        }

        // Ghi nhiều dòng vào file
        for (int i = 0; i < 6; i++) {
            excel.setCellData("AN01", i, 3);
        }
    }
    @Test(priority =4)
    public void signinCRM() throws InterruptedException {
        signInPage = new SignInPage(driver);
        driver.get("https://crm.anhtester.com");
        //Set giá trị vào file properties
        PropertiesFile.setPropValue("email", "admin02@mailinator.com");
        // Đọc data từ file properties
        signInPage.signIn(PropertiesFile.getPropValue("email"),PropertiesFile.getPropValue("password"));

    }
    // Nó sẽ thực thi sau mỗi lần thực thi testcase (@Test)
    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        Thread.sleep(1000);
        //Khởi tạo đối tượng result thuộc ITestResult để lấy trạng thái và tên của từng Test Case
        //Ở đây sẽ so sánh điều kiện nếu testcase passed hoặc failed
        //passed = SUCCESS và failed = FAILURE
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                captureHelpers.captureScreenshot(driver, result.getName());
               } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
    }
    @AfterClass
    public void closeBrowser() throws Exception {
        driver.quit();
        //record bat dau
        captureHelpers.stopRecord();
    }

}

