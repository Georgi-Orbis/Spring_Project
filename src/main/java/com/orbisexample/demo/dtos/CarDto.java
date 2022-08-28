package com.orbisexample.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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