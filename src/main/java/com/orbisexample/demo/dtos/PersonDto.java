package com.orbisexample.demo.dtos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orbisexample.demo.entities.Car;
import com.orbisexample.demo.entities.User;
import lombok.*;

import org.hibernate.validator.constraints.Length;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
    @Data
    @Valid
    public class PersonDto {
        private int id;
        @NotNull @Length(min = 2, max = 20)
        private String firstName;
        @NotNull @Length(min = 2, max = 20)
        private String lastName;
        @Min(18)
        private int age;
        private User user;
        private Set<CarDto> cars = new HashSet<>();

}


