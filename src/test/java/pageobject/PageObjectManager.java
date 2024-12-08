package pageobject;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	public WebDriver driver;
	public Landingpage landingpage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver=driver;
	}
	
	public Landingpage landingpage() {
		landingpage= new Landingpage(driver);
		return landingpage;
	}

}
