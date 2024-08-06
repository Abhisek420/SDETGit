package pageEvents;

import org.testng.Assert;

import pageObjects.LoginPageObjects;
import utils.ElementFetch;

//All the methods for the Login page are stored here

public class LoginPageEvents {
ElementFetch ele=new ElementFetch();

public void verifyIfLoginPageIsLoaded()
{
	Assert.assertTrue(ele.getwebelements("XPATH", LoginPageObjects.loginbtn).size()>0, "Element Not Found");
}

public void enterCredentials() {
	ele.getwebelement("XPATH", LoginPageObjects.emailaddress).sendKeys("rmishra@gmail.com");
	ele.getwebelement("XPATH", LoginPageObjects.pwdaddress).sendKeys("12345");
}
}
