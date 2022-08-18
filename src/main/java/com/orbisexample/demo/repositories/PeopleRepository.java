package com.orbisexample.demo.repositories;

import com.orbisexample.demo.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends CrudRepository<Person, Integer> {
    Person findPersonById(int id);


}
