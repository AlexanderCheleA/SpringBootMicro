package com.tcs.arq.micro.accounts.serenity.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.tcs.arq.micro.accounts.serenity.definitions"
)
public class CucumberTestSuite {
}
