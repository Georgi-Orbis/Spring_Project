package com.orbisexample.demo.services;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.repositories.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Long carId) {
        return carRepository.findById(carId).get();
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCarByCarId(Long carId){
        carRepository.deleteById(carId);
    }
}
