package com.orbisexample.demo.controllers;

import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllerTest {
    @Autowired
    UserRepository userRepository;
    private static User user;
    private static Car car;

    @BeforeAll
    public static void createTestUser(){
         user=  new User(1L,"username", "pass", true, "ROLE_ADMIN", "A",
                "B", new HashSet<>());
    }
    @BeforeAll
    public static void createTestCar(){
        car = new Car(1L, "brand", "model", 5, user);
    }




    @Test
     public void testCreateUserAndTestIfReturnsCorrectValues(){
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUserName());
        assertEquals("pass", user.getPassword());
        assertEquals("ROLE_ADMIN", user.getRoles());
        assertEquals("A", user.getFirstName());
        assertEquals("B", user.getLastName());
    }
    @Test
    public void testCreateCarAndTestIfReturnsCorrectValues(){
        assertEquals(1L, car.getCarId());
        assertEquals("brand", car.getBrand());
        assertEquals("model", car.getModel());
        assertEquals(5, car.getAge());
        assertEquals(user, car.getUser());
    }
    @Test
    public void testAddCarToUserCarList(){
        assertEquals(0, user.getCars().size());
        Set<Car> cars = user.getCars();
        cars.add(new Car());
        assertEquals(1, user.getCars().size());
    }
    @Test
    public void testGetCarFromEmptyUserCarSet(){
        assertThrows(Exception.class, ()-> user.getCars().iterator().next());
    }

    @Test
    public void testSetNewAgeToCar(){
        car.setAge(car.getAge()+1);
        assertEquals(6, car.getAge());
    }
    @Test
    public void testAddNewRoleToUser(){
        String roles = user.getRoles();
        roles = roles + ",ROLE_USER";
        user.setRoles(roles);
        assertEquals("ROLE_ADMIN,ROLE_USER", user.getRoles());
    }



}
