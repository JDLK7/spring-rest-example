package com.bq.personapi.person.service;

import com.bq.personapi.person.dto.CreatedResponse;
import com.bq.personapi.person.model.Person;
import com.bq.personapi.person.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;


// Para no tener que registrarlo en AppConfig
//@org.springframework.stereotype.Service
public class PersonService implements Service<Person> {

    @Autowired
    private Repository repository;

    @Override
    public CreatedResponse create(Person model) {
        var currentTimestamp = System.currentTimeMillis();
        var createdResponse = new CreatedResponse();
        var uuid = UUID.randomUUID().toString();

        model.setId(uuid);
        model.setCreatedAt(currentTimestamp);
        model.setUpdatedAt(currentTimestamp);
        model.setDeletedAt(-1);

        createdResponse.setId(repository.create(model));

        return createdResponse;
    }

    @Override
    public Person retrieveOne(String id) {
        return repository.retrieveOne(id);
    }

    @Override
    public List<Person> retrieveAll() {
        return repository.retrieveAll();
    }

    @Override
    public Person update(String id, Person model) {
        var personToUpdate = retrieveOne(id);
        var currentTimestamp = System.currentTimeMillis();

        model.setId(personToUpdate.getId());
        model.setCreatedAt(personToUpdate.getCreatedAt());
        model.setUpdatedAt(currentTimestamp);
        model.setDeletedAt(personToUpdate.getDeletedAt());

        return repository.update(model);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
