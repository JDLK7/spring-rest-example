package com.bq.personapi.person.converter;


import com.bq.personapi.person.dto.InboundPerson;
import com.bq.personapi.person.dto.PersonDto;
import com.bq.personapi.person.model.Person;

public class PersonConverter implements Converter<PersonDto, Person> {

    public Person inboundDtoToModel(InboundPerson dto) {
        return new Person(dto.getName(), dto.getLastName(), dto.getEmail(), dto.getAge());
    }

    @Override
    public PersonDto modelToDto(Person model) {
        return new PersonDto(
            model.getId(),
            model.getName(),
            model.getLastName(),
            model.getEmail(),
            model.getAge(),
            model.getCreatedAt(),
            model.getUpdatedAt(),
            model.getDeletedAt()
        );
    }

    @Override
    public Person dtoToModel(PersonDto dto) {
        return new Person(dto.getName(), dto.getLastName(), dto.getEmail(), dto.getAge());
    }

}
