package utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class GeneralUtils {
	WebDriver driver;
	String parentWindow;
	String childWindow;
	public GeneralUtils(WebDriver driver) {
		this.driver=driver;
	}

	public void switchToNewTab() {
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> iterator=windows.iterator();
		 parentWindow=iterator.next();
		 childWindow=iterator.next();
		driver.switchTo().window(childWindow);
	}
	public void switchToParentTab() {
		
		driver.switchTo().window(parentWindow);
	}
}
