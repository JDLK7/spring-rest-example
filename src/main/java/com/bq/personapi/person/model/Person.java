package com.bq.personapi.person.model;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class Person {

    public static final String KIND = "people";

    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;

    public Person(String name, String lastName, String email, int age) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Person(String id, String name, String lastName, String email, int age, long createdAt, long updatedAt, long deletedAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public com.google.cloud.datastore.FullEntity toDatastoreEntity(Key key) {
        return Entity.newBuilder(key)
                .set("id", getId())
                .set("name", getName())
                .set("lastName", getLastName())
                .set("email", getEmail())
                .set("age", getAge())
                .set("createdAt", getCreatedAt())
                .set("updatedAt", getUpdatedAt())
                .set("deletedAt", getDeletedAt())
                .build();
    }

    public static Person fromDatastoreEntity(Entity entity) {
        return new Person(
            entity.getString("id"),
            entity.getString("name"),
            entity.getString("lastName"),
            entity.getString("email"),
            (int) entity.getLong("age"),
            entity.getLong("createdAt"),
            entity.getLong("updatedAt"),
            entity.getLong("deletedAt")
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

}
