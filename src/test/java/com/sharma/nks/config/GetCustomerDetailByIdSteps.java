package com.sharma.nks.config;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.HttpStatus;

public class GetCustomerDetailByIdSteps extends CucumberBaseStep {

    @When("^user requests for customer details by customerId '(.*)'$")
    public void userRequestsForCustomerDetailsByCustomerIdCustomer_id(String customerId) {
        getCustomerDetails(customerId);
    }

    @Then("^customer detail should be retrieved with customerId '(.*)'$")
    public void customerDetailShouldBeRetrievedWithCustomerIdCustomer_id(String customerId) throws Throwable {
        Assert.assertNotNull(localCustomer.get());
        Assert.assertEquals(customerId, localCustomer.get().getBody().getCustomerId());
    }

    @And("^status code is '(.*)'$")
    public void statusCodeIsHttp_status(String httpStatus) {
        switch (httpStatus) {
            case "OK":
                Assert.assertEquals(HttpStatus.OK, localCustomer.get().getStatusCode());
                break;
            case "NOT FOUND":
                Assert.assertEquals(HttpStatus.NOT_FOUND, localCustomer.get().getStatusCode());
                break;
        }
    }
}
