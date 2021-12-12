package com.aidan.tutorweb.model;

import java.util.UUID;

public class Tutor extends Person {
    public Tutor(UUID id, String name, String subject, int[][] schedule) {
        super(id, name, "Tutor", 0, subject, schedule);
    }
}
