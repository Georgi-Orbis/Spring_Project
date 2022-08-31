package com.orbisexample.demo.services;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.CarRepository;
import com.orbisexample.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    public UserService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;

        this.carRepository = carRepository;
    }




    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> findAllUsers(){
    return userRepository.findAll();
    }

    public User findUserByUsername(String username){
        return userRepository.findByUserName(username).get();
    }

    public Optional<User> findUserByID(Long id){
        return userRepository.findById(id);
    }

    public List<Car> getUsersCarsById(Long id) {
    return carRepository.findAllByUserId(id);
    }
}
