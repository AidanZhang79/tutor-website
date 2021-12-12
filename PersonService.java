package com.aidan.tutorweb.service;

import com.aidan.tutorweb.dao.PersonDao;
import com.aidan.tutorweb.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public UUID addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePersonById(UUID id, Person newPerson) {
        return personDao.updatePersonById(id, newPerson);
    }

    public int[] getDates(UUID id) {
        return personDao.getDates(id);
    }

}
