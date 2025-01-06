package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Toplinks {

    @Given("user visited the landing page")
    public void user_visited_the_landing_page() {
        System.out.println("System.is.launched");
        System.out.println("System.is.launched");
    }
    @When("user hit the toplinks menu button")
    public void user_hit_the_toplinks_menu_button() throws InterruptedException {

        System.out.println("System.is.launched");

    }
        @Then("user landed on toplinks page")
        public void user_landed_on_toplinks_page() {
            System.out.println("System.is.launched");
        }
    }