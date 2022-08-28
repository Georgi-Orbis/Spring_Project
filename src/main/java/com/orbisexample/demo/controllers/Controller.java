package com.orbisexample.demo.controllers;

import com.orbisexample.demo.dtos.UserDto;

import com.orbisexample.demo.entities.User;
import com.orbisexample.demo.services.CarService;
import com.orbisexample.demo.services.PeopleService;

import com.orbisexample.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


@RestController
@Slf4j
public class Controller {

    private final ModelMapper modelMapper;

    private final UserService userService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private final URL url = new URL("http://localhost:8080/people");

    @Autowired
    public Controller(CarService carService, ModelMapper modelMapper, UserService userService) throws MalformedURLException {
        this.modelMapper = modelMapper;
        this.userService = userService;

    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }




    @GetMapping("/users/{username}")
    public User findUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setRoles("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("success");
        return this.userService.findAllUsers();
    }


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}


