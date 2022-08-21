package com.orbisexample.demo.repositories;

import com.orbisexample.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends CrudRepository<Person, Long> {
    Person findPersonById(Long id);
    List<Person> findAllByAgeBetween(Integer age1, Integer age2);
    List<Person> findAllByFirstName(String firstName);
    List<Person> findAllByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findPersonByFirstNameContains(String content);
    List<Person> findPeopleByIdBefore(Long id);
    Integer countByIdAfter(Long id);
    List<Person> findAll();


}
