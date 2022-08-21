package com.orbisexample.demo.repositories;

import com.orbisexample.demo.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
