package stepdefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobject.Landingpage;
import utils.Testcontextsetup;

public class TopLinks {
	public Landingpage landingpage;
	public Testcontextsetup testcontextsetup;
	public TopLinks(Testcontextsetup testcontextsetup) {
		this.testcontextsetup=testcontextsetup;
		this.landingpage=testcontextsetup.pageobjectmanager.landingpage();
	}
	@Given("user visited the landing page")
	public void user_visited_the_landing_page() {
		Assert.assertEquals(testcontextsetup.testbase.WebDriverManager().getCurrentUrl(),"https://rahulshettyacademy.com/seleniumPractise/#/", "user is on landing page");
	  
	}
	@When("user hit the toplinks menu button")
	public void user_hit_the_toplinks_menu_button() throws InterruptedException {
		
		landingpage.topDeals();
		Thread.sleep(5000);
	 
	}
	@Then("user landed on toplinks page")
	public void user_landed_on_toplinks_page() {
	    testcontextsetup.generalutils.switchToNewTab();
	    Assert.assertEquals(testcontextsetup.testbase.WebDriverManager().getCurrentUrl(), "https://rahulshettyacademy.com/seleniumPractise/#/offers", "user is on top deals page");
	}
}
