package com.sharma.nks.config;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/features", plugin = {"pretty",
        "html:target/cucumber"})
public class CucumberFeatureTestRunner {
}
