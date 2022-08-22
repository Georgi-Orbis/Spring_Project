package com.orbisexample.demo.services;
import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.Person;
import com.orbisexample.demo.repositories.CarRepository;
import com.orbisexample.demo.repositories.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final CarRepository carRepository;

    public PeopleService(PeopleRepository peopleRepository, CarRepository carRepository) {
        this.peopleRepository = peopleRepository;
        this.carRepository = carRepository;
    }
    public List<Person> getAllPeople(){
        return peopleRepository.findAll();
    }
    public Person getPersonById(Long id){
      return peopleRepository.findPersonById(id);
    }
    public void addPerson(Person person) {
        peopleRepository.save(person);
    }

    public void deletePersonById(Long id) {
        peopleRepository.deleteById(id);
    }

    public List<Person> findAllWithAgeBetween(Integer age1, Integer age2){
    return peopleRepository.findAllByAgeBetween(age1, age2);
    }

    public List<Person> getAllPeopleWithFirstName(String firstName){
    return peopleRepository.findAllByFirstName(firstName);
    }

    public List<Person> getAllPeopleWithFirstNameAndLastName(String firstName, String lastName){
    return peopleRepository.findAllByFirstNameAndLastName(firstName, lastName);
    }

    public List<Person> getAllPeopleWithFirstNameContaining(String content){
    return peopleRepository.findPersonByFirstNameContains(content);
    }

    public List<Person> getAllPeopleWithIdBefore(Long id){
        return peopleRepository.findPeopleByIdBefore(id);
    }

    public void saveAll(List<Person> people){
        peopleRepository.saveAll(people);
    }

    public Integer deletePersonByIdIfFound(Long id){
        Optional<Person> optionalPerson = peopleRepository.findById(id);
        if (optionalPerson.isPresent()){
            peopleRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public Integer countAllWithIdHigherThan(Long id){
    return peopleRepository.countByIdAfter(id);
    }

    public Integer sumAllPeopleAges(){
        return peopleRepository.findAll().stream()
                .mapToInt(Person::getAge)
                .sum();

    }

    public List<Car> getAllPersonCars(Long personId){
    return carRepository.findAllByPersonID(personId);
    }



}
