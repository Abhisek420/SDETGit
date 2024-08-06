package pageObjects;

public interface LoginPageObjects {
	//All the weblelement locators for the Login Page are stored here
	
	String loginbtn="//*[@id='ui']/div/div/form/div/div[contains(text(),'Login')]";
	String emailaddress="//input[@placeholder='Email']";
	String pwdaddress="//input[@placeholder='Password']";
}
