package testcases;


import Until.Log;
import Until.TestListener;
import base.BaseSetup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.SkipException;

@Listeners(TestListener.class)
public class ListenerTC {

    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        driver = new BaseSetup().setupDriver("chrome");
    }

    @Test(priority = 1) //Success Test
    public void gotoPage() {
        Log.info("chạy test case: gotopage");
        driver.get("https://anhtester.com");
    }

    @Test(priority = 2) //Failed Test
    public void checkTitle() {
        Log.error("test case checktile");
        String expectedTitle = "Anh Tester";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, expectedTitle, "Title of the website do not match");
    }

    @Test(priority = 3)  //Skip Test
    public void skipTest() {
        throw new SkipException("Skipping The Test Method ");
    }

    public class SuccessPercentageDemo {
        int count = 0;

        @Test(invocationCount = 5, successPercentage = 50)
        public void kiemTraChanLe() {
            count++;
            System.out.println("Số lần chạy: " + count);

            if (count % 2 == 0) {
                Assert.assertTrue(false);
            } else {
                Assert.assertTrue(true);
            }
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}

