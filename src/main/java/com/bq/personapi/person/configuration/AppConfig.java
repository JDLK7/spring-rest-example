package com.bq.personapi.person.configuration;

import com.bq.personapi.person.converter.Converter;
import com.bq.personapi.person.converter.PersonConverter;
import com.bq.personapi.person.repository.PersonRepository;
import com.bq.personapi.person.repository.Repository;
import com.bq.personapi.person.service.PersonService;
import com.bq.personapi.person.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("PersonConverter")
    public Converter personConverter() {
        return new PersonConverter();
    }

    @Bean("PersonService")
    public Service personService() {
        return new PersonService();
    }

    @Bean("PersonRepository")
    public Repository personRepository() {
        return new PersonRepository();
    }
}
