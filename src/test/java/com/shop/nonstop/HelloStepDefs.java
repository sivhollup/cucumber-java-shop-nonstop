package com.shop.nonstop;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class HelloStepDefs extends AbstractDefs {

    private RestTemplate helloShop;
    private String result;


    @Given("^I want to shop$")
    public void iWantToShop() throws Throwable {
        helloShop = new RestTemplate();
    }

    @When("^(.+) say hello$")
    public void sayHello(String name) throws Throwable {
        result = helloShop.getForObject("http://localhost:8080/hello", String.class);
    }

}
