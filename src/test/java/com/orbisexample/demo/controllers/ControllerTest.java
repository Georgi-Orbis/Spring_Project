package com.orbisexample.demo.controllers;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

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
    assertAll(
            ()->assertEquals("admin", userRepository.findById(1L).get().getUserName()),
            ()->assertEquals("$2a$10$zMvkNIYq21r49qx542cn.u/6WDz33tKGsjyWxHtT3/xizSKwPLySe", userRepository.findById(2L).get().getPassword()),
            ()->assertThrows(Exception.class, ()-> userRepository.findById(0L).orElseThrow(),
                    "throws Exception if User is not found")
    );
}
    @Test
    void getUserIdByUserName() {
        assertEquals(1L, userRepository.findByUserName("admin").get().getId());
    }

    @Test
    void getUsersCarsById() {
        Set<Car> cars = new HashSet<>();
        User user = new User(1L,"username", "pass", true, "ROLE_ADMIN", "A",
                "B", cars);
        assertEquals(0, user.getCars().size());
        cars.add(new Car());
        assertEquals(1, user.getCars().size());
    }
}
