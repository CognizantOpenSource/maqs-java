import com.magenic.jmaqs.cucumber.BaseGenericCucumber;
import com.magenic.jmaqs.cucumber.BaseSeleniumCucumber;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions( glue = {"stepdefs.generic"}, plugin = {"pretty"}, features = {"src/test/resources/generic"})
public class TestRunnerGeneric extends BaseGenericCucumber {


}