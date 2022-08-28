package com.orbisexample.demo.config;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;

}
