package com.orbisexample.demo.services;
import com.orbisexample.demo.entities.Person;
import com.orbisexample.demo.repositories.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();
        peopleRepository.findAll().forEach(people::add);
        return people;
    }
    public Person getPersonById(int id){
      return peopleRepository.findPersonById(id);
    }
    public void addPerson(Person person) {
        peopleRepository.save(person);
    }
    public void editPerson(Person person) {
        peopleRepository.save(person);
    }
    public void deletePersonById(int id) {
        peopleRepository.deleteById(id);
    }

}
