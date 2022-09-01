package com.orbisexample.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CarDto {
    private Long carId;
    private String brand;
    private String model;
    private int age;
    @JsonIgnore
    private UserDto userDto;
}