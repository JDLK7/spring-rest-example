package com.bq.personapi.person.repository;

import com.bq.personapi.person.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Repository extends MongoRepository<Person, String> {

    default String create(Person model) {
        var newPerson = save(model);

        return newPerson.getId();
    }

    default Person retrieveOne(String id) {
        var personOpt = findById(id);

        return personOpt.isPresent() ? personOpt.get() : null;
    }

    default List<Person> retrieveAll() {
        return findAll();
    }

    default Person update(Person model) {
        return save(model);
    }

    default void delete(String id) {
        delete(retrieveOne(id));
    }

}
