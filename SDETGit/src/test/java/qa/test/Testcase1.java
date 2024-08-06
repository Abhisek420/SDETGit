package qa.test;

import org.testng.annotations.Test;

import base.baseTest;
import freemarker.log.Logger;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;

public class Testcase1 extends baseTest{
	
	//this is  a testNg class for sample login method . This can be customized based upon the requirement 
	
	ElementFetch ele=new ElementFetch();
	HomePageEvents homePage=new HomePageEvents();
	LoginPageEvents loginPage=new LoginPageEvents();
  @Test
  public void samplemethodforEnteringCredentials() {
	  
	 logger.info("Signin to Login page");
	  homePage.signInBtn();
	 logger.info("Verifying if Login test is present or not");
	  loginPage.verifyIfLoginPageIsLoaded();
	  logger.info("Entering the creadentials");
	  loginPage.enterCredentials();
  }
}
