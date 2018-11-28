package com.bq.personapi.person.service;

import com.bq.personapi.person.dto.CreatedResponse;

import java.util.List;

public interface Service<T> {

    // ¿Cómo de mal está esto?
    CreatedResponse create(T model);

    T retrieveOne(String id);

    List<T> retrieveAll();

    T update(String id, T model);

    void delete(String id);

}
