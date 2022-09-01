package com.orbisexample.demo.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
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
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private boolean enabled;
    @NotNull @Length(min = 2, max = 20)
    private String firstName;
    @NotNull @Length(min = 2, max = 20)
    private String lastName;
    private Set<CarDto> cars = new HashSet<>();
}