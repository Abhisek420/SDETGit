package base;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class baseTest {
	public static WebDriver driver;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;

//common method for capturing screenshots for failed methods and for reporting

	@BeforeTest
	public void beforeTestMethod() {
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"mycrmreport.html");
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);

		extent.setSystemInfo("HostName", "RHEL8");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Test Results for crm.com");
	}
	@BeforeMethod
	@Parameters("browser")
	public void beforemethod(String browser,Method testMethod) {
		// TODO Auto-generated method stub
		
		logger=extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	@AfterMethod
	public void afterMethod(ITestResult itestresult) {
		if(itestresult.getStatus()==ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(itestresult.getName()+" - Testcase Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(itestresult.getThrowable()+" - Testcase Failed", ExtentColor.RED));	
		}
		else if (itestresult.getStatus()==ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(itestresult.getName()+" - Testcase skiped", ExtentColor.ORANGE));
		}
		else if (itestresult.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(itestresult.getName()+" - Testcase Pass", ExtentColor.GREEN));
		}

	}

	@AfterTest
	public void aftertest() {
		extent.flush();

	}

	
	
	//for initialization of different browsers
	public void setupDriver(String Browser) {
		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().clearDriverCache().setup();
			//WebDriverManager.chromedriver().create();
			driver=new ChromeDriver();
		}
		else if (Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if (Browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
	}
}
