package com.orbisexample.demo.services;

import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.AuthorityRepository;
import com.orbisexample.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User getUserByUsername(String username){
        return userRepository.findById(username).get();
    }

    public String getUserAuthority(String username){
        return  authorityRepository.findById(username).get().getAuthority();

    }
}
