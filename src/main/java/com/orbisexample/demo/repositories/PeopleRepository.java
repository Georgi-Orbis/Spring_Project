package com.orbisexample.demo.repositories;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();


}
