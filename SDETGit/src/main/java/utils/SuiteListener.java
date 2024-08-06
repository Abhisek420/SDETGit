package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import base.baseTest;

public class SuiteListener implements ITestListener ,IAnnotationTransformer
{
	//To take screenshots for failed methods
	 public void onTestFailure(ITestResult result) {
		String filename=System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+result.getMethod().getMethodName();
		File f1=((TakesScreenshot)baseTest.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f1, new File(filename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  }
	 //for retrying failed methods
	 public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) 
	 {
	 	   annotation.setRetryAnalyzer(RetryAnalyser.class);
	 	   
	 	  }
}




