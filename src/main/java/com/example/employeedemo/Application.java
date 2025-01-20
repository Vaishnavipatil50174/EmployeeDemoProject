package com.example.employeedemo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Application {
    @Value("${spring.application.name}")
    String appname;
    public void name(){
        System.out.println(appname);
    }

}
