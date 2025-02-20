package com.example.employeedemo.config;

import com.example.employeedemo.controller.EmployeeController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCreation {
    @Bean
    //@ConditionalOnProperty(name ="f.g",havingValue = "true")
    //@ConditionalOnBean(Nope.class)
    @ConditionalOnMissingBean(Nope.class)
    public Appi appi (){
        return new Appi();
    }
}
