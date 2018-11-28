package com.bq.personapi;

import com.bq.personapi.person.dto.CreatedResponse;
import com.bq.personapi.person.dto.InboundPerson;
import com.bq.personapi.person.dto.PersonDto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class Stepdefs {

    private PersonContext context = new PersonContext();

    @Given("^a person name \"([^\"]*)\"$")
    public void a_person_name(String name) throws Exception {
        context.name = name;
    }

    @Given("^lastName \"([^\"]*)\"$")
    public void lastname(String lastName) throws Exception {
        context.lastName = lastName;
    }

    @Given("^email \"([^\"]*)\"$")
    public void email(String email) throws Exception {
        context.email = email;
    }

    @Given("^age (\\d+)$")
    public void age(int age) throws Exception {
        context.age = age;
    }

    @When("^I request to create a person$")
    public void i_request_to_create_a_person() throws Exception {
        var inboundPerson = new InboundPerson(context.name, context.lastName, context.email, context.age);

        var restTemplate = new RestTemplate();
        var url = "http://localhost:8080/people";

        context.response = restTemplate.postForEntity(url, inboundPerson, CreatedResponse.class);
    }

    @When("^I request to retrieve the previous person$")
    public void i_request_to_retrieve_the_previous_person() throws Exception {
        var restTemplate = new RestTemplate();
        var url = String.format("http://localhost:8080/people/%s", context.id);

        context.response = restTemplate.getForEntity(url, PersonDto.class);
    }

    @When("^I request to update a user name to \"([^\"]*)\"$")
    public void i_request_to_update_a_user_name_to(String newName) throws Exception {
        var inboundPerson = new InboundPerson(newName, context.lastName, context.email, context.age);

        var restTemplate = new RestTemplate();
        var url = String.format("http://localhost:8080/people/%s", context.id);
        var httpEntity = new HttpEntity<InboundPerson>(inboundPerson);

        context.response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, PersonDto.class);
    }

    @When("^I request to delete the previous person$")
    public void i_request_to_delete_the_previous_person() throws Exception {
        var restTemplate = new RestTemplate();
        var url = String.format("http://localhost:8080/people/%s", context.id);

        context.response = restTemplate.exchange(url, HttpMethod.DELETE, null, Object.class);
    }

    @Then("^response code must be (\\d+)$")
    public void response_code_must_be(int expectedCode) throws Exception {
        assertEquals(expectedCode, context.response.getStatusCode().value());
    }

    @Then("^person response body must contain an ID not empty$")
    public void person_response_body_must_contain_an_ID_not_empty() throws Exception {
        // Not finished
        assertEquals(CreatedResponse.class, context.response.getBody().getClass());
        assertNotNull(((CreatedResponse) context.response.getBody()).getId());
        assertNotEquals("", ((CreatedResponse) context.response.getBody()).getId());

        context.id = ((CreatedResponse) context.response.getBody()).getId();
    }

    @Then("^check email is \"([^\"]*)\"$")
    public void check_email_is(String expectedEmail) throws Exception {
        assertEquals(expectedEmail, context.email);
    }

    @Then("^returned user has name set to \"([^\"]*)\"$")
    public void returned_user_has_name_set_to(String expectedName) throws Exception {
        // Not finished
        assertEquals(PersonDto.class, context.response.getBody().getClass());
        assertEquals(expectedName, ((PersonDto) context.response.getBody()).getName());
    }

}
