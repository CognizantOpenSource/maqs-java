/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package stepdefs.selenium;

import com.cognizantsoftvision.maqs.cucumber.steps.BaseSeleniumStep;
import io.cucumber.java.en.Then;

public class SeleniumSteps3 extends BaseSeleniumStep {

    @Then("Selenium Step3")
    public void Step3() throws Throwable {
        this.getLogger().logMessage("Step3");
    }
}
