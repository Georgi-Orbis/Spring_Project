package com.orbisexample.demo.controllers;

import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllerTest {
    @Autowired
    UserRepository userRepository;


    @Test
    public void getAllUsers(){
    assertEquals(3, userRepository.findAll().size());

    }

    @Test
    void findById() {
        assertEquals("admin", userRepository.findById(1L).get().getUserName());
        assertEquals("pass", userRepository.findById(2L).get().getPassword());
    }



}
