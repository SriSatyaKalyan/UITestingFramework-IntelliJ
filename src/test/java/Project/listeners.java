/**
 * Author: Satya 
 * Purpose: This class file is the listeners file which keeps a tab on the tests and executes intended functions accordingly
*/

package Project;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.baseClass;

public class listeners implements ITestListener{
	
	//There are many methods which are not filled with code because this test suite does not use all of them
	baseClass base = new baseClass();

	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult result) {
		String nameoftest = result.getName();
		
		try {
			base.getScreenshot(nameoftest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult arg0) {
	}

	public void onTestStart(ITestResult arg0) {
	}

	public void onTestSuccess(ITestResult arg0) {
	}
}
