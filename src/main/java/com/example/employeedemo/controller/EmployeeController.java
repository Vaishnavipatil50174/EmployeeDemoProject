package com.example.employeedemo.controller;

import com.example.employeedemo.model.Employee;
import com.example.employeedemo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

    @Autowired
    EmployeeService employeeService;


//    @PostMapping("/file")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        // Check if the file is empty
//        if (file.isEmpty()) {
//            return "No file selected to upload!";
//        }
//
//        try {
//            // Define the file path to save the uploaded file
//            String filePath = System.getProperty("C:\\Users\\vaishnaviravindra_pa\\Documents\\Movie.txt") + "/uploads/" + file.getOriginalFilename();
//            File dest = new File("C:\\Users\\vaishnaviravindra_pa\\Desktop\\DSA");
//
//            // Create the directory if it doesn't exist
//            dest.getParentFile().mkdirs();
//
//            // Transfer the file to the destination
//            file.transferTo(dest);
//
//            return "File uploaded successfully: " + filePath;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "File upload failed: " + e.getMessage();
//        }
//    }


    @GetMapping("/uni")
    public String rel(HttpServletRequest httpServletRequest){
        String id=httpServletRequest.getParameter("id");
        String name=httpServletRequest.getParameter("name");
        return "ID IS" +id + "Name is"+name;
    }


    @GetMapping("/employee")
    public Iterable<Employee>display(){
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
