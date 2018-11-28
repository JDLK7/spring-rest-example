package com.bq.personapi.person.dto;

public class PersonDto {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private int age;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;

    public PersonDto() {
        this.id = "";
        this.name = "";
        this.lastName = "";
        this.email = "";
        this.age = -1;
        this.createdAt = -1;
        this.updatedAt = -1;
        this.deletedAt = -1;
    }

    public PersonDto(String name, String lastName, String email, int age) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public PersonDto(String id, String name, String lastName, String email, int age, long createdAt, long updatedAt, long deletedAt) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

}
