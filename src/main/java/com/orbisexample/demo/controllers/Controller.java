package com.orbisexample.demo.controllers;

import com.orbisexample.demo.services.PeopleService;
import com.orbisexample.demo.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


@RestController
@Slf4j
public class Controller {
    private final PeopleService peopleService;
    private final URL url = new URL("http://localhost:8080/people");


    public Controller(PeopleService peopleService) throws MalformedURLException {
        this.peopleService = peopleService;
    }

    @RequestMapping("/people")
    public List<Person> getPeople() {
        return peopleService.getAllPeople();
    }

    @RequestMapping("/people/{id}")
    public Person getById(@PathVariable Long id) {
        return peopleService.getPersonById(id);
    }

    @PostMapping("/people")
    public void addPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
    }

    @PutMapping("/people")
    public void editPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
    }

    @DeleteMapping("/people/{id}")
    public void deleteById(@PathVariable Long id) {
        peopleService.deletePersonById(id);
    }

    @GetMapping("/people/age")
    public List<Person> getAllSelectedByAge(@RequestParam Integer age1, @RequestParam Integer age2) {
        return peopleService.findAllWithAgeBetween(age1, age2);
    }

    @GetMapping("/people/first")
    public List<Person> getAllPeopleWithFirstName(@RequestParam String firstName) {
        return peopleService.getAllPeopleWithFirstName(firstName);
    }

    @GetMapping("/people/first-last")
    public List<Person> getAllWithFirstNameAndLastName(@RequestParam String firstName,
                                                       @RequestParam String lastName) {
        return peopleService.getAllPeopleWithFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/people/contain")
    public List<Person> getAllWithFirstNameContaining(@RequestParam String content) {
        log.info("Calling method that finds Person if his name contains (content)[{}/content] url", url);
        return peopleService.getAllPeopleWithFirstNameContaining(content);
    }

    @GetMapping("/people/id")
    public Person getPersonById(@RequestParam Long id) {
        return peopleService.getPersonById(id);
    }

    @GetMapping("/people/beforeId")
    public List<Person> getPeopleByIdBefore(@RequestParam Long id) {
        return peopleService.getAllPeopleWithIdBefore(id);
    }

    @PostMapping("/people/beforeId/{id}")
    public List<Person> postPeopleByIdBefore(@PathVariable Long id) {
        return peopleService.getAllPeopleWithIdBefore(id);
    }

    @PostMapping("/people/addAll")
    public void addAllPeople(@RequestBody List<Person> people) {
        peopleService.saveAll(people);
    }

    @PatchMapping("/people/{id}/{firstName}")
    public void patchPersonInfo(@PathVariable Long id, @PathVariable String firstName) {
        Person person = peopleService.getPersonById(id);
        person.setFirstName(firstName);
        peopleService.addPerson(person);
    }

    @DeleteMapping("/people/delete/{id}")
    public Integer deletePersonByIdIfExists(@PathVariable Long id) {
        return peopleService.deletePersonByIdIfFound(id);
    }

    @GetMapping("/people/count")
    public Integer countAllPeopleWithIdHigherThan(@RequestParam Long id) {
        return peopleService.countAllWithIdHigherThan(id);
    }

    @PostMapping("/people/sum")
    public Integer sumAllPeopleAges() {
        return peopleService.sumAllPeopleAges();
    }
}
