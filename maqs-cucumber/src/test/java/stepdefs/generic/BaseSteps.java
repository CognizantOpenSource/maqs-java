/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package stepdefs.generic;

import com.cognizantsoftvision.maqs.cucumber.steps.BaseGenericStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaseSteps extends BaseGenericStep {

    @Given("^Base Step$")
    public void Step() {

        this.getLogger().logMessage("Base step");
    }
    @When("Base Step2")
    public void Step2() throws Throwable {
        this.getLogger().logMessage("Base step2");
    }

    @Then("Base Step3")
    public void Step3() throws Throwable {
        this.getLogger().logMessage("Base step3");
    }
}