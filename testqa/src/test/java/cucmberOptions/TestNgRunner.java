package cucmberOptions;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src\\test\\java\\features",
		glue="stepDefinition",
		tags="@hrm"
		)
public class TestNgRunner {

}
