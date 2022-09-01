package com.orbisexample.demo.config;

import com.orbisexample.demo.utils.ValidationUtil;
import com.orbisexample.demo.utils.ValidationUtilImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationBeanConfiguration {
    @Bean
        public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return  new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }


}
