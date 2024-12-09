package stepdefinition;


import io.cucumber.java.en.Then;

import org.junit.Assert;
import pageobject.SlackNotifier;
import utils.Testcontextsetup;

public class Compar {
	public Testcontextsetup test;
	public Compar(Testcontextsetup test) {
		this.test=test;
	}
	@Then("print the statement")
	public void print_the_statement() {
	    Assert.assertEquals(test.currentpage, test.currentURL);
	    String reportLink = "link to your test report";
String message = "Test execution completed: " + reportLink; 
	    SlackNotifier.sendSlackNotification(message);

	}
}
