package com.orbisexample.demo.dtos;

import com.orbisexample.demo.entities.Person;
import lombok.Data;

@Data
public class UserDto {
    private String userName;
    private String password;
    private boolean enabled;
    private Person person;
}