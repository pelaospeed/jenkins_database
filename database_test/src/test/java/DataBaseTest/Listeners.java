package DataBaseTest;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener  {

    public void onFinish(ITestContext arg0) {
        System.out.println("On Finish: " +arg0.getName());
    }

    public void onStart(ITestContext arg0) {
        System.out.println("Start: " +arg0.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        System.out.println("TestFailedButWithinSuccessPercentage: " +arg0.getName());
    }

    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        //screenshot
        System.out.println("I failed executed com.automation.test.Listeners Pass code: " +result.getName());
    }

    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub
        System.out.println(arg0.getName()+" test case started");
    }

    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub
        System.out.println("The name of the testcase passed is :"+arg0.getName());
    }
}
