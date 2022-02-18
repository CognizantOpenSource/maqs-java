/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

import com.cognizantsoftvision.maqs.cucumber.BaseGenericCucumber;
import io.cucumber.testng.CucumberOptions;


/**
 * Generic glue.
 */
@CucumberOptions( glue = {"stepdefs.generic"}, plugin = {"pretty"}, features = {"src/test/resources/generic"})
public class TestRunnerGeneric extends BaseGenericCucumber {

}