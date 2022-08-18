package com.orbisexample.demo.controllers;
import com.orbisexample.demo.services.PeopleService;
import com.orbisexample.demo.entities.Person;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class Controller {
    private final PeopleService peopleService;

    public Controller(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @RequestMapping("/people")
    public List<Person> getPeople(){
    return peopleService.getAllPeople();
    }

    @RequestMapping ("/people/{id}")
    public Person getById(@PathVariable int id){
       return peopleService.getPersonById(id);
    }

    @PostMapping("/people")
    public void addPerson(@RequestBody Person person){
       peopleService.addPerson(person);
    }

    @PutMapping("/people/{id}")
    public void editPerson(@RequestBody Person person){
        peopleService.editPerson(person);
    }

    @DeleteMapping("/people/{id}")
    public void deleteById(@PathVariable int id){
        peopleService.deletePersonById(id);
    }

}
