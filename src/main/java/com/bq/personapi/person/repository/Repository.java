package com.bq.personapi.person.repository;

import com.bq.personapi.person.dto.CreatedResponse;

import java.util.List;

public interface Repository<T> {

    String create(T model);

    T retrieveOne(String id);

    List<T> retrieveAll();

    T update(T model);

    void delete(String id);

}
