package rahulshettyacademy.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	// This class will be called from testng.xml through listeners tag
	
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> local = new ThreadLocal<ExtentTest>();// thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
	    // not implemented
		test = extent.createTest(result.getMethod().getMethodName());
		local.set(test);
	  }

	@Override
	public void onTestSuccess(ITestResult result) {
	    // not implemented
		local.get().log(Status.PASS, "Passed");
	  }
	@Override
	public void onTestFailure(ITestResult result) {
	    // not implemented
		local.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());// field is class level not associated with method
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		local.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	  }
	
	@Override
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }
}
