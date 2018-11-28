package com.bq.personapi;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
@SpringBootTest
public class PersonDtoApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
