package com.example.employeedemo.controller;

import com.example.employeedemo.model.Employee;
import com.example.employeedemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Employess", description = "Operations related to employee management")
public class EmployeeController {

    //private static final  Logger log= (Logger) LoggerFactory.getLogger(EmployeeController.class);
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/uni")
    public String rel(HttpServletRequest httpServletRequest){
        String id=httpServletRequest.getParameter("id");
        String name=httpServletRequest.getParameter("name");
        return "ID IS" +id + "Name is"+name;
    }

    @GetMapping("/employee")
    public Iterable<Employee>display(){
        log.error("Fetching User Details");
        return employeeService.showAll();

    }

    @Operation(summary = "Find employee by ID", description = "Provide an ID to look up specific employees details")
    @GetMapping("/employee/{id}")
    public Employee bid(@PathVariable Integer id){
        return employeeService.byId(id);
    }

    @Operation(summary = "Create a new Employee")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("employees")
    public Employee forAdding(@RequestBody Employee employee){
        return employeeService.add(employee);
    }

    @PutMapping("/emp/{id}")
    public Employee update(@PathVariable Integer id,@RequestBody Employee employee){
        return employeeService.update(id, employee);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id){
        employeeService.delete(id);
    }


}
