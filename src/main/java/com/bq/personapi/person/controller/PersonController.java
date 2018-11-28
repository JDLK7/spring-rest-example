package com.bq.personapi.person.controller;

import com.bq.personapi.person.converter.Converter;
import com.bq.personapi.person.converter.PersonConverter;
import com.bq.personapi.person.dto.CreatedResponse;
import com.bq.personapi.person.dto.InboundPerson;
import com.bq.personapi.person.dto.PersonDto;
import com.bq.personapi.person.model.Person;
import com.bq.personapi.person.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    @Qualifier("PersonConverter")
    private Converter<PersonDto, Person> converter;

    @Autowired
    @Qualifier("PersonService")
    private Service<Person> service;

    @GetMapping("/people")
    public List<PersonDto> retrievePeople() {
        var people = service.retrieveAll();
        var peopleDto = converter.modelListToDtoList(people);

        return peopleDto;
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDto> retrievePerson(@PathVariable String id) {
        var person = service.retrieveOne(id);

        return person != null
                ? ResponseEntity.ok().body(converter.modelToDto(person))
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/people")
    public CreatedResponse createPerson(@Valid @RequestBody InboundPerson inboundPerson) {
        var person = ((PersonConverter) converter).inboundDtoToModel(inboundPerson);
        var response = service.create(person);

        return response;
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable String id, @Valid @RequestBody InboundPerson inboundPerson) {
        var person = ((PersonConverter) converter).inboundDtoToModel(inboundPerson);
        var updatedPerson = service.update(id, person);

        return updatedPerson != null
                ? ResponseEntity.ok().body(converter.modelToDto(updatedPerson))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity deletePerson(@PathVariable String id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }


}
