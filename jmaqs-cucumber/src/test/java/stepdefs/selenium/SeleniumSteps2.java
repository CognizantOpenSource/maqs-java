/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package stepdefs.selenium;

import com.cognizantsoftvision.maqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.When;

public class SeleniumSteps2  extends BaseSeleniumStep {

    @When("Selenium Step2")
    public void Step2() throws Throwable {
        this.getLogger().logMessage("Step2");
    }

}
