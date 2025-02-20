package com.example.employeedemo.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.io.Serializable;

@Data
@Entity
@ToString
//@AllArgsConstructor

@Table(name = "employee",indexes = {
        @Index(name = "idx_emp_name", columnList = "name")
})
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotBlank(message = "name must be there")
    public String name;
}
