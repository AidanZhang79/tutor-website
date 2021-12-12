package com.aidan.tutorweb.dao;

import com.aidan.tutorweb.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    UUID insertPerson(UUID id, Person person);

    default UUID insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

    int[] getDates(UUID id);
}
