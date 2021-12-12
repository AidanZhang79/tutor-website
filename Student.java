package com.aidan.tutorweb.model;

import java.util.UUID;

public class Student extends Person {
    public Student(UUID id, String name, int grade, String subject, int[][] schedule) {
        super(id, name, "Student", grade, subject, schedule);
    }
}
