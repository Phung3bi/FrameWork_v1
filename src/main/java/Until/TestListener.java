package Until;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.DriverManager;
public class TestListener implements ITestListener {

    private DriverManager BaseSetup;

    @Override
    public void onFinish(ITestContext result) {

    }

    @Override
    public void onStart(ITestContext result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("Đây là test case bị fail: " + result.getName());
    //chụp màn hình khi failed
//        try {
//            captureHelpers.captureScreenshot(BaseSetup.getDriver(), result.getName());
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot " + e.getMessage());
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("Đây là test case bị bỏ qua: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("Đây là test case chạy thành công: " + result.getName());
        //không in thì ta có thể làm bất cứ thứ gì như: in, report, count, chụp hình, record , ghi log, xuất execl , time lỗi....

    }
}