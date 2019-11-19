package com.sharma.nks.config;

import com.customer.service.impl.model.Customer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.time.LocalDate;

public class CreateCustomer extends CucumberBaseStep {

    @Given("^an authenticated user$")
    public void anAuthenticatedUser() {
    }

    @When("^user adds new customer$")
    public void userAddsNewCustomer() {
        createCustomer(buildCustomer());
    }

    @Then("^customer should be created$")
    public void customerShouldBeCreated() {
        Assert.assertEquals(buildCustomer(), localCustomer.get().getBody());
    }

    private Customer buildCustomer() {
        return new Customer().withCreationDate(LocalDate.now().toString()).withCustomerId(11111)
                .withEmailId("dlcgv@c.co").withFirstName("Dian").withLastName("Clove").withPhoneNumber("1234523");
    }
}
