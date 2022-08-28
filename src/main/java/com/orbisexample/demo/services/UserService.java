package com.orbisexample.demo.services;

import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getUserByUsername(String username){
        return userRepository.findById(username).get();
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
}
