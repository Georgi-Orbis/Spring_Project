package com.orbisexample.demo.dtos;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
@XmlAccessorType(XmlAccessType.FIELD)
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Valid
    public class PersonDto {

        private int id;
        @NotNull @Length(min = 2, max = 20)
        private String firstName;
        @NotNull @Length(min = 2, max = 20)
        private String lastName;
        @Min(18)
        private int age;

    }


