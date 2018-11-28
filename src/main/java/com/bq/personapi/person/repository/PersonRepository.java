package com.bq.personapi.person.repository;

import com.bq.personapi.person.model.Person;
import static com.bq.personapi.person.model.Person.KIND;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.datastore.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<Person> {

    private Datastore datastore;
    private KeyFactory keyFactory;

    public PersonRepository() {
        var resource = new ClassPathResource("keyfile.json");

        try {
            var credentials = ServiceAccountCredentials.fromStream(resource.getInputStream());

            datastore = DatastoreOptions.newBuilder().setCredentials(credentials).build().getService();
            keyFactory = datastore.newKeyFactory().setKind(KIND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String create(Person model) {
        var key = keyFactory.newKey(model.getId());
        var personEntity = datastore.add(model.toDatastoreEntity(key));

        return personEntity.getKey().getName();
    }

    @Override
    public Person retrieveOne(String id) {
        Person person = null;

        var filter = StructuredQuery.PropertyFilter.eq("id", id);
        var query = EntityQuery.newEntityQueryBuilder()
                .setKind(KIND)
                .setLimit(1)
                .setFilter(filter)
                .build();

        QueryResults results = datastore.run(query);

        if (results.hasNext()) {
            var result = (Entity) results.next();
            person = Person.fromDatastoreEntity(result);
        }

        return person;
    }

    @Override
    public List<Person> retrieveAll() {
        List<Person> people = new ArrayList<Person>();

        var query = EntityQuery.newEntityQueryBuilder()
                .setKind(KIND)
                .build();

        QueryResults results = datastore.run(query);

        while (results.hasNext()) {
            var entity = (Entity) results.next();
            people.add(Person.fromDatastoreEntity(entity));
        }

        return people;
    }

    @Override
    public Person update(Person model) {
        var key = keyFactory.newKey(model.getId());
        var entity = model.toDatastoreEntity(key);
        var updatedEntity = datastore.put(entity);

        return Person.fromDatastoreEntity(updatedEntity);
    }

    @Override
    public void delete(String id) {
        var key = keyFactory.newKey(id);

        datastore.delete(key);
    }
}
