package stepdefinition;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LaunchAUR {
	

	@Given("I have the url and launch the browser")
	public void i_have_the_url_and_launch_the_browser() {
		
	     System.out.println("System.is.launched");

	}
	@When("compare the url")
	public void compare_the_url() {
		System.out.println("System.is.launched");
	}
	
}
