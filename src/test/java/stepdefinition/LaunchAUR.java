package stepdefinition;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobject.SlackNotifier;
import utils.Testcontextsetup;

public class LaunchAUR {
	
	public Testcontextsetup test;
	public LaunchAUR(Testcontextsetup test) {
		this.test=test;
	}
	@Given("I have the url and launch the browser")
	public void i_have_the_url_and_launch_the_browser() {
		
	     System.out.println("System.is.launched");
	     String reportLink = "link to your test report";
	     String message = "Test execution completed: " + reportLink; 
	     	    SlackNotifier.sendSlackNotification(message);
	}
	@When("compare the url")
	public void compare_the_url() {
	 test.currentURL="https://rahulshettyacademy.com/seleniumPractise/#/";
	 test.currentpage=test.testbase.WebDriverManager().getCurrentUrl();
	}
	
}
