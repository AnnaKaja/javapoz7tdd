package com.sda.service.cucumber;

import com.sda.services.StringCalculator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class StringCalculatorSteps {

    private String value;

    private int result;

    private StringCalculator calculator;

    @Given("^I initialize stringCalculator$")
    public void i_intialize_stringCalculator() {
        this.calculator = new StringCalculator();
    }

    @Given("^I pass (.*) value$")
    public void I_pass_single_number_value(String passedValue) {
        this.value = passedValue;

    }

    @And("^I pass value null$")
    public void I_pass_null_value() {
        this.value = null;
    }


    @When("^I trigger calculate function$")
    public void I_trigger_calculate_function() {
        this.result = calculator.calculate(value);

    }

    @Then("^I get (.*) as a result$") //(.*)
    public void I_gert_5_as_a_resut(int passedResult) {
        Assert.assertEquals(passedResult, result);

    }
}


