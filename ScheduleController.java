package com.aidan.tutorweb.api;

import com.aidan.tutorweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/Schedule")
@RestController
public class ScheduleController {

    private final PersonService personService;

    @Autowired
    public ScheduleController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "{id}")
    public int[] getDates(@PathVariable("id") UUID id) {
        return personService.getDates(id);
    }

}
