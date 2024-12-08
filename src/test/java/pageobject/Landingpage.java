package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Landingpage {
	public WebDriver driver;
	
	public Landingpage(WebDriver driver) {
		this.driver=driver;
	}
	private By topdeals=By.xpath("//a[contains(text(),'Top Deals')]");
	
	public void topDeals() {
		driver.findElement(topdeals).click();
	}

}
