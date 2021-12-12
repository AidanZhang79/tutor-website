package com.aidan.tutorweb.dao;

import com.aidan.tutorweb.model.Student;
import com.aidan.tutorweb.model.Tutor;
import com.aidan.tutorweb.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("personDao")
public class PersonDaoImplementation implements PersonDao {

    private static List<Person> studentDB = new ArrayList<Person>();
    private static List<Person> tutorDB = new ArrayList<Person>();

    @Override
    public UUID insertPerson(UUID id, Person person) {
        if ("Tutor".equals(person.getRole())) {
            tutorDB.add(new Tutor(id, person.getName(), person.getSubject(), person.getSchedule()));
        } else if ("Student".equals(person.getRole())) {
            studentDB.add(new Student(id, person.getName(), person.getGrade(), person.getSubject(), person.getSchedule()));
        }
        return id;
    }

    @Override
    public List<Person> selectAllPeople() {
        List<Person> allPeople;
        allPeople = tutorDB;
        allPeople.addAll(studentDB);
        return allPeople;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        List<Person> allPeople;
        allPeople = tutorDB;
        allPeople.addAll(studentDB);
        return allPeople.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if (personMaybe.isEmpty()) {
            return 0;
        }
        studentDB.remove(personMaybe.get());
        tutorDB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person newPerson) {

        for (Person person : studentDB) {
            if (person.getId().equals(id)) {
                studentDB.set(studentDB.indexOf(person), new Person(id, newPerson.getName(), newPerson.getRole(), newPerson.getGrade(), newPerson.getSubject(), newPerson.getSchedule()));
                return 1;
            }
        }
        for (Person person : tutorDB) {
            if (person.getId().equals(id)) {
                tutorDB.set(tutorDB.indexOf(person), new Person(id, newPerson.getName(), newPerson.getRole(), newPerson.getGrade(), newPerson.getSubject(), newPerson.getSchedule()));
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int[] getDates(UUID id) {
        Person person = selectPersonById(id).get();
        int[][] schedule = person.getSchedule();
        String subject = person.getSubject();
        if ("Student".equals(person.getRole()))
            for (Person tutor : tutorDB) {
                if (tutor.getSubject().equals(subject) && (overlap(tutor, person) != null)) {
                    return overlap(tutor, person);
                }
            }
        else if ("Tutor".equals(person.getRole()))
            for (Person student : studentDB) {
                if (student.getSubject().equals(subject) && (overlap(student, person) != null)) {
                    return overlap(student, person);
                }
            }
        return null;
    }

        public int[] overlap (Person tutor, Person student){
            for (int day = 0; day < 6; day++) {
                int[] tutoringRange = new int[3];
                int tutorRangeStart = tutor.getSchedule()[day][0];
                int tutorRangeEnd = tutor.getSchedule()[day][1];
                int studentRangeStart = student.getSchedule()[day][0];
                int studentRangeEnd = student.getSchedule()[day][1];
                if ((!(tutorRangeEnd <= studentRangeStart || tutorRangeStart >= studentRangeEnd)) && tutorRangeStart != 0 && studentRangeStart != 0) {
                    tutoringRange[0] = day + 1;
                    tutoringRange[1] = Math.max(tutorRangeStart, studentRangeStart);
                    tutoringRange[2] = Math.min(tutorRangeEnd, studentRangeEnd);
                    tutorRangeStart = 0;
                    tutorRangeEnd = 0;
                    return tutoringRange;
                }
            }
            return null;
        }
    }



