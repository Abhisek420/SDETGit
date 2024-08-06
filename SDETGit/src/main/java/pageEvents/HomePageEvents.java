package pageEvents;

import pageObjects.HomePageObjects;
import utils.ElementFetch;
//All the methods for the Homepage are stored here

public class HomePageEvents {
ElementFetch ele=new ElementFetch();
public void signInBtn() {
	ele.getwebelement("XPATH", HomePageObjects.signInBtnText).click();
}
}
