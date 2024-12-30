package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(publish=true,features="src/test/resources",
        glue="stepdefinition"
)
public class TestNGrUNNER  {
	
}