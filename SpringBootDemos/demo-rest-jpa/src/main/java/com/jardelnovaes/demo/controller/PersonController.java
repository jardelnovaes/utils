package com.jardelnovaes.demo.controller;

import com.jardelnovaes.demo.model.Person;
import com.jardelnovaes.demo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public  ResponseEntity<List<Person>> all() {
        return  ResponseEntity.ok(repository.findAll());
    }

    @ResponseBody
    @RequestMapping(value = "/by-name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> byName(@PathVariable("name") final String name) {
        return ResponseEntity.ok(repository.findAllByName(name));
    }


    @ResponseBody
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public ResponseEntity<String> load() {
        repository.save(Person.builder().name("João").build());
        repository.save(Person.builder().name("Maria").build());
        repository.save(Person.builder().name("Carlos").build());
        repository.save(Person.builder().name("José").build());
        return ResponseEntity.status(HttpStatus.CREATED).body("Data was loaded!");
    }
}
