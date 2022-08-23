package com.orbisexample.demo.services;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.Person;
import com.orbisexample.demo.repositories.CarRepository;
import com.orbisexample.demo.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final PeopleRepository peopleRepository;

    public CarService(CarRepository carRepository, PeopleRepository peopleRepository) {
        this.carRepository = carRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Car> getAllCars(){
    return carRepository.findAll();
    }
    public Car getCarById(Long id){
        return carRepository.findCarByCarId(id);
    }
    public void addCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCarByCarId(Long id) {
        carRepository.deleteById(id);
    }

    public void addOwner(Long personId, Long carId){
        Car car = carRepository.findCarByCarId(carId);
        car.setPerson(peopleRepository.findById(personId).get());
        carRepository.save(car);
    }

    public List<Car> findAllCarsFromSelectedBrandAndModel(String brand, String model){
    return carRepository.findAllByBrandAndModel(brand, model);
    }


}
