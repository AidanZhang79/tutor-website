package com.aidan.tutorweb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.UUID;

public class Person {

    private final UUID id;
    private final String role;
    private final String name;
    private int grade;
    private String subject;
    private int[][] schedule = new int[7][2];

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("role") String role,
                  @JsonProperty("grade") int grade,
                  @JsonProperty("subject") String subject,
                  @JsonProperty("schedule") int[][] schedule) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.grade = grade;
        this.subject = subject;
        this.schedule = schedule;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int[][] getSchedule() {
        return schedule;
    }

    public int getGrade() {
        return grade;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", subject='" + subject + '\'' +
                ", schedule=" + Arrays.toString(schedule) +
                '}';
    }
}

