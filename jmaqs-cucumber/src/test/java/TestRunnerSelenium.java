import com.magenic.jmaqs.cucumber.BaseSeleniumCucumber;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions( glue = {"stepdefs.selenium"}, plugin = {"pretty"}, features = {"src/test/resources/selenium"})
public class TestRunnerSelenium extends BaseSeleniumCucumber {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}