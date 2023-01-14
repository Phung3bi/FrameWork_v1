package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.List;

    public class BaseValidateHelper {
    private static WebDriver driver;
    private Duration timeoutWaitForPageLoaded = Duration.ofSeconds(5);
    private WebDriverWait wait;
    JavascriptExecutor js;
    private Actions action;
    private List list;

    private By element = By.xpath("abc");


    public BaseValidateHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        this.list = list;
    }

    public BaseValidateHelper() {
    }


    public void setText(By element, String value) {
        waitLoadedFULL();
        // sendkeys mot gia tri la value cho element truyen vao
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }
//    public void scroll(By z) {
//        js.executeScript("arguments[0].scrollIntoView(true);",z);
//    }

    public void clickElement(By element) {
        waitLoadedFULL();
        // click vao 1 phan tu
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
        // clcik co 2 dang: dang tren va click by JS
        //js.executeScript("arguments[0].click();", element);

    }

    public void clickElementByJs(By element) {
        waitLoadedFULL();
        // doi den khi elemnet ton tai
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // scroll to element voi js
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(element));
        // click voi js
        js.executeScript("arguments[0].click();", driver.findElement(element));

    }
     //click chuot phai
    public void rightClickElement (By element) {
        waitLoadedFULL();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        action.contextClick().build().perform();
    }

    // handle dropdown select option
    public void selectOptionByText(By element, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        //de su dung doi tuong by By ta can them element vao
        Select select = new Select(driver.findElement(element)); // truongfw hop nay khong khoi taop tren contructor vi can 1 element cu the
        select.selectByVisibleText(text);

    }

    public void selectOptionByValue(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        //de su dung doi tuong by By ta can them element vao
        Select select = new Select(driver.findElement(element));
        select.selectByValue(value);

    }

    // kiem tra tong so luong option
    public void verifyOptionTotal(By element, int total){
        Select select = new Select(driver.findElement(element));
        Assert.assertEquals(total, select.getOptions().size());

    }
    public void checkSearchTable(int column, String value){
        //Xac dinh so luong dong sau khi search
        //so dong tuong ung voi the 'tr': search xptath = //tr se ra sl dong
        // table > thead > tr chua tieu de > body chua so luong dong
        // search //table//tbody//tr
        List<WebElement> row = (List<WebElement>) driver.findElement(By.xpath("//table//tbody/tr"));
        int rowtotal = row.size();
        for (int i = 0; i < rowtotal; i++) {//mang bat dau tu 0
            //cot khong thay doi, dong thay doi
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr["+i+"]/td["+column+"]"));
            //scroll full page
            js.executeScript("arguments[0].scrollIntoView();", elementCheck);
            //in gia tri truen vao
            System.out.print("value"+"-");
            //in gia tri thuc te
            System.out.println(elementCheck.getText());
            //kiem tra gia tri text nhap vao co chua khong
            //chuyen in hoa 2 gia tri truyen vao va thuc te
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()),"khong chua gia tr tim kiem");
            }
        }


    // Search 1 gia tri
        public  void enterSearchValue (){
            setText(element,"value");
        }

    // wait for Javascript to loaded
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expection = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expection);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }

    // wait for Javascript to loaded. chi danh cho chuyen trang
    public void waitForJQeryLoaded() {
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long)((JavascriptExecutor)driver).executeScript("return jQuery.active")==0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang CUA JQUERY.");
        }
    }

    // wwait for javascript to loaded and jqery loaded
    public void waitLoadedFULL() {
        ExpectedCondition<Boolean> expection = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((long)((JavascriptExecutor)driver).executeScript("return jQuery.active")==0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expection);
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }
    }

    //Lay ra url de verify
    public boolean verifyUrl(String url){
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);
        return driver.getCurrentUrl().contains(url);//true/fales
    }

    //verify 1 element voi 1 gia tri text
    //Cac ham co xu ly findElement thi nen chen ham wait
    public boolean verifyElementText(By element, String textValue ){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue);//true/fales
    }

    //verify 1 element co ton tai o 1 page
    public boolean verifyElementExist(By element ){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    //tao bien chua danh sach ca element
        List<WebElement> listElements = driver.findElements(element);
        int total = listElements.size();
        if(total > 0){
            return true;
        }
        return false;
    }

    //


}
