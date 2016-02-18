package com.shop.nonstop;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@CucumberOptions(plugin = {"pretty"})
@RunWith(Cucumber.class)
@SpringApplicationConfiguration(Main.class)
public class RunCukesTest {
    @Test
    public void testName() throws Exception {


    }
}
