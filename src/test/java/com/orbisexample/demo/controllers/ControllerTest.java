package com.orbisexample.demo.controllers;
import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ControllerTest {
    User user;
    Car car;
    Car car1;
    @BeforeEach
    public void createTestUser(){
         user=  new User(1L,"username", "pass", true, "ROLE_ADMIN", "A",
                "B", new HashSet<>());
    }
    @BeforeEach
    public void createTestCar(){
        car = new Car(1L, "brand", "model", 5, new User());
        car1 = new Car(2L, "brand1", "model1", 3, new User());
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

    @Test
    public void testUserSetNewPassword(){
        user.setPassword("new pass");
        assertEquals("new pass", user.getPassword());
    }

    @Test
    public void testIfCarsAreAddedCorrectlyInUser(){
        Set<Car> carSet = new HashSet<>();
        carSet.add(car);
        carSet.add(car1);

        Set<Car> cars = user.getCars();
        cars.add(car);
        cars.add(car1);
        assertEquals(2, user.getCars().size());
        assertIterableEquals(carSet, user.getCars());

    }
}
